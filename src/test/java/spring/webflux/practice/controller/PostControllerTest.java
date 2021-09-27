package spring.webflux.practice.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import spring.webflux.practice.bean.Post;
import spring.webflux.practice.dto.PostDTO;
import spring.webflux.practice.request.CreatePostRequest;
import spring.webflux.practice.response.CommonResponse;
import spring.webflux.practice.response.PostListResponse;
import spring.webflux.practice.service.PostService;
import spring.webflux.practice.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ahsuoy Yekoraf
 * @since 9/27/21
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@ActiveProfiles("dev")
@WebFluxTest(controllers = PostController.class)
class PostControllerTest {

    private static final String BASE_API_URL = "/api/posts";

    @MockBean
    PostService service;

    @Autowired
    private WebTestClient webTestClient;


    @Test
    @DisplayName(value = "Create New Post Test")
    void TestCreatePost() {

        var createPostRequest = CreatePostRequest.builder()
                .id(7L)
                .userId(1L)
                .title("Test Title 7")
                .body("Test Body 7")
                .build();

        var response = CommonResponse.builder()
                .timestamp(TimeUtil.getCurrentTimestamp())
                .build();

        Mockito.when(service.create(createPostRequest)).thenReturn(Mono.just(response));

        webTestClient.post()
                .uri(BASE_API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(createPostRequest))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(CommonResponse.class)
                .isEqualTo(response);

        Mockito.verify(service, Mockito.times(1))
                .create(createPostRequest);
    }

    @Test
    @DisplayName(value = "Get All Posts Test")
    void TestGetAllPosts() {

        List<PostDTO> posts = new ArrayList<>();

        var post = Post.builder()
                .id(1L)
                .userId(1L)
                .title("Test Title")
                .body("Test Body")
                .build();

        posts.add(PostDTO.of(post, new ModelMapper()));

        var postListResponse = PostListResponse.builder()
                .posts(posts)
                .build();

        Mockito.when(service.getAll()).thenReturn(Mono.just(postListResponse));

        webTestClient.get()
                .uri(BASE_API_URL)
                .exchange()
                .expectStatus()
                .isOk();

        Mockito.verify(service, Mockito.times(1)).getAll();
    }

    @Test
    @DisplayName(value = "Get Post By Id Test")
    void TestGetPostById() {

        var post = Post.builder()
                .id(1L)
                .userId(1L)
                .title("Test Title")
                .body("Test Body")
                .build();

        var postDTO = PostDTO.of(post, new ModelMapper());

        Mockito.when(service.get(postDTO.getId())).thenReturn(Mono.just(postDTO));

        webTestClient.get()
                .uri(BASE_API_URL + "/" + postDTO.getId())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Post.class);

        Mockito.verify(service, Mockito.times(1)).get(postDTO.getId());
    }
}
