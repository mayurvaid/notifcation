# Generic Notification System

Create a generic library for notification.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to run the libraray

```
JDK8
```
```
Gradle >4.0 Version
```
```
kafka 
```
```
Cassadra >2.1 Version
```
```
FakeSTMP Server
```
### Architecture
![Alt text](notification.png?raw=true "Optional Title")

### Running the code

A step by step series of examples that tell you how to get a code running

* Start Cassandra on localhost with port 9042 
* Start Zookeeper on localhost with port 2181 
* Then start kafka server
* Start FAKESMTP server
* ./gradler clean build from the root folder
* Run NotificationApplication.java

## Testing
* We are using spring boot starter testing framework

## Deployment
We can use docker to deploy and migrate the code in higher environment.
* Gradle clean build
* docker build -f Dockerfile -t docker-spring-notification
* docker run -p 10000:10000 docker-spring-notification
* This will start on port 10000

## Built With

* [Gradle](https://gradle.org/) - Build tool
* [Spring](https://spring.io/) - Farmework
