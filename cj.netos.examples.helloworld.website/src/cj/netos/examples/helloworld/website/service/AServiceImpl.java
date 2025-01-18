package cj.netos.examples.helloworld.website.service;

import cj.studio.ecm.annotation.CjService;

@CjService(name = "aService")
public class AServiceImpl implements IAService{

    @Override
    public String hello(String name) {
        return String.format("Hello %s!", name);
    }
}
