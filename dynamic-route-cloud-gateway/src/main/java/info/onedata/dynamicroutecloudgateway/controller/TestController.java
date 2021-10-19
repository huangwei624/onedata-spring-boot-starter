package info.onedata.dynamicroutecloudgateway.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0.0
 * @author: huangwei
 * @date: 2021/12/30 13:55
 * @description: TestController
 */
@RestController
public class TestController {
    @GetMapping(value = "/test", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> test() {
        return Flux.interval(Duration.ofSeconds(1)).map(item -> UUID.randomUUID().toString());
    }
}
