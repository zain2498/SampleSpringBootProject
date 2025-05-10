                                            EXCEPTION HANDLING
@ExceptionHandler : we use this annotation to handle the specific exception and returns the custom error response back to the client.
@ControllerAdvice : we use this annotation to handle the exception globally it means that we use to handle all the specific exceptions and global exceptions in a  single place.

                                            SPRING BOOT ACTUATOR
Spring boot actuator module provides production ready feature such as health checks, monitoring and metrics.
The Spring boot actuator module enables you to monitor the application using HTTP endpoints and JMX.
Spring boot provides the spring boot starter actuator library to auto-configure actuator.

adding the dependency in pom.xml file
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
then we add this configuration to enable all the acutator end points in our application.properties file.
management.endpoints.web.exposure.include =*
once follow both the above steps then run the application and you got this in tomcat logs:
Exposing 14 endpoints beneath base path '/actuator'
with the help of this url we can access the actuator end point in order to monitor our application.
we added the info to our application in application.properties file
like this to see accessing this endpoint http://localhost:9090/actuator/info
these 4 properties added below:
management.info.env.enabled = true
info.app.name = Spring boot web application
info.app.description = Spring boot web application demo to perform CRUD operations
info.app.version = 1.0.0

acutator/health
this end point shows the health of the application including the disk space, databases and more
and add this configuration in application.properties file to see the datbase and other attributes of the application
management.endpoint.health.show-details=always

these are all the end points of actuator
/info
/health
/beans
/conditions
/mappings
/configprops --> to see all the config properties in our application which is defined by us in application.properties file or spring in application.yml
/metrics --> shows metrics of current application such as how much memory is being used, how much memory is free, the size of the heap used, the number of threads used
/env --> exposes all the properties from the spring's ConfigurableEnvironment interface, such as a list of active profiles, application properties, system environment variables and so on.
 /threaddump
/logger
/shutdown --> we can gracefully shut down our application and to do so,
we have to add this configuration in application.properties file
management.endpoint.shutdown.enabled = true
and then call this end point from postman with payload none
http://localhost:9090/actuator/shutdown and this is how our application shut down.


                                            




