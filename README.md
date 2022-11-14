# ALKEMY JAVA TECHNICAL CHALLENGE - WALLET
![68747470733a2f2f692e696d6775722e636f6d2f767569525271582e706e67](https://user-images.githubusercontent.com/24995631/201564506-0175d8eb-69d5-4333-8672-e6f00a18a170.png)

### Tech Stack

    Java 17

    Maven

    Spring Boot

    Spring Security

    Spring Data JPA

    JWT

    Lombok

    MySQL

### Tools

    IntelliJ IDEA

    Postman

    DBeaver

### API STRUCTURE

MVC API rest
![API-Structure](https://user-images.githubusercontent.com/24995631/201565282-d695dc13-4322-4fd2-b89c-eaf82f882845.png)

### INSTALLATION PROCESS:
Before running the API you must create a new Schema on your local database with the following script:
CREATE SCHEMA skill_up_java;
After launching the API in your local server you must create 2 Roles (ADMIN and USER)in order to create users and admins properly:
In order to create this roles, please submit a POST request to the following endpoint:
http://localhost:8080/role
using this jason format at the body of the request:
{
 "id":1,
 "name":"ADMIN",
 "description": "example"
 }
Once you had created the roles you must be able to create users, admins and use the different endpoints available in the application.

### References

    https://en.wikibooks.org/wiki/Java_Persistence
    https://www.youtube.com/watch?v=pKawc1UtVcQ
    https://www.arquitecturajava.com/jpa-vs-hibernate/
    https://www.youtube.com/watch?v=_M4e-eaYnzU
    https://www.youtube.com/watch?v=RNqFqAGxZoU
    https://www.nestoralmeida.com/cascade-en-jpa-hibernate/
    https://www.coderscampus.com/hibernate-eager-vs-lazy-fetch-type/
    https://www.youtube.com/watch?v=_bImyniwpBQ&t=727s
    https://code.tutsplus.com/es/tutorials/a-beginners-guide-to-http-and-rest--net-16340
    https://es.sensedia.com/post/api-rest-design-7-essential-tips
    https://reflectoring.io/spring-data-specifications/
    https://www.youtube.com/watch?v=GB8k2-Egfv0&list=PLC97BDEFDCDD169D7
    https://www.youtube.com/watch?v=vtPkZShrvXQ
    https://www.youtube.com/watch?v=CfGDZmPj3Qw&list=PLTd5ehIj0goPcnQs34i0F-Kgp5JHX8UUv
    https://www.youtube.com/watch?v=wYy8RNPtChM

### Authors
Group J1 Springfield
- Aldo Ayala
- Gian Ortega Di Pascuales
- Giulana Castellini
- Nicolas Romano Blandini
- Leandro Silva
- Esteban Casile
- Denise Colotta Twerd
