FROM tomcat:8.5-alpine

LABEL maintainer="ortizman@gmail.com"

COPY tomcat/conf/tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
COPY tomcat/conf/context.xml /usr/local/tomcat/webapps/manager/META-INF/context.xml

COPY historiaSocial/target/*.war /usr/local/tomcat/webapps/

EXPOSE 5000

WORKDIR /usr/local/tomcat