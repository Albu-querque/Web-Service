package main.java.webserver;

import javax.jws.WebService;

import static main.java.source_code.strategy.StrategyJDBC.dataFromDB;

@WebService(endpointInterface = "main.java.webserver.WebServer")
public class WebServiceImpl implements WebServer {
    @Override
    public long getId(String string) {
        return Long.parseLong(dataFromDB(string));
    }

    @Override
    public String getString(int id) {
        return dataFromDB(id + "");
    }
}
