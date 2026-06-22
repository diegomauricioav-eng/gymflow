# GymFlow API - Sistema de Gestión de Gimnasios

Este proyecto contiene el desarrollo y despliegue de la API REST para el sistema GymFlow. El backend fue desarrollado utilizando el estándar corporativo de Jakarta EE y estructurado bajo una Arquitectura Hexagonal (Puertos y Adaptadores), logrando un desacoplamiento completo entre la lógica del dominio, los casos de uso y la infraestructura externa (base de datos y controladores).

---

## Estructura de la Arquitectura

Para el diseño del sistema se aplicaron los principios de la Arquitectura Hexagonal para asegurar que el núcleo del negocio no dependa de tecnologías externas:
* **Core (Dominio y Casos de Uso):** Contiene las entidades de negocio y las reglas de la aplicación sin ningún tipo de acoplamiento a frameworks.
* **Puertos (Ports):** Interfaces puras de Java que definen cómo interactúa el núcleo con el exterior (tanto para recibir datos como para persistirlos).
* **Adaptadores (Adapters):** Capa de infraestructura que implementa los puertos. Aquí se ubican los controladores REST (adaptadores de entrada) y los repositorios que manejan JPA (adaptadores de salida).

---

## Ficha Técnica del Entorno de Despliegue

El empaquetado y la puesta en marcha de la aplicación se consolidaron localmente bajo las siguientes tecnologías y versiones específicas:

* **Lenguaje de Programación:** Java 21 (JDK21)
* **Especificación Corporativa:** Jakarta EE 10
* **Gestor de Proyectos y Dependencias:** Apache Maven 3.9.16
* **Servidor de Aplicaciones:** GlassFish 7.0.8
* **Proveedor de Persistencia (ORM):** EclipseLink 4.0.8 (JPA 3.x)
* **Motor de Base de Datos:** MySQL Community Server 8.0.46
* **Formato de Empaquetado:** Artefacto .war (Web Application Archive)

---

## Configuración de Red y Puertos Local

Para el aislamiento del entorno y la ejecución del servidor se definieron los siguientes parámetros en localhost:

* **Puerto del Servicio HTTP (API):** 8080 (Canal donde corre la aplicación).
* **Puerto de Administración de GlassFish:** 4848 (Asignado al dominio aislado `gym_domain` creado por terminal para evitar conflictos con el almacén de certificados `keystore.p12` del dominio por defecto).
* **Puerto del Motor de MySQL:** 3306

---

## Proceso de Despliegue y Conexión JDBC

La integración entre el servidor de aplicaciones y el motor relacional se configuró directamente en la infraestructura del servidor mediante los siguientes pasos:

1. **Compilación:** Se generó el archivo empaquetado `gymflow.war` mediante el ciclo de vida de Maven.
2. **Creación del Dominio:** Se levantó un dominio limpio (`gym_domain`) ejecutando `asadmin create-domain` para asegurar un entorno libre de errores de certificados criptográficos en local.
3. **Pool de Conexiones JDBC:** Se registró un connection pool llamado `Gymflow` enlazado a la clase del driver de conexión `com.mysql.cj.jdbc.MysqlConnectionPoolDataSource`. En el archivo `domain.xml` se configuraron las propiedades necesarias para el handshake con MySQL 8:
   * `user`: root
   * `password`: admin
   * `databaseName`: gymflow
   * `useSSL`: false (Para deshabilitar la encriptación SSL en desarrollo local).
   * `allowPublicKeyRetrieval`: true (Permitir el intercambio de llaves públicas con el motor).
4. **Recurso JNDI:** Se creó el recurso físico bajo el nombre de búsqueda `jdbc/gymflowDS`, vinculándolo directamente con la unidad de persistencia (`gymflowPU`) definida en el proyecto.
5. **Generación del Esquema:** Al realizar el despliegue del `.war` por consola (`asadmin deploy`), la especificación de JPA interactuó con el esquema físico previamente creado en MySQL Workbench, construyendo de forma automática las tablas y relaciones del sistema.

---

## Pruebas de los Endpoints

Los controladores de la infraestructura están desarrollados utilizando únicamente peticiones transaccionales de tipo POST para la inserción y persistencia de datos. Al no implementar métodos GET en esta fase, las pruebas no pueden realizarse mediante la barra de direcciones de un navegador convencional (ya que este opera estrictamente bajo GET). El protocolo de pruebas requiere el uso de clientes HTTP como Postman o la extensión Thunder Client.

* **URL Base de la API:** `http://localhost:8080/gymflow/api/`
* **Formato de Entrada/Salida:** `application/json`

### Ejemplo de Petición HTTP para Inserción
* **Método:** POST
* **Endpoint Local:** `http://localhost:8080/gymflow/api/clientes` (o el recurso correspondiente según el @Path del controlador).
* **Headers:** `Content-Type: application/json`
* **Body (JSON):**
```json
{
    "cedula": "1099011099",
    "nombre": "Hansel Ceron",
    "telefono": "3001234567"
}
