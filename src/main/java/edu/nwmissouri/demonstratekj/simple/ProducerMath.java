package edu.nwmissouri.demonstratekj.simple;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import javax.xml.namespace.QName;

public class ProducerMath {
  static int count=0;
  private static Scanner in;
  static int answer = 0;
  static String another = "";

  public static void main(String[] argv) throws Exception {
    if (argv.length != 1) {
      System.err.println("Please specify 1 parameter (the name of the topic)");
      System.exit(-1);
    }
    String topicName = argv[0];
    in = new Scanner(System.in);
    System.out.println("Thank you for providing the topic " + topicName + "\n");
    System.out.println("Enter message (type exit to quit).\n");

    // Configure the Producer
    Properties configProperties = new Properties();
    configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.ByteArraySerializer");
    configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringSerializer");
    System.out.println("The configuration properties are: " + configProperties.toString());
    System.out.println("\nWill use this configuration to create a producer.\n");

    org.apache.kafka.clients.producer.Producer producer = new KafkaProducer(configProperties);

    // My Custom logic to return length of string
    // allows input from keyboard

    Random rand = new Random();
      int random = rand.nextInt(3);
      String message = "Welcome to the Kafka Mathematical lab.\nAfter each question input a Y if you would like to have another math question.\nPlease answer the following question \n" + createMessage(random);
      ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, message);
      producer.send(rec);
      
      String input = "";
      another = "";
    while (!input.equals("exit")) {
      if(another.equals("y") || another.equals("Y")){
        random = rand.nextInt(3);
        message = createMessage(random);
        rec = new ProducerRecord<String, String>(topicName, message);
        producer.send(rec);
      }
      else if(input != ""){
          return;
      }

      input = in.nextLine();
      ProducerRecord<String, String> rec1 = new ProducerRecord<String, String>(topicName,input);
      producer.send(rec1);

      int usersInput = Integer.parseInt(input);

      if(usersInput == answer){
          input = "You got it right! :) \n\nType Y and click enter for another question\nType exit to quit";
      }
      else {
          input = "You are not correct... :( \n\nType Y and click enter for another question\nType exit to quit";
      }
      another = in.nextLine();
    }

    in.close();
    producer.close();
  }
  
  private static String createMessage(int i) {
    String[] questions = { "What is 2+2?", "What is 12*12?", "What is the square root of 121?" };
    String result= questions[i];
    count++;

    if(i == 0){
        answer = 4;
    }
    else if(i == 1){
        answer = 144;
    }
    else if(i == 2){
        answer = 11;
    }

    return result;
  }

 
}


