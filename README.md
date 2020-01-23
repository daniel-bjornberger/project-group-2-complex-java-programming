# project-group-2-complex-java-programming
Project in the course Complex Java-programming, ITHS. A MUD game using SpringBoot.
Created by Daniel Björnberg, Filip Christofferson, Tonny Frisk and Elin Sexton

# Tools needed
Download and place the project in your prefered directory.
make sure you have Java 11, RabbitMQ version 3.8.2 or higher and MySQL version 8 or higher installed on your computer.
Run MuddersApplcation and RabbitMQReciever.

# Necessary Configurations
Open project-group-2-complex-java-programming\src\main\resources and create a file called applcation.properties
This file needs to be configured to fit your need, an example of what a working applciation.properties file could look like down below.
Make sure to create a database with the name mud.

# DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
spring.datasource.url=jdbc:mysql://localhost:3306/mud?serverTimezone=UTC&useLegacyDatetimeCode=false
server.port=8093
spring.datasource.username=root
spring.datasource.password=admin
# Hibernate
# The SQL dialect makes Hibernate generate better SQL for the chosen database
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto=update

# Start localhost
Now you can try the program via localhost and a port of your choice.
We automaticly create an account with admin privleges which you can use to try all functions with.
Make sure you log in with: Admin@mud.com / admin
while the user only has basic priveleges to play the game.

Please enjoy and thanks for trying our program!
