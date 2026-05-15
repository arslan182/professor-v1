
package com.acme.professor.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.tomcat.TomcatWebServerFactory;
import org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;


/// Factory für Tomcat als Servlet-Container, um auch `HTTP` bereitzustellen - zusätzlich zu `HTTPS`.
///
/// @author [Jürgen Zimmermann](mailto:Juergen.Zimmermann@h-ka.de)
sealed interface TomcatHttpConnector permits DevConfig {
    /// Port für HTTP (zusätzlich zu HTTPS).
    int HTTP_PORT = 8080;

    /// Protokoll-Ausgabe, wenn Kubernetes erkannt wird.
    ///
    /// @return Factory für Tomcat als Servlet-Container, um zusätzlich zu `HTTPS` und Port `8443` auch `HTTP` mit Port
    ///         `8080` bereitzustellen.
    @Bean
    default WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatHttpConnector() {
        return factory -> {
            final var connector = new Connector(TomcatWebServerFactory.DEFAULT_PROTOCOL);
            connector.setScheme("http");
            connector.setPort(HTTP_PORT);
            connector.setSecure(false);
            factory.addAdditionalConnectors(connector);
        };
    }
}
