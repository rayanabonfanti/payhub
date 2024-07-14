package foodify.iam.utils;

public class ConstantsUtil {

    public static final String LOGIN = "/foodify/v1/authorization/login";
    public static final String AUTH_ISSUE = "auth-api";
    public static final String URL_USER_CUSTOMER = "http://localhost:8082/foodify/v1/user-customer";
    public static final String PATH_USER_CUSTOMER = "/get-login";

    private ConstantsUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

}
