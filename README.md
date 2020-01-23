# project-group-2-complex-java-programming
Project in the course Complex Java-programming, ITHS. A MUD game using SpringBoot.
Created by Daniel Björnberg, Filip Christofferson, Tonny Frisk and Elin Sexton

## Prerequistes
The following are necessary to successfully run the program. 
* Java 11
* RabbitMQ version 3.8.2 or higher
* MySQL version 8 or higher
* Maven
* Spring
* Thymeleaf

Download and place the project in your prefered directory.
Run MuddersApplcation and RabbitMQReciever.

## Necessary Configurations
Open project-group-2-complex-java-programming\src\main\resources and create a file called applcation.properties
This file needs to be configured to fit your need, an example of what a working applciation.properties file could look like down below.
Make sure to create a database with the name mud.

### DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
Add to application.properties file using your own ports, usernames and passwords.
```
spring.datasource.url=jdbc:mysql://localhost:3306/mud?serverTimezone=UTC&useLegacyDatetimeCode=false
server.port=****
spring.datasource.username=****
spring.datasource.password=****

```
### Hibernate
The SQL dialect makes Hibernate generate better SQL for the chosen database

```
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect

```
### Hibernate ddl auto (create, create-drop, validate, update)
```
spring.jpa.hibernate.ddl-auto=update

```

## Start localhost
Now you can try the program via localhost and a port of your choice.
```
localhost:****/login

```

We automaticly create an account with admin privleges which you can use to try all functions with.
Make sure you log in with: 
 * Enter email: Admin@mud.com  
 * Enter Password: admin
 
The user only has basic priveleges to play the game.

Please enjoy and thanks for trying our program!
