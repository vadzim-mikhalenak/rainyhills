# Rainy Hills REST service

Test project. It has one end-point to calculate water volume on the base of array of Hills that are passed in the body of the request
 
## Getting Started

To start this application on your local machine you can:
  - run it from IDE (IntelliJIdea for example) as spring-boot project
  - build it as jar 
   (using maven as it show below. It will also run tests)
   first you need to change 
   ```
   <packaging>war</packaging> 
   ```
   to
   
   ```
   <packaging>jar</packaging> 
   ```
   build it
   ```
   ./mvnw clean package
   ```
   and then run
   
   ```
   java -jar rainyhills-0.0.1-SNAPSHOT.jar
   ```
   
   the application will be available under http://localhost:8080/
   
  - deploy on WildFly
  1) start Wildfly server
  2) execute the followin command:
  
   to deploy the application 
   ```   
   mvn wildfly:deploy
   ```
   
   
   to undeploy
   ```   
   mvn wildfly:undeploy
   ```
   
   
   Application then will be available under http://localhost:8080/rainyhills-0.0.1-SNAPSHOT
   
### Prerequisites

Install WilfFly server if you want to have application running on the server.

### Installing

Please, see 'Getting Started' section

## Deployment

Please, see 'Getting Started' section

## Using

There is one end-point:
```
<host>/rainyhills/calculatewater
 ```

It accepts POST request with Content-Type=application/json and json body of the following structure:

```
{
	"hills": [1,2,3,1,2]
}
```
- hills - an array of ints representing height of Hills 

Content of the request is validated to be non-null and not contain negative data.

Example of request (Response with http code 200):

```
http://localhost:8080/rainyhills/calculatewater

{
	"hills":[3,2,4,1,2]
}
 
```
Response:
```
{
    "amount_of_water": 2
}
 
```

Invalid request:
```
http://localhost:8080/v1/diff/1/left

{
	"hills":[-3,2,4,1,2]
}
 
```

Response:
 
```
{
    "error_message": "Hills should not contain negative value"
}
 
```


## Built With

* [Java](https://www.oracle.com/java/index.html) - Programming Language
* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring](https://spring.io/) - Framework with various of modules to build EE applications
* [Spring-boot](http://projects.spring.io/spring-boot/) - The Easiest way to start application with Spring Framework

## Authors

* **Vadzim Mikhalenak**

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
