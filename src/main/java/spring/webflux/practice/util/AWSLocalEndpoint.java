package spring.webflux.practice.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Ahsuoy Yekoraf
 * @since 9/23/21
 */
@Data
@Component
public class AWSLocalEndpoint {

    @Value("${aws.dynamodb.endpoint}")
    private String dynamoDBEndpoint;

    @Value("${aws.region}")
    private String awsRegion;
}
