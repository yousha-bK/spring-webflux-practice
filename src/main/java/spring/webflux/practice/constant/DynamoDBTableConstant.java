package spring.webflux.practice.constant;

/**
 * @author Ahsuoy Yekoraf
 * @since 9/23/21
 */
public class DynamoDBTableConstant {

    private DynamoDBTableConstant() {}

    public static final String TABLE_COMMON_PART = "-swp";

    public static final String POST_TABLE = TABLE_COMMON_PART + "-post";

}
