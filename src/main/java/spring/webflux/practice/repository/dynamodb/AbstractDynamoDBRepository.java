package spring.webflux.practice.repository.dynamodb;

import lombok.extern.slf4j.Slf4j;
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

    protected AbstractDynamoDBRepository(String tableName,
                                         Class<T> beanClass,
                                         DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient) {
        this.dynamoDbAsyncTable = dynamoDbEnhancedAsyncClient
                .table(tableName, TableSchema.fromBean(beanClass));
    }

    protected PagePublisher<T> getAll() {
        return dynamoDbAsyncTable.scan(ScanEnhancedRequest.builder().consistentRead(true).build());
    }

    protected PagePublisher<T> getAll(String index) {

        var scannedData = dynamoDbAsyncTable
                .index(index)
                .scan(ScanEnhancedRequest.builder()
                        .consistentRead(false).build());


        return PagePublisher.create(scannedData);
    }

    protected PagePublisher<T> query(Key queryKey) {

        var queryConditional = QueryConditional
                .keyEqualTo(queryKey);

        return dynamoDbAsyncTable.query(r -> r.queryConditional(queryConditional).scanIndexForward(false));
    }

    protected CompletableFuture<Void> save(T t) {

        return dynamoDbAsyncTable.putItem(t);
    }

    protected CompletableFuture<T> update(T t) {

        return dynamoDbAsyncTable.updateItem(t);
    }

    protected CompletableFuture<T> delete(Key key) {

        return dynamoDbAsyncTable.deleteItem(key);
    }

    protected CompletableFuture<T> get(Key key) {

        return dynamoDbAsyncTable.getItem(key);
    }

    protected DynamoDbAsyncTable<T> getTable() {

        return dynamoDbAsyncTable;
    }
}
