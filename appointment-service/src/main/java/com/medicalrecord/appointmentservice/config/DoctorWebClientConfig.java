package com.medicalrecord.appointmentservice.config;

import com.medicalrecord.appointmentservice.client.DoctorClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class DoctorWebClientConfig {

    private final LoadBalancedExchangeFilterFunction filterFunction;

    public WebClient doctorWebClient() {
        return WebClient.builder()
                .defaultStatusHandler(HttpStatusCode::isError, response -> Mono.empty())
                .baseUrl("http://doctor-service")
                .filter(filterFunction)
                .build();
        // TODO improve this, handle exceptions better, for now used only in gp uic constraint validator, returns
        // false when response from api call is error, otherwise true (as per logic in gp uic constraint validator)
    }

    @Bean
    public DoctorClient doctorClient() {
        HttpServiceProxyFactory httpServiceProxyFactory
                = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(doctorWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(DoctorClient.class);
    }

}
