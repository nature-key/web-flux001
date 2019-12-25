package com.jiepi.web.webflux;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class SomeController {

    @GetMapping("/common")
    public String common() {
        log.info("start time");
        String commonHander = send("common hander");
        log.info("end time");
        return commonHander;
    }

    @GetMapping("/flux")
    public Flux<String> flux() {
        log.info("start time");
        Flux<String> flux = Flux.just("beijing", "shanghao", "shenzhen");
        log.info("end time");
        return flux;
    }

    @RequestMapping("/fluxArray")
    public Flux<String> fluxArray(@RequestParam String[] cities) {
        return Flux.fromArray(cities);
    }

    @RequestMapping("/fluxList")
    public Flux<String> fluxList(@RequestParam List<String> cities) {
        return Flux.fromStream(cities.stream());
    }

    @RequestMapping("/fluxTime")
    public Flux<String> fluxTime(@RequestParam List<String> cities) {
        log.info("start time");
        Flux<String> stream = Flux.fromStream(cities.stream().map(i -> send(i)));
        log.info("end time");
        return  stream;
    }

    private String send(String msg) {
        try {
            log.info("send");
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            log.error("error", e);
        }
        return msg;
    }
}
