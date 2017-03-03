package me.money.type.mq;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import me.money.type.utils.TimePause;

public class TestProducer {

	public static void main(String[] args) throws JMSException {
		// TODO Auto-generated method stub
		Connection conn = MqConnection.getConnection();

		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("test");
		MessageProducer producer = session.createProducer(topic);
		for (int i = 0; i < 1000; i++) {
			TextMessage message = session.createTextMessage("消息" + i);
			message.setStringProperty("JMSXGroupID", "niu-test");
			producer.send(message);
			System.out.println("发送消息a："+message.getText());
			TimePause.pause(1);
		}

		conn.close();
	}

}
