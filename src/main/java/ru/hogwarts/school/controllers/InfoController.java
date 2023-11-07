package ru.hogwarts.school.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/info")
public class InfoController {
    private final Logger logger = LoggerFactory.getLogger(InfoController.class);
    @Value("${server.port}")
    private String port;

    @GetMapping
    public String getPort() {
        return port;
    }

    @GetMapping("/return-integer")
    public void returnInteger() {
        long start = System.currentTimeMillis();
        int result = Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
        long finish = System.currentTimeMillis();
        logger.info("result {}, time {}", result, finish - start);
    }

    @GetMapping("/return-integer-with-parallel-stream")
    public void returnIntegerWithParallelStream() {
        long start = System.currentTimeMillis();
        int result = Stream.iterate(1, a -> a + 1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
        long finish = System.currentTimeMillis();
        logger.info("result {}, time {}", result, finish - start);
    }
}
