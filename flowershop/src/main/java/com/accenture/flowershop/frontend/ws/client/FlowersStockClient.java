package com.accenture.flowershop.frontend.ws.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FlowersStockClient {

    private static String flowersStockServiceUrl = "http://localhost:8080/ws/FlowersStockWebService?wsdl";

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            try {
                int start = 10;
                int end = 30;
                int randomNumber = start + (int) (Math.random() * end);

                increaseFlowersStock(randomNumber);

                // For example 10 sec :
                // Thread.sleep(10 * 1000L);

                Thread.sleep(10000L * 60);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void increaseFlowersStock(int count) throws IOException {

        String soapXML = increaseForFlowersStockSoapXML(count);
        HttpURLConnection connection = (HttpURLConnection) new URL(flowersStockServiceUrl).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // insert xml in POST
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(soapXML.getBytes());
        outputStream.flush();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String stringBufferedReader;
        while ((stringBufferedReader = bufferedReader.readLine())!= null) {
            System.out.println(stringBufferedReader);
        }
    }

    private static String increaseForFlowersStockSoapXML(int count) {

        String soupXml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                            "xmlns:impl=\"http://Impl.ws.frontend.flowershop.accenture.com/\">\n" +
                            "<soapenv:Header/>\n" +
                                "<soapenv:Body>\n" +
                                    "<impl:increaseFlowersStockSize>\n"+
                                        "<count>" + count + "</count>\n" +
                                    "</impl:increaseFlowersStockSize>\n" +
                                "</soapenv:Body>\n" +
                            "</soapenv:Envelope>\n";
        return soupXml;
    }
}
