# ALKEMY JAVA TECHNICAL CHALLENGE - WALLET

### PROJECT SETUP & TOOLS
1. JDK 17
2. [MySQL](https://dev.mysql.com/doc/refman/8.0/en/installing.html).
3. [Postman](https://www.postman.com/downloads/) for testing endpoints.

### CODE STANDARDS
- Keep in mind rules from [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html).
- Code must be in English.
- The controllers should finish with suffix "Controller". Example: UserController.
- The services should finish with suffix "Service". Example: UserService.
- The repositories should finish with suffix "Repository". Example: UserRepository.
- The interfaces should start with prefix "I". Example: IUserRepository.
- The implementations should finish with suffix "Impl". Example: UserServiceImpl.
- The DTOs should finish with suffix "Dto". Example: UserDto, UserRequestDto.
- Usage of DTOs is a must. Can have DTOs for request and response.
- Package names are in singular.
- The names of attributes/fields from Java classes must be written using camel case. Example: firstName.
- The name of columns in the entities must be written using underscore and uppercase. Example: FIRST_NAME. The name of the tables is always in plural, but the entity name should be in singular.
- Exceptions should be handled by an implementation of ControllerAdvice. 
- Messages to user can't be hardcoded them should be handled. Some refs [here](https://looksok.wordpress.com/2014/07/05/string-externalization-in-spring-3-1-with-messagesource-no-web-xml/) and [here](https://zetcode.com/spring/messagesource/). 
- If you add a new endpoint, make sure to set the role access for it in the SecurityConfig class.


You will find an example of how to work with the project architecture in `architecture-example` branch.

### GIT STANDARDS

#### FORMAT
- Always create the branch from develop
- The branch name format is: `feature/{jiraTicket#}`.
- The pull request title format is: `{jiraTicket#}: {jiraTitle}`.
- The commits format is: `{jiraTicket#}: {commitDescription}`. Small commits are a nice to have.
- The pull request has to contain only the changes related to the scope defined in the ticket.
- Pull request should always be from your current branch to develop.

#### EVIDENCE
- If you do not write unit test or integration test as part of your code changes, you should add the HTTP request and response as evidence that the code is working as expected.
- Screenshots from Postman with different scenarios are a good way to show your work.

#### BRANCHES
In the current repository you will see three diferent branches
- `master` -> this branch is only for productive versions, it has official release history.
- `develop` -> this branch serves as an integration branch for features. All features must start from this branch and after it's finished it gets merged back into develop.
- `architecture-example` -> in this branch you will find an example of a suggested architecture. You can take it as a reference but you should not modify it. 

For understanding more about git and how to work with different branches, I recommend to read about Gitflow workflow. [Here](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow) you have a little explanation that can serve as introduction.

### RUN LOCALLY
On the root folder run:
```
mvn spring-boot:run
```
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
