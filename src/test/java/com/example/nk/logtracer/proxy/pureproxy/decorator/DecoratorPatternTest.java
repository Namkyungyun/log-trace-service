package com.example.nk.logtracer.proxy.pureproxy.decorator;

import com.example.nk.logtracer.proxy.pureproxy.decorator.code.*;
import org.junit.jupiter.api.Test;

public class DecoratorPatternTest {

    @Test
    void realComponentTest() {
        Component component = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(component);

        client.execute();;
    }


    @Test
    void messageDecoratorTest() {
        Component realComponent = new RealComponent();
        Component messageComponent = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageComponent);

        client.execute();
    }

    @Test
    void timeDecoratorTest() {
        Component realComponent = new RealComponent();
        Component messageComponent = new MessageDecorator(realComponent);
        Component timeComponent = new TimeDecorator(messageComponent);

        DecoratorPatternClient client = new DecoratorPatternClient(timeComponent);

        client.execute();
    }

}
