package org.tch.rtpsender;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.jms.JMSException;


public class MessageSenderMain {
	


	public static void main(String[] args) throws JMSException {
		
		 int uniqueMessagePrefix = 0;
	XMLMessageUtil xmlMessageUtil;
        Date now = new Date(System.currentTimeMillis());
       SimpleDateFormat dateFormatter = new SimpleDateFormat("HHmmss");
       
       String tempUniqueNumber = dateFormatter.format(now);
       if (tempUniqueNumber.matches("\\d+"))
       {
       	
       	
       	uniqueMessagePrefix = Integer.parseInt(tempUniqueNumber);
       }
       else
       {
       	System.out.println("Unique *number* is not a parseable number: " + tempUniqueNumber + " - Exiting NOW.");
       	System.exit(-1);
       }
       
       System.out.println("Unique message ID: " + uniqueMessagePrefix);
       
      String  uniqueMessagePrefixString  = "" + uniqueMessagePrefix; 
      
  	xmlMessageUtil = new XMLMessageUtil("000000022PF", "000000022", uniqueMessagePrefixString);

  	
	String message = "";
		
		message = xmlMessageUtil.createSignOnRequestMessage(new Random().nextInt(1000), true);
		
         MessageUtil messageUtil = new MessageUtil();
         
      System.out.println("Sending Sign On Message......." + message);
    
 messageUtil.sendMessage("PERFBANK2_MQ1.REQUEST.TO.RTP_MQ1", message);
    
    
    //now sign off 
    
//    XMLMessageUtil xmlMessageUtil2 = new XMLMessageUtil("000000022PF", "000000022", uniqueMessagePrefixString);
//
//  	
//    
//    String signOffMessge = xmlMessageUtil2.createSignOffRequestMessage(new Random().nextInt(1000), true);
//    
//    System.out.println("\n\n\nNow sending sign off message ............." + signOffMessge);
//    
//    messageUtil.sendMessage("PERFBANK2_MQ1.REQUEST.TO.RTP_MQ1", signOffMessge);

    

	}

}
