package foodify.user.customer.config;

import foodify.user.customer.interceptor.GrpcJwtInterceptor;
import foodify.user.customer.interceptor.GrpcSecurityInterceptor;
import net.devh.boot.grpc.server.interceptor.GlobalServerInterceptorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {

    @Bean
    public GlobalServerInterceptorConfigurer globalInterceptorConfigurerAdapter() {
        return registry -> {
            registry.add(new GrpcJwtInterceptor());
            registry.add(new GrpcSecurityInterceptor());
        };
    }
}
