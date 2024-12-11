package com.example.nk.logtracer.app.proxy.v2;

import org.springframework.web.bind.annotation.*;

@RestController
public class ProxyOrderControllerV2 {
    private final ProxyOrderServiceV2 proxyOrderServiceV2;

    public ProxyOrderControllerV2(ProxyOrderServiceV2 proxyOrderServiceV2) {
        this.proxyOrderServiceV2 = proxyOrderServiceV2;
    }

    @GetMapping(value = "v2/request")
    public String request(@RequestParam("itemId") String itemId) {
        proxyOrderServiceV2.orderItem(itemId);

        return "ok";
    }

}
