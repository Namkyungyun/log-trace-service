package com.example.nk.logtracer.app.proxy.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @RequestMapping(value="v3/request", method = RequestMethod.GET)
    String request(@RequestParam String itemId) {
        orderService.orderItem(itemId);

        Object result = Map.of("result", "ok");

        return "ok";
    }

}
