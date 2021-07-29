Consultorio Médico
=================================

_Sistema para el registro y atención de Pacientes de un Consultorio Médico._

* Registro de pacientes
* Registro de doctores
* Registro de consultas médicas


Tabla de contenido
------------------

* [Pre-requisitos](#pre-requisitos)
* [Base de Datos](#base-de-datos)
* [Clonar el Repositorio](#clonar-el-repositorio)
* [Back End](#back-end)
* [Front End](#front-end)
* [Licencia](#licencia)

Pre-requisitos
------------------

_Software necesario para el desarrollo_

1. MySQL
1. Java 8
1. Gradle
1. NodeJS
1. Visual Studio Code

Base de Datos
------------------

_Script para la creación de la base de datos_

```sql
CREATE DATABASE doctor_office;

USE doctor_office;

CREATE TABLE patient (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  birth_date DATE NOT NULL,
  address VARCHAR(255) NOT NULL,
  image TEXT NULL
);

CREATE TABLE doctor (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  specialty VARCHAR(100) NOT NULL,
  birth_date DATE NOT NULL,  
  address VARCHAR(255) NULL,
  image TEXT NULL
);

CREATE TABLE consultation(
  id INT AUTO_INCREMENT PRIMARY KEY,
  patient_id INT NOT NULL,
  doctor_id INT NOT NULL,
  description VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  prescription_drugs VARCHAR(255) NOT NULL,
  FOREIGN KEY (patient_id) REFERENCES patient (id) ON UPDATE RESTRICT ON DELETE CASCADE,
  FOREIGN KEY (doctor_id) REFERENCES doctor (id) ON UPDATE RESTRICT ON DELETE CASCADE
);
```

_Datos de prueba para iniciar la base de datos_

```sql
INSERT INTO doctor (name, last_name, specialty, birth_date, address, image) VALUES
('JORGE', 'MONTAÑO', 'MEDICINA INTERNA', '1990-04-15', 'Calle Cornejo, Nro 123', NULL),
('MARTHA', 'CARRASCO', 'NEUMOLOGIA', '1985-01-20', 'Zona Sur Av Botelo, Nro 554', NULL),
('MATIAS DAN', 'RAMAYO', 'PEDIATRIA', '2000-02-04', 'Villa Salome Calle 23, Nro 22', NULL);

INSERT INTO patient (name, last_name, birth_date, address, image) VALUES
('ANTONIO', 'AGUILAR', '2005-10-02', 'Villa Bolivar', NULL),
('MONICA', 'BUSTILLO', '2010-10-02', 'Zona Norte', NULL),
('CARLOS', 'VILLA', '1989-05-12', 'Av. Augusto, Nro 54', NULL);

INSERT INTO consultation (patient_id, doctor_id, description, created_at, prescription_drugs) VALUES
(1, 1, 'Revisión Anual', CURRENT_TIMESTAMP, 'Paracetamol, Aspirina'),
(2, 1, 'Consulta Programada', CURRENT_TIMESTAMP, 'Calmadol, Refrianex'),
(3, 2, 'Cita para Vacuna', CURRENT_TIMESTAMP, 'Diclofenaco, Vitamina A'),
(1, 3, 'Chequeo de Rutina', CURRENT_TIMESTAMP, 'Complejo B, Ibuprofeno');
```

Clonar el Repositorio
---------------------

_Clonar un repositorio extrae una copia integral de todos los datos del mismo que GitHub tiene en ese momento, incluyendo todas las versiones para cada archivo y carpeta para el proyecto_

1. Abre **Git Bash**
2. Cambia el directorio de trabajo actual a la ubicación en donde quieres clonar el directorio
3. Escribe `git clone`, y luego pega la URL del repositorio
```
$ git clone https://github.com/titancrisis/doctor_office.git
```
4. Presiona Enter para crear tu clon local

Back-End
---------------------

_Para la ejecutar el back-end, vaya al directorio `back_test` y luego ejecute el siguiente comando en una ventana de terminal_

```
$ ./gradlew bootRun
```

_Debería ver un resultado similar al siguiente:_

```
 $ Starting DoctorOfficeApplication using Java 1.8.0_241 on GAMER with PID 13680
 $ No active profile set, falling back to default profiles: default
 $ Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
 $ For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
 $ Bootstrapping Spring Data JPA repositories in DEFAULT mode.
 $ Finished Spring Data repository scanning in 72 ms. Found 3 JPA repository interfaces.
 $ Tomcat initialized with port(s): 9090 (http)
 $ Starting service [Tomcat]
 $ Starting Servlet engine: [Apache Tomcat/9.0.41]
 $ Initializing Spring embedded WebApplicationContext
 $ Root WebApplicationContext: initialization completed in 1546 ms
 $ HHH000204: Processing PersistenceUnitInfo [name: default]
 $ HHH000412: Hibernate ORM core version 5.4.27.Final
 $ HCANN000001: Hibernate Commons Annotations {5.1.2.Final}
 $ HikariPool-1 - Starting...
 $ HikariPool-1 - Start completed.
 $ HHH000400: Using dialect: org.hibernate.dialect.MySQL5InnoDBDialect
 $ HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
 $ Initialized JPA EntityManagerFactory for persistence unit 'default'
 $ spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during vi 
 $ LiveReload server is running on port 35729
 $ Tomcat started on port(s): 9090 (http) with context path ''
 $ Started DoctorOfficeApplication in 3.739 seconds (JVM running for 4.191)
```

_Ahora ejecute el servicio con curl (en una ventana de terminal separada), ejecutando el siguiente comando:_

```
$ curl localhost:9090/api/doctor

[{"id":1,"name":"JORGE","lastName":"MONTAÑO","specialty":"MEDICINA INTERNA","birthDate":"15/04/1990","address":"Calle Cornejo, Nro 123"},
{"id":2,"name":"MARTHA","lastName":"CARRASCO","specialty":"NEUMOLOGIA","birthDate":"20/01/1985","address":"Zona Sur Av Botelo, Nro 554"},
{"id":3,"name":"MATIAS DAN","lastName":"RAMAYO","specialty":"PEDIATRIA","birthDate":"04/02/2000","address":"Villa Salome Calle 23, Nro 22"}]
```

Front-End
---------------------

_Primero asegúrese de tener [Angular CLI](https://github.com/angular/angular-cli#installation) instalado globalmente. Para la ejecutar el front-end, vaya al directorio `front_test` y luego ejecute el siguiente comando en una ventana de terminal_

```
$ npm install
```

_Para iniciar la aplicación en el servidor de desarrollo ejecute el siguiente comando_

```
$ ng serve
```

_Finalmente navega a [http://localhost:4200/](http://localhost:4200/)_

_Credenciales:_

> Usuario: **admin**

> Contraseña: **admin**


Licencia
------------------

_El proyecto es de uso libre y educativo_
