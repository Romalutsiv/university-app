package org.university.app.config;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class GlobalErrorWebExceptionHandler implements ErrorWebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        HttpStatusCode status;
        if (ex instanceof ResponseStatusException) {
            status = ((ResponseStatusException) ex).getStatusCode();
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        Map<String, Object> errorAttributes = new HashMap<>();
        errorAttributes.put("error", ex.getMessage());
        errorAttributes.put("path", exchange.getRequest().getPath().value());
        errorAttributes.put("status", status.value());
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        byte[] bytes = errorAttributes.toString().getBytes(StandardCharsets.UTF_8);
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
    }
    @Bean
    @Order(-2)
    public ErrorWebExceptionHandler globalErrorWebExceptionHandlerNew() {
        return new GlobalErrorWebExceptionHandler();
    }
}
