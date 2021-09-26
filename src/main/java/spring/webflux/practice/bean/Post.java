package spring.webflux.practice.bean;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.UpdateBehavior;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;
import spring.webflux.practice.constant.DynamoDBTableConstant;
import spring.webflux.practice.util.TimeUtil;

/**
 * @author Ahsuoy Yekoraf
 * @since 9/23/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class Post {

    @Getter(onMethod_ = {@DynamoDbPartitionKey,
            @DynamoDbSecondaryPartitionKey(indexNames = {DynamoDBTableConstant.POST_TABLE_GSI_CREATED_AT})})
    private Long id;

    @Getter(onMethod_ = {@DynamoDbAttribute("userId")})
    private Long userId;

    @Getter(onMethod_ = {@DynamoDbAttribute("title")})
    private String title;

    @Getter(onMethod_ = {@DynamoDbAttribute("body")})
    private String body;

    @Getter(onMethod_ = {@DynamoDbAttribute("createdAt"),
            @DynamoDbSecondarySortKey(indexNames = {DynamoDBTableConstant.POST_TABLE_GSI_CREATED_AT})})
    @Setter(onMethod_ = {@DynamoDbUpdateBehavior(UpdateBehavior.WRITE_IF_NOT_EXISTS)})
    @Builder.Default
    private Long createdAt = TimeUtil.getCurrentTimestampInSecond();

    @Getter(onMethod_ = {@DynamoDbAttribute("updatedAt")})
    @Setter(onMethod_ = {@DynamoDbUpdateBehavior(UpdateBehavior.WRITE_ALWAYS)})
    @Builder.Default
    private Long updatedAt = TimeUtil.getCurrentTimestampInSecond();
}
