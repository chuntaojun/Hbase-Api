package com.tensor.nacos.nacosapi.handler;

import com.tensor.nacos.nacosapi.service.NacosService;
import com.tensor.nacos.nacosapi.util.HttpUrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.util.function.Consumer;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author liaochuntao
 */
@Slf4j
@Component
public class NacosHandlerImpl implements NacosHandler {

    @Value("${nacos.client.service.name}")
    private String hbaseApi;

    @Autowired
    private NacosService nacosService;

    @Override
    public Mono<ServerResponse> put(ServerRequest request) {
        return request.bodyToMono(String.class).map(s -> Tuples.of(s, HttpUrlUtil.url(nacosService.getInstance(hbaseApi), request.path())))
                .map(tuple2 -> WebClient.create(tuple2.getT2()).put()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(Mono.just(tuple2.getT1()), String.class)
                        .retrieve()
                        .bodyToMono(String.class))
                .flatMap(stringMono -> ok().body(stringMono, String.class));
    }

    @Override
    public Mono<ServerResponse> post(ServerRequest request) {
        return request.bodyToMono(String.class).map(s -> Tuples.of(s, HttpUrlUtil
                .url(nacosService.getInstance(hbaseApi), request.path())))
                .map(tuple2 -> WebClient.create(tuple2.getT2()).post()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(Mono.just(tuple2.getT1()), String.class)
                        .retrieve()
                        .bodyToMono(String.class))
                .flatMap(stringMono -> ok().body(stringMono, String.class));
    }

    @Override
    public Mono<ServerResponse> get(ServerRequest request) {
        return Mono.just(HttpUrlUtil.url(nacosService.getInstance(hbaseApi), request.path()))
                .map(url -> WebClient.builder()
                        .defaultHeaders(httpHeaders -> request.headers().asHttpHeaders())
                        .build()
                        .get()
                        .uri(url)
                        .retrieve()
                        .bodyToMono(String.class))
                .flatMap(stringMono -> ok().body(stringMono, String.class));
    }
}
