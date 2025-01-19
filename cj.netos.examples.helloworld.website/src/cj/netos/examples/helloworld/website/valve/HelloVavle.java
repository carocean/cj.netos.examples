package cj.netos.examples.helloworld.website.valve;

import cj.studio.ecm.Scope;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.net.CircuitException;
import cj.studio.gateway.socket.pipeline.IAnnotationInputValve;
import cj.studio.gateway.socket.pipeline.IIPipeline;

@CjService(name = "helloVavle", scope = Scope.multiton)
public class HelloVavle implements IAnnotationInputValve {
    @Override
    public int getSort() {
        return 0;
    }

    @Override
    public void onActive(String inputName, IIPipeline pipeline) throws CircuitException {
    }

    @Override
    public void flow(Object request, Object response, IIPipeline pipeline) throws CircuitException {
        pipeline.nextFlow(request, response, this);
    }

    @Override
    public void onInactive(String inputName, IIPipeline pipeline) throws CircuitException {

    }
}
