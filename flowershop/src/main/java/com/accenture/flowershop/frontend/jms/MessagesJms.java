package com.accenture.flowershop.frontend.jms;

import com.accenture.flowershop.backend.entity.Customer;
import com.accenture.flowershop.backend.services.Impl.UserBusinessServiceImpl;
import com.accenture.flowershop.backend.services.Impl.UserMarshgallingServiceImp;
import com.accenture.flowershop.frontend.servlets.LoginServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.jms.*;
import java.io.IOException;
import java.sql.SQLOutput;

@Service
public class MessagesJms {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private Queue inQueue;

    @Autowired
    private Queue outQueue;

    @Autowired
    private UserMarshgallingServiceImp userMarshgallingService;

    @Autowired
    private UserBusinessServiceImpl userService;

    private static final Logger LOG = LoggerFactory.getLogger(MessagesJms.class);

    Session session;
    Connection connection;

    @PostConstruct
    public void init() throws JMSException {

        try {
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            MessageConsumer consumer = session.createConsumer(inQueue);
            connection.start();
            consumer.setMessageListener(
                new MessageListener() {
                    public void onMessage(Message message) {
                        try {

                            String replyString = ((TextMessage) message).getText();
                            System.out.println("replyString: " + replyString);

                            LOG.error("Error LOG");

                            Customer customerFromXML = (Customer) userMarshgallingService.convertFromStringXMLToObject(replyString);
                            userService.updateCustomerForJMS(customerFromXML);

                        } catch (JMSException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            );

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void outMessages(String stringXml) throws JMSException {

        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        try {
            connection.start();

            Message message = session.createTextMessage(stringXml);
            MessageProducer producer = session.createProducer(outQueue);
            producer.send(message);

        } catch (JMSException ex) {
            throw ex;
        } finally {
            session.close();
            connection.close();
        }

    }


}
