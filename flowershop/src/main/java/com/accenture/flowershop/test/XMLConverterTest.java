package com.accenture.flowershop.test;

import java.io.IOException;

import com.accenture.flowershop.backend.entity.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XMLConverterTest {
    private static final String XML_FILE_NAME = "customer.xml";

    public static void main(String[] args) throws IOException {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationCont.xml");
        com.accenture.flowershop.test.XMLConverter converter = (com.accenture.flowershop.test.XMLConverter) appContext.getBean("XMLConverter");

        Customer customer = new Customer();
        customer.setLogin("NameXML");
        customer.setPassword("343253");
        customer.setPhone("334534645745");
        customer.setAddress("Tver");

        System.out.println("Convert Object to XML!");

        //from object to XML file
        converter.convertFromObjectToXML(customer, XML_FILE_NAME);
        System.out.println("Done \n");

        System.out.println("Convert XML back to Object!");
        //from XML to object
        Customer customer2 = (Customer)converter.convertFromXMLToObject(XML_FILE_NAME);
        System.out.println(customer2);
        System.out.println("Done");
    }
}
