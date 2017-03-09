package me.money.type.mq;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.money.type.utils.TimeStop;

public class TestProducer {
	private static Logger logger = LoggerFactory.getLogger(TestProducer.class);

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
			logger.info("发送消息a：" + message.getText());
			TimeStop.second(1);
		}

		conn.close();
	}

}
