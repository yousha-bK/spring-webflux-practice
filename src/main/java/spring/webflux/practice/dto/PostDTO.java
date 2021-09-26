package spring.webflux.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import spring.webflux.practice.bean.Post;

import java.io.Serializable;

/**
 * @author Ahsuoy Yekoraf
 * @since 9/26/21
 */
@AllArgsConstructor
@Data
@Builder
public class PostDTO implements Serializable {

    public static PostDTO of(Post post, ModelMapper modelMapper) {

        return modelMapper.map(post, PostDTO.class);
    }
}
