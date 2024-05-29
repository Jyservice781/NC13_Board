import connector.ConnectionMaker;
import connector.MysqlConnectionMaker;
import controller.BoardController;
import controller.ReplyController;
import controller.UserController;
import viewer.UserViewer;

public class BoardMain {
    // 외부에서 연결되는 connection
    public static void main(String[] args) {
        ConnectionMaker connectionMaker = new MysqlConnectionMaker();
        UserViewer userViewer = new UserViewer();
        userViewer.showIndex();
    }
}
