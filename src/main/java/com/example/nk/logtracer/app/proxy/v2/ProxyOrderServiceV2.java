package com.example.nk.logtracer.app.proxy.v2;

public class ProxyOrderServiceV2 {
    private final ProxyOrderRepositoryV2 repository;

    public ProxyOrderServiceV2(final ProxyOrderRepositoryV2 repository) {
        this.repository = repository;
    }

    public void orderItem(String itemId) {
        repository.save(itemId);
    }

}
