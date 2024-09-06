package foodify.user.customer.interceptor;

public class GrpcMethodInterceptor {

    private GrpcMethodInterceptor() {}

    static boolean verifyMethodSecurityInterceptorGrpc(String methodName) {
        return "foodify.user.customer.TestService/GetTestGrpc".equals(methodName);
//                ||
//                "foodify.user.customer.UserCustomerService/GetUserByLogin".equals(methodName);
    }

}
