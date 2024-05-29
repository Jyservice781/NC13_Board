package controller;

import connector.ConnectionMaker;
import model.BoardDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardController {
    // connection 연결
    private Connection connection;

    public BoardController(ConnectionMaker connectionMaker) {
        connection = connectionMaker.makeConnection();
    }

    // 필요하다면 list 사용 가능
    // 게시글 입력
/*
    public BoardDTO insert(String title, String content){
        String query = "SELECT * FROM title WHERE title = ? AND content = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, content);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                BoardDTO boardDTO = new BoardDTO();

                boardDTO.setTitle(resultSet.getString("title"));
                boardDTO.setContent(resultSet.getString("content"));

                return boardDTO;
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
*/
    // 선생님이랑 같이 코드----------------------
    // selectAll
    public List<BoardDTO> selectAll() {
        List<BoardDTO> list = new ArrayList<>();
        String query = "SELECT * FROM board as b INNER JOIN user ON b.writer_id = user.id";
        // 같은 컬럼이 나왔을 경우 첫번째 나온 컬럼이 우선시 되기 때문에 INNER JOIN 으로 인한 중복을 걱정하지 않아도 된다.

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BoardDTO boardDTO = new BoardDTO();
                boardDTO.setId(resultSet.getInt("id"));
                boardDTO.setTitle(resultSet.getString("title"));
                boardDTO.setContent(resultSet.getString("content"));
                boardDTO.setEntryDate(resultSet.getTimestamp("entry_date"));
                boardDTO.setModifyDate(resultSet.getTimestamp("modify_date"));
                boardDTO.setWriterId(resultSet.getInt("writer_id"));
                boardDTO.setTitle(resultSet.getString("nickname"));

                list.add(boardDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 게시글 수정 - 스스로 함
 /*   public boolean update(BoardDTO boardDTO) {
        String query = "UPDATE board SET title = ?, content = ?, writerId =? WHERE id =? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, boardDTO.getTitle());
            preparedStatement.setString(2, boardDTO.getContent());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }*/

    // 선생님이랑 --------
    // selectOne
    public BoardDTO selectOne(int id) {
        String query = "SELECT * FROM board INNER JOIN user ON board.writer_id = user.id WHERE board.id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BoardDTO boardDTO = new BoardDTO();
                boardDTO.setId(resultSet.getInt("id"));
                boardDTO.setTitle(resultSet.getString("title"));
                boardDTO.setContent(resultSet.getString("content"));
                boardDTO.setEntryDate(resultSet.getTimestamp("entry_date"));
                boardDTO.setModifyDate(resultSet.getTimestamp("modify_date"));
                boardDTO.setWriterId(resultSet.getInt("writer_id"));
                boardDTO.setTitle(resultSet.getString("nickname"));

                return boardDTO;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 게시글 입력
    public void insert(BoardDTO boardDTO) {
        String query = "INSERT INTO board(title, content, writer_id) VALUES(?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, boardDTO.getTitle());
            preparedStatement.setString(2, boardDTO.getContent());
            preparedStatement.setInt(3, boardDTO.getWriterId());

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 게시글 수정
    public void update(BoardDTO boardDTO) {
        String query = "UPDATE board SET title = ?, content =?, modify_date = NOW() WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, boardDTO.getTitle());
            preparedStatement.setString(2, boardDTO.getContent());
            preparedStatement.setInt(3, boardDTO.getId());

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //게시글 삭제
    public void delete(int id) {
        String query = "DELETE FROM board WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*  public boolean validate(){
         if(){
             return true;
         }else{}
         return false;
    }*/
}