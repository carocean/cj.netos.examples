package cj.netos.examples.helloworld.ecm;

import cj.studio.ecm.Assembly;
import cj.studio.ecm.IAssembly;
import cj.studio.ecm.adapter.IActuator;
import cj.studio.ecm.adapter.IAdaptable;

public class HelloMain {

    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        String jarFileName = dir + "/cj.netos.examples.helloworld.ecm/outputs/cj.netos.examples.helloworld.ecm-1.0.0.jar";
        IAssembly assembly = Assembly.loadAssembly(jarFileName);
        assembly.start();
        IAdaptable activable = (IAdaptable) assembly.workbin().part("helloService");
        System.out.println(activable);
//        IActuator actuator = activable.getAdapter(IActuator.class);
//        String ret = (String) actuator.exeCommand("hello", "Andrew");
//        System.out.println("This is the result returned by the Java service helloService: " + ret);
        assembly.stop();
    }
}
