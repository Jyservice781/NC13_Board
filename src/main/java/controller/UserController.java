package controller;

import connector.ConnectionMaker;
import model.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
    private Connection connection;

    public UserController(ConnectionMaker connectionMaker) {
        connection = connectionMaker.makeConnection();
    }


    // 로그인 메소드
    public UserDTO auth(String username, String password) {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                UserDTO userDTO = new UserDTO();

                userDTO.setId(resultSet.getInt("id"));
                userDTO.setUsername(resultSet.getString("username"));
                userDTO.setPassword(resultSet.getString("password"));
                userDTO.setNickname(resultSet.getString("nickname"));

                return userDTO;
            }

        } catch (SQLException e) {
            // SQLException 은 query 의 에러를 잡는다
            // exception 은 모든 에러를 잡는다.
            e.printStackTrace();
        }
        return null;
    }

    // 회원가입
    public boolean register(UserDTO userDTO) {
        String query = "INSERT INTO user(username, password, nickname) VALUES (?,?,?)";

        // username이 중복됨 - > 중복되면 가입되지말아야함.
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userDTO.getUsername());
            preparedStatement.setString(2, userDTO.getPassword());
            preparedStatement.setString(3, userDTO.getNickname());

            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    // 입력된 값 수정
    public boolean update(UserDTO userDTO) {
        String query = "UPDATE user SET password = ?, nickname = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userDTO.getPassword());
            preparedStatement.setString(2, userDTO.getNickname());
            preparedStatement.setInt(3, userDTO.getId());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 입력된 값 삭제
    public void delete(int id) {
        String query = "DELETE FROM user WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}