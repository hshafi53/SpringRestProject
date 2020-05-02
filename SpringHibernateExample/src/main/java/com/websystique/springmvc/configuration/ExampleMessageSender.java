package com.websystique.springmvc.configuration;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.websystique.springmvc.model.Transaction;

public class ExampleMessageSender {
	private final MessageProducer producer;
	private final Session session;
	private final Connection con;

	public ExampleMessageSender() throws JMSException {
		ConnectionFactory factory = JmsProvider.getConnectionFactory();
		this.con = factory.createConnection();
		con.start();

		this.session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("example.queue");
		this.producer = session.createProducer(queue);
	}

	public void sendMessage(Transaction transaction) throws JMSException {
		System.out.printf("Sending message: %s, Thread:%s%n", transaction, Thread.currentThread().getName());
		ObjectMessage textMessage = session.createObjectMessage((Serializable) transaction);
		producer.send(textMessage);
	}

	public void destroy() throws JMSException {
		con.close();
	}
}