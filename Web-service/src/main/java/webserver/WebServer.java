package main.java.webserver;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface WebServer {
    @WebMethod
    long getId(String string);
    @WebMethod
    String getString(int id);
}
