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

Build

1. Ir al directorio principal del proyecto (donde se encuentra el pom.xml)
* Asegurarse que se configuro correctamente la persistencia
* Ejecutar `mvn clean package` para generar el artefacto
* Copiar el WAR en el directorio de deployment
     

Chequear que este funcionando
--------------

//TODO


### Browsing metadata

//TODO

### Pruebas desde un cliente:

//TODO