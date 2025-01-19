package cj.netos.examples.helloworld.ecm;

import cj.studio.ecm.Assembly;
import cj.studio.ecm.CJSystem;
import cj.studio.ecm.IAssembly;
import cj.studio.ecm.adapter.IActuator;
import cj.studio.ecm.adapter.IAdaptable;

public class HelloMain {

    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        String jarFileName = dir + "/cj.netos.examples.helloworld.ecm.starter/outputs/cj.netos.examples.helloworld.ecm-1.0.0.jar";
        IAssembly assembly = Assembly.loadAssembly(jarFileName);
        assembly.start();
        IAdaptable activable = (IAdaptable) assembly.workbin().part("helloService");
        IActuator actuator = activable.getAdapter(IActuator.class);
        String ret = (String) actuator.exeCommand("hello", "Andrew");
        CJSystem.logging().info("This is the result returned by the Java service helloService: " + ret);
        assembly.stop();
    }
}
