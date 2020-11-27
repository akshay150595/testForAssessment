package com.Testing.Kubernet.TesterProject.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.jms.annotation.JmsListener;

import org.bson.Document;
 
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientURI;

import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

import com.Testing.Kubernet.TesterProject.bean.Operand;

@RestController
@RequestMapping(value ="/")
public class Controller {
	
	@GetMapping("/test")
	public String testMethod() {
		return "Working";
	}
	
	@PostMapping("/add")
	public int add(@RequestBody Operand operands) {
		return operands.getA() + operands.getB();
	}
	
	@PostMapping("/subtract")
	public int subtract(@RequestBody Operand operands) {
		return operands.getA() - operands.getB();
	}
	
	@PostMapping("/multiply")
	public int multiplication(@RequestBody Operand operands) {
		return operands.getA() * operands.getB();
	}
	
	@PostMapping("/division")
	public float division(@RequestBody Operand operands) {
		return (float)operands.getA() / operands.getB();
	}

	@JmsListener(destination = "TestQueue")
	public Boolean getQueueData(String data) {
		System.out.println("Receiver....................");
		try {
		//ActiveMQConnectionFactory connFactory = new ActiveMQConnectionFactory("ssl://b-58d4dab0-84fa-469f-af93-45fe3b465c2d-1.mq.us-east-2.amazonaws.com:61617");
 
       // Specify the master username and password.
     //  connFactory.setUserName("demo");
     //  connFactory.setPassword("DemoQueue_1234$");
        
      // Connection consumerConn = connFactory.createConnection();
      // consumerConn.start();
 
	//	Session consumerSession = consumerConn.createSession(false,Session.AUTO_ACKNOWLEDGE);
 
      // Destination consumerDest = consumerSession.createQueue("TestQueue");
 
     //  MessageConsumer consumer = consumerSession.createConsumer(consumerDest);
 
      // Message consumerMesg = consumer.receive(1000);
 
      // TextMessage consumerTextMesg = (TextMessage) consumerMesg;
       System.out.println("Received Message is : " + data);

	   	    MongoClientURI uri = new MongoClientURI("mongodb://test:test123@3.138.191.183:27017/jEvalTest");

            MongoClient mongoClient = new MongoClient(uri);
 
            // Get database
            // If database doesn't exists, MongoDB will create it for you
            MongoDatabase db = mongoClient.getDatabase("jEvalTest");
 
            // Get collection / table from 'devjavasource'
            // If collection doesn't exists, MongoDB will create it for you
            MongoCollection<Document> collection = db.getCollection("dummy");

			final Document document = new Document();
			document.put("req",data);

			collection.insertOne(document);
 
    //    consumer.close();
    //    consumerSession.close();
    //    consumerConn.close();
	   return true;
		}
		catch(Exception e) {
			System.out.println("Exception..................");
			System.out.println(e);
			return false;

		}
	}

}
