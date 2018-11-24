package main.java.webserver;


import java.net.URL;
import java.net.MalformedURLException;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;


public class Client {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:1998/ws/WebServer?wsdl");
        QName name = new QName("http://webserver.java.main/", "WebServiceImplService");
        Service service = Service.create(url, name);
        WebServer server = service.getPort(WebServer.class);
        System.out.println(server.getString(3));
        System.out.println(server.getId("Spring MVC"));
    }
}
