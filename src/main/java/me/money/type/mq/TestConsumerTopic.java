package me.money.type.mq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import soso.utils.TimePause;

public class TestConsumerTopic implements MessageListener {

	public static void main(String[] args) throws JMSException {
		// TODO Auto-generated method stub
		Connection conn = MqConnection.getConnection();
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

		Destination destination = session.createTopic("test");

		MessageConsumer consumer = session.createConsumer(destination);

		TestConsumerTopic t = new TestConsumerTopic();
		consumer.setMessageListener(t);

		TimePause.pause(10000);
	}

	@Override
	public void onMessage(Message message) {
		TextMessage text = (TextMessage) message;
		try {
			System.out.println("收到消息：" + text.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
