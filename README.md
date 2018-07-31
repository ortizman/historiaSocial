# Historia Social Hospitalaria
##### Proyecto WEB que lleva la historia social de los pacientes, donde se pueden cargar las practicas (incluytendo las ambulatorias)

Tabla de Contenidos
=================

1. [Requerimientos del sistema](#requerimientos-del-sistema)
* [Persistencia](#persistencia)
* [Build y Deploy del proyecto](#build-y-deploy-del-proyecto)
* [Chequear que este funcionando](#chequear-que-este-funcionando)
  * [Browsing metadata](#browsing-metadata)
  * [Pruebas desde un cliente:](#pruebas-desde-un-cliente)

Requerimientos del sistema
--------------------------
* Maven 3.1.1 o superior
* JDK 1.6 o superior
* Tomcat 7+
* MySQL 5+

Persistencia
------------

Se utiliza JPA para la persistencia. En el archivo de `persistence.xml` se puede configurar todas las propiedaes respecto al motor de base de datos que se utilice.
** Importante: **
	Como minimo requiere cambiar las propiedaes:
	
		<property name="hibernate.connection.username" value="historia" />
		<property name="hibernate.connection.password" value="historia" />
		<property name="hibernate.connection.url" value="jdbc:mysql://localhost/historiaSocial" />



Build y Deploy del proyecto
-------------------------------

### Build y deploy normal

1. Ir al directorio principal del proyecto (donde se encuentra el pom.xml)
* Asegurarse que se configuro correctamente la persistencia
* Ejecutar `mvn clean package` para generar el artefacto
* Copiar el WAR en el directorio de deployment
     
### Usando Docker

1. Tener instalado Docker y Docker Compose 1.21+
2. Sobre el root del proyecto se encuentra el docker-compose que tiene 3 servicios:
	* tomcat: Imagen docker con tomcat 8.5. El WAR de la app se copia dentro de la imagen
	* mysql-db: Mysql 5.7. **IMPORTANTE:** Cambiar el volumen donde se almacenan los datos de la base dentro de docker-compose.yml 
	* nginx: proxy a tomcat

	**NOTA:** Siempre que se haga un cambio sobre el codigo fuente de la app se tiene que volver a generar el WAR y forzar a docker a re-construir la imagen

Comando para hacer el build de la historia social, construir la imagen y levantar los containers

```bash
( cd historiaSocial/ && mvn clean package -DskipTests) && docker-compose up --build
```

Chequear que este funcionando
--------------

Navegar a localhost:8080/manager para acceder al manager de tomcat
Navegar a localhost:8080/HistoriaSocial para acceder a al sistema

### Login

Usuario por defecto: admin / admin