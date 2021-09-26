package spring.webflux.practice.request;

import lombok.*;
import org.modelmapper.ModelMapper;
import spring.webflux.practice.bean.Post;
import spring.webflux.practice.dto.PostDTO;

import java.io.Serializable;

/**
 * @author Ahsuoy Yekoraf
 * @since 9/26/21
 */
@Data
@ToString
@NoArgsConstructor
@Builder
public class CreatePostRequest implements Serializable {

    public static PostDTO of(Post post, ModelMapper modelMapper) {
        return modelMapper.map(post, PostDTO.class);
    }
}
