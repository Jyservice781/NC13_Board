package controller;

import connector.ConnectionMaker;
import model.ReplyDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReplyController {
    // 생성자
    private Connection connection;

    public ReplyController(ConnectionMaker connectionMaker) {
        connection = connectionMaker.makeConnection();
    }
    // 몇 번 게시글의 댓글인지 중요함.

    //전체댓글 선택
    public List<ReplyDTO> selectAll(int boardId) {
        List<ReplyDTO> list = new ArrayList<>();
        String query = "SELECT * FROM reply INNER JOIN user ON reply.writer_id = user.id WHERE board_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, boardId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ReplyDTO replyDTO = new ReplyDTO();

                replyDTO.setId(resultSet.getInt("id"));
                replyDTO.setContent(resultSet.getString("content"));
                replyDTO.setEntryDate(resultSet.getTimestamp("entry_date"));
                replyDTO.setModifyDate(resultSet.getTimestamp("modify_date"));
                replyDTO.setWriterId(resultSet.getInt("writer_id"));
                replyDTO.setBoardId(resultSet.getInt("board_id"));
                replyDTO.setNickname(resultSet.getString("nickname"));

                list.add(replyDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //삽입
    public void insert(ReplyDTO replyDTO) {
        String query = "INSERT INTO reply(content, writer_id, board_id) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, replyDTO.getContent());
            preparedStatement.setInt(2, replyDTO.getWriterId());
            preparedStatement.setInt(3, replyDTO.getBoardId());

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //수정
    public void update(ReplyDTO replyDTO){
        String query = "UPDATE reply SET content =?, modify_date = NOW() WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, replyDTO.getContent());
            preparedStatement.setInt(2, replyDTO.getId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //삭제
    public void delete(int id){
        String query = "DELETE FROM reply WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 리플라이 삽입
/*    public ReplyDTO insert(int writerId, int boardId, String content) {
        String query = "SELECT * FROM user WHERE writerId = ?, boardId = ? AND content = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, writerId);
            preparedStatement.setInt(2, boardId);
            preparedStatement.setString(3, content);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ReplyDTO replyDTO = new ReplyDTO();

                replyDTO.setId(resultSet.getInt("id"));
                replyDTO.setWriterId(resultSet.getInt("writerId"));
                replyDTO.setBoardId(resultSet.getInt("boardId"));
                replyDTO.setContent(resultSet.getString("content"));

                return replyDTO;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }*/

    // 리플라이 수정
  /*  public boolean update(ReplyDTO replyDTO) {
        String query = "UPDATE reply SET writerId = ?, boardId = ?, content =? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, replyDTO.getWriterId());
            preparedStatement.setInt(2, replyDTO.getBoardId());
            preparedStatement.setString(3, replyDTO.getContent());
            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
*/
    // 리플라이 삭제
/*    public void delete(int id) {
        String query = "DELETE FROM reply WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
