package guru.springframework.sfgjms.listener;

import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloMesssageListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, Message message) {

//        System.out.println("I got a message!");
//
//        System.out.println(helloWorldMessage);

        // debug to see the change of jms_redelivered and JMSXDeliveryCount in headers
        // throw new RuntimeException("foo");
    }

    @JmsListener(destination = JmsConfig.MY_SEND_RCV_QUEUE)
    public void listenForSendAndReceive(@Payload HelloWorldMessage helloWorldMessage,
                               @Headers MessageHeaders headers, Message message,
                                        org.springframework.messaging.Message springMessage) throws JMSException {

        HelloWorldMessage payloadMsg = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("World!!! - by sendAndReceive")
                .build();

        // Approach 1: use JMS message type: javax.jms.Message
        // jmsTemplate.convertAndSend(message.getJMSReplyTo(), payloadMsg);

        // Approach 2: use Spring Message type: org.springframework.messaging.Message
        jmsTemplate.convertAndSend((Destination) springMessage.getHeaders().get("jms_replyTo"), "got it!");
    }
}
