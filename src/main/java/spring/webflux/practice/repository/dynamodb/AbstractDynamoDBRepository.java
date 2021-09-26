package spring.webflux.practice.repository.dynamodb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PagePublisher;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;

import java.util.concurrent.CompletableFuture;

/**
 * @author Ahsuoy Yekoraf
 * @since 9/23/21
 */
@Slf4j
public class AbstractDynamoDBRepository<T> {

    private final DynamoDbAsyncTable<T> dynamoDbAsyncTable;

    @Value("${app.profile}")
    private String appProfile;

    protected AbstractDynamoDBRepository(String tableName,
                                         Class<T> beanClass,
                                         DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient) {
        this.dynamoDbAsyncTable = dynamoDbEnhancedAsyncClient
                .table(getTableName(tableName), TableSchema.fromBean(beanClass));
    }

    public PagePublisher<T> getAll() {
        return dynamoDbAsyncTable.scan(ScanEnhancedRequest.builder().consistentRead(true).build());
    }

    public PagePublisher<T> query(String partitionKeyValue) {

        var queryKey = Key.builder()
                .partitionValue(partitionKeyValue)
                .build();

        var queryConditional = QueryConditional
                .keyEqualTo(queryKey);

        return dynamoDbAsyncTable.query(r -> r.queryConditional(queryConditional).scanIndexForward(false));
    }

    public CompletableFuture<Void> save(T t) {

        return dynamoDbAsyncTable.putItem(t);
    }

    public CompletableFuture<T> update(T t) {

        return dynamoDbAsyncTable.updateItem(t);
    }

    public CompletableFuture<T> delete(T t) {

        return dynamoDbAsyncTable.deleteItem(t);
    }

    private String getTableName(String tableName) {

        return appProfile + tableName;
    }

}
