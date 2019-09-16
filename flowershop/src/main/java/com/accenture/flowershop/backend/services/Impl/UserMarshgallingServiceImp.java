package com.accenture.flowershop.backend.services.Impl;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;


public class UserMarshgallingServiceImp {

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;
    private String path;

    public UserMarshgallingServiceImp() {
    }

    public void convertFromObjectToXML(Object object, String filepath)
            throws IOException {

        FileOutputStream os = null;
        try {
            os = new FileOutputStream(filepath);
            getMarshaller().marshal(object, new StreamResult(os));
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }

    public String convertFromObjectToString(Object object) throws IOException {

            StringWriter sw = new StringWriter();
        try {
            getMarshaller().marshal(object, new StreamResult(sw));
            return sw.toString();
        } finally {
            if (sw != null) {
                sw.close();
            }
        }
    }

    public Object convertFromXMLToObject(String xmlfile) throws IOException {

        FileInputStream is = null;
        try {
            is = new FileInputStream(xmlfile);
            return getUnmarshaller().unmarshal(new StreamSource(is));
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public Object convertFromStringXMLToObject(String stringXml) throws IOException {
        StringReader sr  = new StringReader(stringXml);
        return getUnmarshaller().unmarshal(new StreamSource(sr));
    }

    public Marshaller getMarshaller() {
        return marshaller;
    }

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
