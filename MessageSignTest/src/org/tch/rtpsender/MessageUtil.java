package org.tch.rtpsender;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.ibm.mq.constants.CMQC;
import com.ibm.mq.jms.MQQueue;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.jms.JmsConstants;
import com.ibm.msg.client.wmq.WMQConstants;
import com.ibm.msg.client.wmq.common.CommonConstants;

public class MessageUtil {
	
	
	public void sendMessage(String queueName, String message) throws JMSException {
		
		  MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
		  mqQueueConnectionFactory.setHostName("10.108.38.33");
          mqQueueConnectionFactory.setChannel("SIM.APP.CLNT");//communications link
          mqQueueConnectionFactory.setPort(1414);
          mqQueueConnectionFactory.setQueueManager("QSIMA10");//service provider 
          
          mqQueueConnectionFactory.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);

          
           QueueConnection queueConnection = mqQueueConnectionFactory.createQueueConnection();
           queueConnection.start();
          QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
          
          //Queue queue = queueSession.createQueue("PERFBANK1_MQ1.REQUEST.TO.RTP_MQ1");
          
          
          Queue queue = queueSession.createQueue(queueName);
          
          MQQueue tempMQQueue = (MQQueue)queue;
          tempMQQueue.setTargetClient(CommonConstants.WMQ_CLIENT_NONJMS_MQ);
          tempMQQueue.setCCSID(1208);
          tempMQQueue.setEncoding(546);
          
          tempMQQueue.setIntProperty(WMQConstants.WMQ_MESSAGE_BODY, WMQConstants.WMQ_MESSAGE_BODY_MQ);
          tempMQQueue.setMQMDWriteEnabled(true);
          
          QueueSender queueSender = queueSession.createSender(queue);
        
          
     //     System.out.println("The message number is " + ++messageNumber);
  
  		
      	TextMessage textMessage = queueSession.createTextMessage(message);
  		
    	  Queue expiryDestinationQueue = queueSession.createQueue("DUMMY");
    		
          textMessage.setJMSReplyTo(expiryDestinationQueue);
          textMessage.setJMSType("mcd://xmlns");//message type
          textMessage.setJMSExpiration(10000);//message expiration
          textMessage.setJMSDeliveryMode(DeliveryMode.PERSISTENT); //message delivery mode either persistent or non-persistemnt

          // MQ Expiration Report Setup
          textMessage.setIntProperty(JmsConstants.JMS_IBM_FEEDBACK, CMQC.MQFB_EXPIRATION);          
          textMessage.setIntProperty(JmsConstants.JMS_IBM_REPORT_EXPIRATION, CMQC.MQRO_EXPIRATION_WITH_FULL_DATA);
          textMessage.setIntProperty(JmsConstants.JMS_IBM_REPORT_PASS_MSG_ID, CMQC.MQRO_PASS_MSG_ID);

          queueSender.setTimeToLive(10000);
          
          
          queueSender.send(textMessage);
          
	}

}
