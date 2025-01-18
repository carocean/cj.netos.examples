package cj.netos.examples.helloworld.website;

import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.net.Circuit;
import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;
import cj.studio.gateway.socket.app.IGatewayAppSiteResource;
import cj.studio.gateway.socket.app.IGatewayAppSiteWayWebView;
import cj.ultimate.gson2.com.google.gson.Gson;
import org.jsoup.nodes.Document;

/**
 * Homepage: Since openports is specifically designed for Restful APIs, access to the homepage can be disabled when no root directory service is provided.
 * @author caroceanjofers
 *
 */
@CjService(name="/")
public class Home implements IGatewayAppSiteWayWebView{

	@Override
	public void flow(Frame frame, Circuit circuit, IGatewayAppSiteResource resource) throws CircuitException {
		Document doc=resource.html("/index.html");
		circuit.content().writeBytes(doc.toString().getBytes());
	}

}
