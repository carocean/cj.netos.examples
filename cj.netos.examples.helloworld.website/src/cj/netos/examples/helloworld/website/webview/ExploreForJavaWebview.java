package cj.netos.examples.helloworld.website.webview;

import cj.netos.examples.helloworld.website.service.IAService;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.Circuit;
import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;
import cj.studio.gateway.socket.app.IGatewayAppSiteResource;
import cj.studio.gateway.socket.app.IGatewayAppSiteWayWebView;
import org.jsoup.nodes.Document;

@CjService(name = "/pages/exploreforjava.html")
public class ExploreForJavaWebview implements IGatewayAppSiteWayWebView {
    @CjServiceRef
    IAService aService;

    @Override
    public void flow(Frame frame, Circuit circuit, IGatewayAppSiteResource resource) throws CircuitException {
        Document doc = resource.html(frame.relativeUrl());
        doc.select("#welcome-message").html(aService.hello("Tom"));
        circuit.content().writeBytes(doc.toString().getBytes());
    }
}
