package spring.webflux.practice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.webflux.practice.dto.PostDTO;

import java.util.List;

/**
 * @author Ahsuoy Yekoraf
 * @since 9/26/21
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostListResponse {

    private List<PostDTO> posts;
}
