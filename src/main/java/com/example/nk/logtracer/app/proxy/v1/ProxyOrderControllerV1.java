package com.example.nk.logtracer.app.proxy.v1;

import org.springframework.web.bind.annotation.*;

@RestController
public interface ProxyOrderControllerV1 {

    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("v1/no-log")
    String noLog();

}
