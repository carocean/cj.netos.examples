
# ECM (Event-Connection Model) Development Documentation

## 1. Overview

Event-Connection Model (ECM) is a programming framework that focuses on connections and events. It aims to address service interaction challenges in complex distributed systems. ECM simplifies service configurations, enhances development efficiency, and optimizes runtime performance, making it highly advantageous for building modular, dynamically scalable enterprise systems.

ECM is part of the ECM technology stack, which includes various frameworks and components such as Gateway, OpenPorts, MIC, and ECM framework:
- **Gateway**: Provides distributed capabilities and functions similarly to a web container. It supports multiple protocols like HTTP, HTTPS, TCP, UDT, and UDP, leveraging NIO technology for efficient communication.
- **OpenPorts**: Facilitates integration and interaction with external systems or services, offering RESTful APIs.
- **MIC (Module Integration Components)**: Acts as a node registration and management center, handling module registration, discovery, and health monitoring.
- **ECM Framework**: Serves as the foundational component for Gateway and OpenPorts, analogous to how the Spring framework supports Spring Boot and Spring Cloud.

## 2. Advantages of ECM

1. **Javascript Service (JSS) and Java Service Integration**
    - ECM enables cross-language programming, combining Javascript Services (JSS) with Java Services.
    - Developers can write dynamic service logic with JSS while utilizing Java's robust ecosystem for complex computations and backend support.
    - The seamless interaction between JSS and Java makes ECM an efficient tool for integrating frontend and backend logic.

2. **Lightweight and High Performance**
    - ECM minimizes system coupling and reduces performance overhead through event-driven connection models.

3. **Modularity and Dynamic Extension**
    - ECM supports dynamic module loading and on-demand extensions with its built-in plugin mechanism and scanner.

4. **Modular Architecture**
    - Leverages OSGi for modular design, offering high extensibility and decoupling capabilities.

5. **IOC Service Container**
    - Provides an IOC (Inversion of Control) service container for flexible service injection and dependency management.

6. **Multi-Format Service Definitions**
    - Supports defining services through JSON, XML, and annotations, catering to diverse development needs.

7. **Dynamic Injection Capabilities**
    - Allows dynamic injection of external objects and property values for flexible service configuration.

8. **Localization Support**
    - Offers built-in support for multilingual configurations and adaptability to various environments.

9. **Fine-Grained Control**
    - Features such as `switchFilter` allow developers to balance performance and functionality by controlling interceptors.

## 3. Advanced Features of ECM

1. **Robust Event-Driven Architecture**
    - Efficient service invocation through events, reducing coupling between services.
    - Supports asynchronous event handling, improving system concurrency.

2. **High Scalability and Flexibility**
    - OSGi-based modular management enables dynamic loading, unloading, or updating of modules.
    - Service lifecycles and dependencies can be adjusted as needed.

3. **Seamless Integration with Distributed Components**
    - Gateway provides distributed routing and load balancing capabilities, supporting multiple protocols (HTTP, HTTPS, TCP, UDT, UDP).
    - OpenPorts offers RESTful API support for external system integration.
    - MIC handles service discovery and health monitoring as a node registration and management center.

4. **Unified Service Development Model**
    - Allows efficient service scripting with JSS, similar to Node.js.
    - Facilitates seamless interaction between services, including cross-language calls between Java and JSS.

5. **Dynamic Dependency Injection and Configuration**
    - Supports runtime dependency injection and dynamic configuration for enhanced development and maintenance efficiency.
    - Enables flexible adjustment of service behavior and functionality without system restart.

6. **Multi-Format Resource Support**
    - Supports JSON, XML, and annotations for resource definition.
    - Provides a powerful parsing engine for efficient and reliable resource loading.

7. **Advanced Debugging and Monitoring**
    - Built-in logging and event tracking systems for rapid issue identification.
    - Comprehensive monitoring tools to observe service performance and status in real time.

---

## 4. Configuration Overview

Below is a typical `assembly.yaml` configuration breakdown:

### 4.1 Assembly Information Configuration

```yaml
assemblyInfo:
  assemblyTitle: helloworld
  assemblyDescription: Produced by CJ Studio, built on the LNS platform
  assemblyCompany: CJ Studio
  assemblyProduct: cj.netos.examples.helloworld.ecm
  assemblyCopyright: Copyright 2011
  guid: 3AD05D29-80AE-440B-AC52-C9D9150F9797
  assemblyVersion: 1.0.0.0
  assemblyFileVersion: 1.0.0.0
```

**Explanation**:
- `assemblyTitle`: Module title.
- `assemblyDescription`: Module description for documentation and management.
- `assemblyVersion` and `assemblyFileVersion`: Define the current module version for version control.
- `guid`: Unique identifier for the module.

### 4.2 Service Container Configuration

```yaml
serviceContainer:
  name: netContainer
  switchFilter: off
  monitors:
  jss:
    - module: helloJss
      package: cj.netos.examples.helloworld.ecm.jss
      unzip: true
      searchMode: link
      extName: .jss.js
  scans:
    - package: cj.netos.examples.helloworld.ecm.service
      extName: .class|.json|.xml
      exoterical: true
```

**Explanation**:
1. **Service Container Name**: Identifies the service container for reference and extension.
2. **`switchFilter`**: Controls service interception behavior (`on` to enable, `off` to disable).
3. **Monitors**:
    - Dynamically loads plugins such as the `helloJss` module.
    - Configures JSS package paths and search strategies (`searchMode`).
    - Supports `.jss.js` script extensions.
4. **Scanners**:
    - Scans specified package paths for resources (e.g., `.class`, `.json`, `.xml`).
    - Allows external dependencies with `exoterical: true`.

### 4.3 Global Settings

```yaml
global:
  default: en_US
  desc: Fallback language if the required language is not available for certain modules.
```

**Explanation**:
- `default`: Sets the default language (e.g., English).
- `desc`: Describes system behavior in multilingual environments.

---

## 5. ECM Capabilities and Examples

Below is a simple ECM example demonstrating plugin loading and service scanning.

### Sample Code

#### `helloJss.jss.js`

```javascript
/*
 * Author: Andrew
 * Description: Declares the services referenced by this JSS.
 */
exports.hello = function (name) {
    return 'Hello ' + name + '!';
};
```

#### `HelloService.java`

```java
package cj.netos.examples.helloworld.ecm.service;

import cj.netos.examples.helloworld.ecm.IHello;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;

@CjService(name = "helloService", isExoteric = true)
public class HelloService implements IHello {
    @CjServiceRef(refByName = "$.cj.jss.helloJss.helloJssService")
    IHello jssService;

    @Override
    public String hello(String name) {
        String hello = jssService.hello(name);
        return String.format("The result of the Java service calling the JSS service: %s!", hello);
    }
}
```

#### Workflow
1. At system startup, `serviceContainer` loads the `helloJss` plugin and scans for services.
2. The Java service `HelloService` dynamically injects the JSS service using `@CjServiceRef`.
3. The JSS service `helloJssService.jss.js` implements the `IHello` interface, providing a dynamic greeting.
4. `helloService.hello()` calls the JSS logic and returns the result to the Java service, completing cross-language interaction.

---

## 6. Conclusion

ECM offers developers a powerful and straightforward way to build distributed systems. Its modular architecture, cross-language capabilities, and dynamic service injection make it highly versatile. Gateway provides distributed routing and protocol support (HTTP, HTTPS, TCP, UDT, UDP), while OpenPorts facilitates external interaction via RESTful APIs. MIC ensures reliable module registration and health monitoring. ECM is ideal for high-performance, modular distributed systems requiring dynamic loading and flexibility.

---
