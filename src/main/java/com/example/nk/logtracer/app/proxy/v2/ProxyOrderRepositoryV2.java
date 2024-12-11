package com.example.nk.logtracer.app.proxy.v2;

public class ProxyOrderRepositoryV2 {
    public void save(String itemId) {
        if(itemId.equals("ex")) {
            throw  new IllegalStateException();
        }

        sleep(1000);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
