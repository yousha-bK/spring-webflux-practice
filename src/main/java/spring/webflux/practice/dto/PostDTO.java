package spring.webflux.practice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import spring.webflux.practice.bean.Post;
import spring.webflux.practice.util.TimeUtil;

import java.io.Serializable;

/**
 * @author Ahsuoy Yekoraf
 * @since 9/26/21
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostDTO implements Serializable {

    private Long id;
    private Long userId;
    private String title;
    private String body;

    @JsonIgnore
    private Long createdAt;
    @JsonIgnore
    private Long updatedAt;

    public static PostDTO of(Post post, ModelMapper modelMapper) {

        return modelMapper.map(post, PostDTO.class);
    }

    public String getCreatedAt() {
        return TimeUtil.getTimestamp(createdAt);
    }

    public String getUpdatedAt() {
        return TimeUtil.getTimestamp(updatedAt);
    }
}
