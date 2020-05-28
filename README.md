# kafka-java-demonstration
This repository will hold the content for a demonstration video via Vid Grid of a Kafka/Java application for 44517 Big Data. 

### Contributers
- Eli Ross
- Michael Baumli

##### Metadata
- groupId: edu.nwmissouri.demonstratekj
- artifactId: kafka-java-demonstration

### Eli Ross' Project
I am creating a kafka java application of which the producer will ask a small series of math problems and then it is up to the consumer to answer them correctly or not. The producer will be able to reply whether or not the user/consumer got the question correct. 

1. Prerequisites:
    - Running installations:
        - Zookeeper
        - Kafka
        - Java
        - Maven
    - Project Code requirements:
        - Created java files must include
            - Consumer
            - Producer
            - pom.xml

2. Process:
    - Create a maven project within a desirable folder by opening there a PowerShell window and run it as an Administrator
        - Run the command `mvn archetype:generate`
            - You will be asked to enter the following information:
                1. groupId (for example mine is edu.nwmissouri.demonstratekj)
                2. artifactId (for example mine is the same as my project name kafka-java-demonstration)
                3. Select enter for the rest of the steps in order to finalize the project. 
    - Start the ZooKeeper service
        - Open PowerShell as an Administrator from anywhere and perform the command `zkServer`
    - Start the Kafka service
        - Open PowerShell as an Administrator from your respective kafka folder in the `/bin/windows` (my kafka location is `C:\kafka_2.12-2.5.0\bin\windows`)
        - Run the command `.\kafka-server-start.bat .\server.properties`
    - Create a topic with the kafka command `.\bin\windows\kafka-topics.bat --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --create --topic bearcat-messages` where the topic name is bearcat-messages
    - Run the created Consumer and Producer
        - To run the Consumer run the command: `java -cp target/kafka-java-demonstration-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.demonstratekj.simple.Consumer bearcat-messages group1`
            - Note the location of the groupId and artifactId in the command
        - To run the Producer run the command: `java -cp target/kafka-java-demonstration-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.demonstratekj.simple.ProducerMath bearcat-messages` 
