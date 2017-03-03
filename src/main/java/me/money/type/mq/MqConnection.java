package me.money.type.mq;

import javax.jms.Connection;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MqConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static Connection getConnection() throws JMSException {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://127.0.0.1:61616");
		Connection conn = factory.createTopicConnection();
		conn.start();
		return conn;

	}

}
