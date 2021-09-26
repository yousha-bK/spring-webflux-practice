package spring.webflux.practice.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import spring.webflux.practice.dto.PostDTO;
import spring.webflux.practice.request.CreatePostRequest;
import spring.webflux.practice.request.UpdatePostRequest;
import spring.webflux.practice.response.CommonResponse;
import spring.webflux.practice.response.PostListResponse;
import spring.webflux.practice.service.PostService;

/**
 * @author Ahsuoy Yekoraf
 * @since 9/26/21
 */
@Slf4j
@RestController
@AllArgsConstructor
public class PostController extends AbstractController {

    private final PostService service;

    @ApiOperation(value = "", notes = "GET ALL POSTS", response = PostListResponse.class, tags = {"POST Controller"})
    @GetMapping(value = "/posts", consumes = MediaType.ALL_VALUE)
    public Mono<ResponseEntity<PostListResponse>> getAll() {
        return service.getAll().map(ResponseEntity::ok);
    }

    @ApiOperation(value = "", notes = "GET POST BY ID", response = PostDTO.class, tags = {"POST Controller"})
    @GetMapping(value = "/posts/{id}", consumes = MediaType.ALL_VALUE)
    public Mono<ResponseEntity<PostDTO>> get(@PathVariable("id") Long id) {
        return service.get(id).map(ResponseEntity::ok);
    }

    @ApiOperation(value = "", notes = "CREATE NEW POST", response = CommonResponse.class, tags = {"POST Controller"})
    @PostMapping("/posts")
    public Mono<ResponseEntity<CommonResponse>> create(@RequestBody CreatePostRequest request) {

        return service.create(request).map(ResponseEntity::ok);
    }

    @ApiOperation(value = "", notes = "UPDATE POST", response = PostDTO.class, tags = {"POST Controller"})
    @PutMapping("/posts")
    public Mono<ResponseEntity<PostDTO>> update(@RequestBody UpdatePostRequest request) {

        return service.update(request).map(ResponseEntity::ok);
    }

    @ApiOperation(value = "", notes = "DELETE POST BY ID", response = PostDTO.class, tags = {"POST Controller"})
    @DeleteMapping(value = "/posts/{id}", consumes = MediaType.ALL_VALUE)
    public Mono<ResponseEntity<PostDTO>> delete(@PathVariable Long id) {

        return service.delete(id).map(ResponseEntity::ok);
    }
}
