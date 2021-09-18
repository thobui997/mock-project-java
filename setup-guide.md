# Setup Guide
-----------------------
## Environment requirement
- JDK 11
- Spring boot framework 2.5.0
- Mysql database
- Maven build tool
## Tool
- IDE: Intellij, Eclipse,...
- Mysql workbench
- Postman
## Connect with database
- Add below code in application.yml file, path `src/main/resources/application.yml` 

```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: ten user thiet lap database
    password: password thiet lap database
    url: jdbc:mysql://localhost:3306/ten-database
```
## Import the dumb data use Mysql Workbench
- Link guide: https://dev.mysql.com/doc/workbench/en/wb-admin-export-import-management.html