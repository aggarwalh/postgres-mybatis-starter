# SpringBoot-MyBatis-Postgres Tester Project

## License
  - Apache License, Version 2.0
  - TODO: Add license-maven-plugin (See [this](https://github.com/mybatis/parent/blob/master/pom.xml) and [this](https://github.com/mybatis/mybatis-3/blob/master/license.txt))

## Primer 

### Spring Boot

 TODO: Refer spring boot primer notes + User best practices like using profiles.
 
### MyBatis

 TODO: Refer mybatis primer notes + Best practices like leveraging dynamic sql features, configuring BATCH ExecutorType for bulk interts/updates, use of `@Flush`, minimizing object creation in case of complex objects with same sub-objects objects by using basic and complex objects. 
 
### Postgres

 [Postgres Primer](docs/postgresql.md)
 
## Setup

  - **Create a basic project** using Spring Initializr (i.e. start.spring.io)
       - Add Postgres and Mybatis as required dependencies and generate a starter project.
       - Only dependency I had to add later was javax.inject for using jsr-330 annotations instead of springs.
  -  **Configure Datasource** [official doc](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html)
     - Configuration with third party DataSource in classpath
         - Standard Options (Essentials): What db-server, db and Auth credentials 
            - `spring.datasource.{url,username,password}`
            - For this tester project I have done just this. Everything else left to defaults
            - Don't provide even these then spring-boot's default in memory db is configured.
         - DataSource type:
            - Autoconfiguration. By default it chooses ones already available in classpath in following order of preference
               - Tomcat (org.apache.tomcat.pool.DataSource) for it's performance and concurrency. Recommended one.
               - Followed by HikariCP, Commons DBCP, Commons DBCP2
            - Enforce: You can enforce the type by providing `spring.datasource.type`
         - Fine tuned configurations
            - For major third party sources, there are existing configurations endpoints available
               - Ex: `spring.datasource.tomcat.*`
     - **Custom Configuration** of your own impl of DataSource or more than one datasource in project
          - [Ref](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-data-access.html#howto-configure-a-datasource)
  - **Add bootstrap querries** and refer them with classpath using `spring.datasource.schema`
  - **Configure Mybatis** [spring-boot-mybatis doc](http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)
    - Spring boot auto detects `DataSource` and creates an instance `SqlSessionFactoryBean` (which implements `SqlSessionFactory`) and 
    `SqlSessionTemplate` (which implements `SqlSession`. This is the primary Java interface for working with MyBatis i.e. accessing mappers, 
    executing commands and managing transactions). It also auto detects mappers with `@Mapper` annotations.
      - In this starter project everything is left to defaults 
      - Possible customizations 
        - Enforce the ExecutorType using `mybatis.executor-type`
        - Narrow down scan for Mappers by providing their namespace using `@MapperScan`
        - Provide your own bean of `SqlSessionFactoryBean` and/or `SqlSessionTemplate`.
      - Mybatis configurations become spring boot configurations
        - Mybatis configurations mentioned [here](http://www.mybatis.org/mybatis-3/configuration.html#settings) can be configured by adding mybatis related prefix to them and placing in application.properties file.
      - Configure log levels using namespace.
        - Ex: `logging.level.sample.mybatis.mapper=DEBUG`
        
  - **Logging** 
      - [SpringBoot logging doc](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-logging.html)

### Gotchas/Tricks

   1. When connected to existing production applications you would like NOT to have the schema.sql file for initial setup.
   But if you remove it. The system somehow deletes existing data from tables which are touched in application runtime.
   In such cases it's better to have a vanilla spring.datasource.schema file which does nothing.
