package com.Testing.Kubernet.TesterProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import javax.jms.Queue;

@SpringBootApplication
@EnableJms
public class TesterProjectApplication {

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("TestQueue");
	}

	public static void main(String[] args) {
		SpringApplication.run(TesterProjectApplication.class, args);
		System.out.println("Alpha on mark");
	}


}
