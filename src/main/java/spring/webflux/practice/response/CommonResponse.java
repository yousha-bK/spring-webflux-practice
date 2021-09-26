package spring.webflux.practice.response;

import lombok.*;
import spring.webflux.practice.util.TimeUtil;

/**
 * @author Ahsuoy Yekoraf
 * @since 9/26/21
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResponse {

    @Builder.Default
    private String timestamp = TimeUtil.getCurrentTimestamp();
}
