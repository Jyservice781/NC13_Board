package connector;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConnectionMaker implements ConnectionMaker {
    // 다형성을 부여해서 connection maker 를 상속받는 아이를 따로 만들어서 사용하면 수정에 용이
    // 만약 오라클로 DBMS 가 바뀐다 하더라도 데이터 재사용이 가능함.
    // 여러개의 클래스를 다 고쳐줄 필요없이 DBMS 별 connectionMaker 를 만들어서 사용하면 수정에 용이하다

    private final String URL = "jdbc:mysql://localhost:3306/board";
    private final String USERNAME = "root";
    private final String PASSWORD = "1234";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    @Override
    public Connection makeConnection() {
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            return connection;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
