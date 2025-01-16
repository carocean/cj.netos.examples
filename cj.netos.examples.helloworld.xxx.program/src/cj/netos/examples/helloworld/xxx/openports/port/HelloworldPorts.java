package cj.netos.examples.helloworld.xxx.openports.port;

import cj.netos.examples.helloworld.openports.IHelloworldPorts;
import cj.studio.ecm.annotation.CjService;
/*
 * To implement an open port, simply declare it as a regular service. The service name will be used as the access URL for the API.
 * You can choose to use the ".ports" extension, omit the extension altogether, or use the ".service" extension — all options are valid.
 */
@CjService(name = "/helloworld.ports")
public class HelloworldPorts implements IHelloworldPorts {
    @Override
    public String helloworld(String name) {
        return String.format("Hello %s!", name);
    }
}
