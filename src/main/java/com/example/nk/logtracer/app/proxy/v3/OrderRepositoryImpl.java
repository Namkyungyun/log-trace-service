package com.example.nk.logtracer.app.proxy.v3;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public void save(String itemId) {
        if(itemId.equals("ex")) {
            throw new IllegalStateException();
        }
    }
}
