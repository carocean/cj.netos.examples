package cj.netos.examples.helloworld.ecm.service;

import cj.netos.examples.helloworld.ecm.IHello;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;

@CjService(name = "helloService",isExoteric = true)
public class HelloService implements IHello {
    @CjServiceRef(refByName = "$.cj.jss.helloJss.helloJssService")
    IHello jssService;

    @Override
    public String hello(String name) {
        String hello = jssService.hello(name);
        return String.format("The result of the Java service calling the JSS service: %s!", hello);
    }
}
