package cj.netos.examples.helloworld.website;

import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.net.CircuitException;
import cj.studio.gateway.socket.Destination;
import cj.studio.gateway.socket.app.GatewayAppSiteProgram;
import cj.studio.gateway.socket.app.ProgramAdapterType;

/*
 * This is the main entry point of the openports program. Openports is a type of gateway application that provides Restful API capabilities.
 * The formatting requirements are as follows:
 * - Must be declared as a service named: $.cj.studio.gateway.app
 * - Must be declared as an exoteric external service accessible outside the assembly: isExoteric = true
 * - Must derive from the gateway superclass: GatewayAppSiteProgram.
 */
@CjService(name = "$.cj.studio.gateway.app", isExoteric = true)
public class AppsiteProgram extends GatewayAppSiteProgram {
    @Override
    protected void onstart(Destination destination, String s, ProgramAdapterType programAdapterType) throws CircuitException {

    }
}
