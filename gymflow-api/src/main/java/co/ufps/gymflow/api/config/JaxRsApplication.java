package co.ufps.gymflow.api.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api") // La URL base será: http://localhost:8080/gymflow/api
public class JaxRsApplication extends Application {
    // Se deja vacía. Jakarta EE se encarga de escanear los controladores.
}