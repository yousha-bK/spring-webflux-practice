package spring.webflux.practice.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import spring.webflux.practice.enums.Profile;
import spring.webflux.practice.util.AWSLocalEndpoint;

import java.net.URI;

/**
 * @author Ahsuoy Yekoraf
 * @since 9/23/21
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class DynamoDBConfig {

    private final AWSLocalEndpoint awsLocalEndpoint;
    @Value("${app.profile}")
    private String appProfile;

    @Bean
    public DynamoDbEnhancedAsyncClient getDynamoDbEnhancedAsyncClient() {

        return DynamoDbEnhancedAsyncClient.builder()
                .dynamoDbClient(getDynamoDbAsyncClient())
                .build();
    }

    @Bean
    public DynamoDbAsyncClient getDynamoDbAsyncClient() {

        if (Profile.DEV.name().toLowerCase().equals(appProfile)) {

            return DynamoDbAsyncClient.builder()
                    .endpointOverride(URI.create(awsLocalEndpoint.getDynamoDBEndpoint()))
                    .region(Region.of(awsLocalEndpoint.getAwsRegion()))
                    .build();
        }

        return DynamoDbAsyncClient
                .create();
    }
}
