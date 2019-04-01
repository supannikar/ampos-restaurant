---
Developer: Supannikar Nontarak
Stack: Java
Framework: SpringBoot
---

AMPOS Restaurant API
=========

RESTFul API for manage bill order and menu. It's including:

**Menu API**
**Bill Order API**

Authorization for swagger:
--------------------------------
username: demo
password: 1234

Database setup
--------------------------------
This API we are using MongoBd as database and also the database setting it's contain in application.properties file.

----------
Architecture Setup
--------------------------------
**Prerequisite**
- java (require JDK version 8)
- maven (require version 3)
- git

**Step for running project**

1. Clone project from repository: https://github.com/supannikar/ampos-restaurant.git

2. Build project: gradle clean build
3. Run project: gradle bootRun

The API will be run on port 8080: http://localhost:8080/swagger-ui.html

 - Menu API
   - http://localhost:8080/api/menu

 - Bill Order API
   - http://localhost:8092/api/order

And also we've implement RestFul API documentation. It will be run on this link:
http://localhost:8080/v2/api-docs
