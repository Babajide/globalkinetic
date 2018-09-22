GK rest - Backend Application `gk-rest`

# Introduction
Thank you for the opportunity. I have been asked to develop a stateful client-server application as described in the instruction via email. 

The project is  web service which uses the following technologies:

* Java 1.8
* Spring Boot
* Database H2 (In-Memory)
* Maven

## Running the application 
There 2 ways of starting the sever. I have added a convenient start.sh script.
You can invoke the script using...
# `sh start.sh`


The other way is:
# `mvn spring-boot:run`

NOTE: Server log should show something similar to this with default port `8484`. You can override this by passing a new port number via cmd or change in `application.properties, see server.port=8484`
## WARNING!!! If you change the port number, you must change in web-client`
```2018-04-22 19:19:43.261  INFO 43809 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
   2018-04-22 19:19:43.323  INFO 43809 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8484 (http)
   2018-04-22 19:19:43.328  INFO 43809 --- [           main] co.za.randstack.GkRestApplication        : Started GkRestApplication in 4.971 seconds (JVM running for 8.784)
   
```
## GK-Web-client

Once the server is running, you can fire up the client for testing. The web client will send http requests to backend for processing.
You can access the h2 database server using http://localhost:8484/h2 specify JDBC URL as `jdbc:h2:./data/db` default User Name is `sa`

The client is a separate application and can be found here `https://bitbucket.org/randstack/gk-web/src/master/README.md`