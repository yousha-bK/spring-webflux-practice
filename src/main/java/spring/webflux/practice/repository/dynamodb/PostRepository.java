package spring.webflux.practice.repository.dynamodb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import spring.webflux.practice.bean.Post;
import spring.webflux.practice.constant.DynamoDBTableConstant;

import static spring.webflux.practice.constant.DynamoDBTableConstant.POST_TABLE;

/**
 * @author Ahsuoy Yekoraf
 * @since 9/23/21
 */
@Slf4j
@Repository
public class PostRepository extends AbstractDynamoDBRepository<Post> {

    public PostRepository(Environment environment, DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient) {
        super(environment.getActiveProfiles()[0] + POST_TABLE, Post.class, dynamoDbEnhancedAsyncClient);
    }

    public Flux<Post> getAllPosts() {

        return Flux.from(getAll(DynamoDBTableConstant.POST_TABLE_GSI_CREATED_AT)
                .flatMapIterable(Page::items));
    }

    public Mono<Post> savePost(Post post) {

        return Mono.fromFuture(save(post))
                .map(iVoid -> post);
    }

    public Mono<Post> getPost(Long id) {

        var key = Key.builder()
                .partitionValue(id)
                .build();

        return Mono.fromFuture(get(key));
    }

    public Mono<Post> updatePost(Post post) {

        return Mono.fromFuture(update(post));
    }

    public Mono<Post> deletePost(Long id) {

        var key = Key.builder()
                .partitionValue(id)
                .build();

        return Mono.fromFuture(delete(key));
    }
}
