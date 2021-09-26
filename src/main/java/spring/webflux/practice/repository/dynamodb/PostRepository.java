package spring.webflux.practice.repository.dynamodb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import spring.webflux.practice.bean.Post;

import static spring.webflux.practice.constant.DynamoDBTableConstant.POST_TABLE;

/**
 * @author Ahsuoy Yekoraf
 * @since 9/23/21
 */
@Slf4j
@Repository
public class PostRepository extends AbstractDynamoDBRepository<Post> {

    public PostRepository(DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient) {
        super(POST_TABLE, Post.class, dynamoDbEnhancedAsyncClient);
    }

    public Flux<Post> getAllPosts() {

        return Flux.from(getAll().flatMapIterable(Page::items));
    }

    public Mono<Post> savePost(Post post) {

        return Mono.fromFuture(save(post))
                .map(iVoid -> post);
    }
}
