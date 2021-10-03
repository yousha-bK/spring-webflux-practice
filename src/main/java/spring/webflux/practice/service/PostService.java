package spring.webflux.practice.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.webflux.practice.dto.PostDTO;
import spring.webflux.practice.request.CreatePostRequest;
import spring.webflux.practice.request.UpdatePostRequest;
import spring.webflux.practice.response.CommonResponse;
import spring.webflux.practice.response.PostListResponse;

/**
 * @author Ahsuoy Yekoraf
 * @since 9/26/21
 */
public interface PostService {

    Mono<PostListResponse> getAll();

    Flux<PostDTO> getViewAllList();

    Mono<CommonResponse> create(CreatePostRequest request);

    Mono<PostDTO> get(Long id);

    Mono<PostDTO> update(UpdatePostRequest request);

    Mono<PostDTO> delete(Long id);
}
