package cj.netos.examples.helloworld.openports;

import cj.studio.openport.AccessTokenIn;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenportParameter;
import cj.studio.openport.annotations.CjOpenports;
/*
 * This interface declares open ports with the following specifications:
 * - The interface must be declared as an open port: @CjOpenports
 * - Interface methods must be declared using @CjOpenport.
 *
 * Notes:
 * 1. The @CjOpenports annotation is required to mark the interface for open port functionality.
 * 2. Each method within the interface must use the @CjOpenport annotation to expose its API endpoints.
 * 3. The above declarations will be visible on the /portsapi/ page and can be used for testing purposes.
 * 4. Ensure proper API documentation for each exposed method for better usability and integration.
 * 5. It is recommended to declare the ports interface in a separate project and implement it in the main program (program). This is because callers of the ports API, who want to use strong typing for invocation, need to include this JAR package.
 */
@CjOpenports(usage = "This is an example of an Openports")
public interface IHelloworldPorts {
    @CjOpenport(usage = "How can I get the name you typed in via the parameter name?",tokenIn = AccessTokenIn.nope)
    String helloworld(@CjOpenportParameter(usage = "the name" ,name = "name") String name);
}
