package connector;

import java.sql.Connection;

public interface ConnectionMaker {
     // connection 을 만듦
    public Connection makeConnection();
}
