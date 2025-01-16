
# Openports Application Setup Guide

Openports is a distributed microservice platform designed specifically for developing RESTful APIs, built on the ecm service container framework. It is supported by the gateway (Gateway) for distributed networking, running as a type of application under the gateway.

## Development Environment

- **JDK 1.8**
- **Gateway** (latest version: [Gateway GitHub Repository](https://github.com/carocean/cj.studio.gateway2/tree/cj/cmdtools/gateway))
- **Nexus** (Gradle compilation is used in this example, requiring access to a private repository to fetch dependencies)

## Running the Example

### Direct Execution of the Program

1. Change directory to the current project:
    ```bash
    cd cmdtools/gateway
    ```

2. Execute the following commands:
    - On Linux or macOS:
    ```bash
    sh gateway.sh
    ```
    - On Windows:
    ```bash
    gateway.bat
    ```

3. Open the URL:
    ```
    http://localhost:6060/hello/portsapi/
    ```

   The page displaying the open ports will be shown. Click on the `helloworld` item in the left tree, and input the `name` parameter on the right-hand side of the page.

![Ports API](pic.jpg)

**Summary**: This example demonstrates the functionality of open ports. You will get an overall impression of its operation. Next, we will explore how to run the example using IntelliJ IDEA if you have cloned the source code from GitHub.

### Debugging the Program

1. Check out the project using IntelliJ IDEA.
2. The example project uses Nexus for the private repository, and dependencies such as `cj.netos.*` and `cj.studio.*` might not be found initially. Don’t worry.
3. Set up a Nexus private repository, recommended to run Nexus using Docker Desktop. Below is the `docker-compose.yml` code:
    ```yaml
    version: '3.7'
    services:
      nexus:
        image: sonatype/nexus3:latest
        container_name: nexus
        restart: always
        ports:
          - "65000:8081"  # Map port 65000 to container's port 8081
        volumes:
          - nexus-data:/nexus-data
        environment:
          INSTALL4J_ADD_VM_PARAMS: "-Xms2G -Xmx2G -XX:MaxDirectMemorySize=2G"
        networks:
          - geochat_network

    volumes:
      nexus-data:
        driver: local

    networks:
      geochat_network:
        external: true
    ```

4. Create a private repository named `netos` in Nexus.

5. Check out the following projects from GitHub:
    - **ecm Base Service Container**: [GitHub Repository](https://github.com/carocean/cj.studio.ecm)
    - In IntelliJ IDEA, open the Gradle panel (right panel), and run the command:
    ```bash
    gradle uploadArchives
    ```
   It should automatically publish to the Nexus repository. If the repository path is incorrect, update the Nexus repository address in the `./gradle.properties` file.

    - Similarly, check out the Gateway project and publish it: [GitHub Repository](https://github.com/carocean/cj.studio.gateway2)

6. In IntelliJ IDEA, open the Gradle panel for the current example project, and run the following command:
    ```bash
    gradle release
    ```
   If it runs successfully, the setup is complete.

7. Now, proceed to configure the program for debugging:
    - In the IntelliJ IDEA toolbar, go to `Run/Debug Configurations` and select `Edit Configurations`.
    - Click on the `+` button to add a new configuration: `Add new configuration`.
    - Select `Jar Application` as the configuration type.
    - In the configuration window, set the `Path To Jar` to the file `gateway-2.4.6.jar` found in the `cmdline` directory of this project.
    - Copy the current directory path where `gateway-2.4.6.jar` is located and paste it in the `Program Arguments` field with the following argument:
    ```bash
    -d <paste path here>
    ```
    - After confirming, you will see the configuration in the `Run/Debug Configurations` dropdown. You can now run or debug the program.

    - Before debugging, you can set a breakpoint in the `helloworld` method of the `HelloworldPorts` class, then open the URL `http://localhost:6060/hello/portsapi/` to test the `helloworld` open port. If the breakpoint is hit, the debugging setup is successful.

## Developing Openports Applications

### Openports Project Structure

The development guidelines require that a complete Openports project contains at least two sub-projects:
1. A project dedicated to declaring open ports, which only declares the API interfaces without implementing them. This project is typically named with the `.openports` suffix.
2. An implementation project, which provides the actual service implementation for the open ports. This is typically named with the `.program` suffix.

#### Declare Open Ports Interface

In the `.openports` project, declare the open ports interface like this:

```java
package cj.netos.examples.helloworld.openports;

import cj.studio.openport.AccessTokenIn;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenportParameter;
import cj.studio.openport.annotations.CjOpenports;

@CjOpenports(usage = "This is an example of an Openports")
public interface IHelloworldPorts {
    @CjOpenport(usage = "How can I get the name you typed in via the parameter name?", tokenIn = AccessTokenIn.nope)
    String helloworld(@CjOpenportParameter(usage = "the name", name = "name") String name);
}
```

This interface declares an open port and includes the necessary annotations (`@CjOpenports`, `@CjOpenport`, and `@CjOpenportParameter`) to expose the API endpoint.

#### Implement the Open Ports Interface

The implementation class is typically found in the `.program` project. Here’s an example implementation:

```java
package cj.netos.examples.helloworld.xxx.openports.port;

import cj.netos.examples.helloworld.openports.IHelloworldPorts;
import cj.studio.ecm.annotation.CjService;

@CjService(name = "/helloworld.ports")
public class HelloworldPorts implements IHelloworldPorts {
    @Override
    public String helloworld(String name) {
        return String.format("Hello %s!", name);
    }
}
```

### Openports Configuration Files

There are a few configuration files to be aware of:

1. **`cj.properties`**: The main configuration file for the ecm service container.
    - The configuration items in `assembly.yaml` should be reviewed in the context of the cj.studio.ecm project documentation.

2. **Gateway Program Configuration**:
    - `servers.json`: Configures server settings.
    - `cluster.json`: Configures request routing, such as targeting the service program or forwarding to other openports servers.
    - `mic-registry.json`: Registers this project with the mic registry, enabling remote management.

3. **Assembly Directory**:
    - Located at `./cmdtools/gateway/assemblies`, it contains the main assembly and plugin files (e.g., MyBatis, MongoDB, Redis, etc.).

### Monitoring and Managing the Setup

In debugging mode, you can monitor and manage configurations using the IntelliJ IDEA console. The configuration files are persistently stored in the directories mentioned above.
