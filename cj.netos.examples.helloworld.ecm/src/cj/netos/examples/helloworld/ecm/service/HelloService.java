package cj.netos.examples.helloworld.ecm.service;

import cj.netos.examples.helloworld.ecm.IHello;
import cj.studio.ecm.annotation.CjService;

@CjService(name = "helloService", isExoteric = true)
public class HelloService implements IHello {

    @Override
    public String hello(String name) {
        return String.format("Hello %s!", name);
    }
}
