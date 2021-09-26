package spring.webflux.practice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import spring.webflux.practice.bean.Post;
import spring.webflux.practice.dto.PostDTO;
import spring.webflux.practice.repository.dynamodb.PostRepository;
import spring.webflux.practice.request.CreatePostRequest;
import spring.webflux.practice.request.UpdatePostRequest;
import spring.webflux.practice.response.CommonResponse;
import spring.webflux.practice.response.PostListResponse;

/**
 * @author Ahsuoy Yekoraf
 * @since 9/26/21
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public Mono<PostListResponse> getAll() {

        return repository.getAllPosts()
                .map(post -> PostDTO.of(post, modelMapper))
                .collectList()
                .map(postDTOs -> PostListResponse.builder()
                        .posts(postDTOs)
                        .build());
    }

    @Override
    public Mono<CommonResponse> create(CreatePostRequest request) {

        return repository.savePost(modelMapper.map(request, Post.class))
                .then(Mono.just(CommonResponse.builder().build()));
    }

    @Override
    public Mono<PostDTO> get(Long id) {

        return repository.getPost(id)
                .map(post -> modelMapper.map(post, PostDTO.class));
    }

    @Override
    public Mono<PostDTO> update(UpdatePostRequest request) {

        return repository.updatePost(modelMapper.map(request, Post.class))
                .map(post -> modelMapper.map(post, PostDTO.class));
    }

    @Override
    public Mono<PostDTO> delete(Long id) {

        return repository.deletePost(id)
                .map(post -> modelMapper.map(post, PostDTO.class));
    }
}
