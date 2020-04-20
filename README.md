SC Coding Challange
================================
The project demonstrates below aspects:
1. Building REST end points using spring MVC
2. REST conventions.
3. Global Error Handling.
6. Spring AOP.
7. Redis to maintain keep count of number of hits
8. Unit testing
9. Integration testing.

PreRequisite
==============
install Redis server and provide redis server and port if you are running redis in diffrent host or port in application.properties
redis.host=127.0.0.1
redis.port=6379

The project Hierarchy
=======================
- advice - contains pointcuts and advices for the method level logging at diffrent tier level.
- validation - check request is avalid request or not.
- interceptor - It has file related to interceptor to calculate hit count
- configuration - contains file related to redis configuration and registration of interceptor for statistics count.
- services - contains list of service class for rhyme words and statistics hit count.
- controllers - contains list of controller for rhyme words and statistics hit count.
- dto - contains classes used for data transfer from Controller to service layer.
- Exception - contains classes for some application specific runtime exception.
- util - contains business logic to find rhyme words.
- test - contains all junit test cases for respective controller and service classes.
- deployment_plan.png - This file contains a overall plan for deployment.

Running project
======================
just run ./mvn clean install to build the project

once ready go to target folder and run the command java -jar rhymeApp-0.0.1-SNAPSHOT.jar

Please find my understanding below.


1> http://localhost:8082/rhymes/all
 POST  api, as per my understanding we are fetching all rhymed words ,which words matched with our sample input file present inside src/main/resources folder.input will be a list of rymed words.
 Ex:-
 
input :- 
 {
  "words": ["fighting", "signing"]
 }
 output :- 
 {
    "fighting": [
        "shooting"
    ],
    "signing": [
        "singing",
        "dancing",
        "shooting"
    ]
}
2> GET -  http://localhost:8082/rhymes/allRyhmeList
 GET  api, as per my understanding we are fetching all existing rhymed words ,which words matched with our sample input file present inside src/main/resources folder same as api1.input will be a list of rhymed words.
 Ex:-
 
input :- 
 {
  "words": ["fighting", "signing"]
 }
 output :- 
 {
    "fighting": [
        "shooting"
    ],
    "signing": [
        "singing",
        "dancing",
        "shooting"
    ]
}


Each time, when  we are hitting rhymeservice,incrementing hit counter by 1 and expire all other existing records,incase those entries are older than 1 min.
We would like to have a RESTful API for our statistics. The main use case for the API is to calculate realtime statistics for the last 60 seconds of transactions.

TAPI needs the following endpoints:

3> GET - http://localhost:8082/statistics/count
returns the request count  statistic based of the transactions of the last 60 seconds
seconds.http://localhost:8082/count

Output:-
{
hit_count: 10
}

