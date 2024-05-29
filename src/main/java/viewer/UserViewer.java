package viewer;

import connector.ConnectionMaker;
import connector.MysqlConnectionMaker;
import controller.UserController;
import model.UserDTO;
import util.ScannerUtil;

import java.util.Scanner;

public class UserViewer {
    private final Scanner SCANNER = new Scanner(System.in);
    private UserDTO logIn;
    private ConnectionMaker connectionMaker;

    public UserViewer() {
        connectionMaker = new MysqlConnectionMaker();
    }

    public void showIndex() {
        String message = "1.로그인 2.회원가입 3.종료";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                auth();
                if (logIn != null) {
                    showMenu();
                }
            } else if (userChoice == 2) {
                register();
            } else if (userChoice == 3) {
                System.out.println("사용해 주셔서 감사합니다.");
                break;
            }
        }
    }

    private void auth() {
        String message = "아이디를 입력해주세요";
        String username = ScannerUtil.nextLine(SCANNER, message);

        message = "비밀번호를 입력해주세요";
        String password = ScannerUtil.nextLine(SCANNER, message);

        UserController userController = new UserController(connectionMaker);
        UserDTO temp = userController.auth(username, password);

        if (temp == null) {
            System.out.println("잘못된 정보입니다.");
        } else {
            logIn = temp;
        }
    }


    // 회원가입
    private void register() {
        String message = "사용하실 아이디를 입력해주세요";
        String username = ScannerUtil.nextLine(SCANNER, message);

        message = "사용하실 비밀번호를 입력해주세요";
        String password = ScannerUtil.nextLine(SCANNER, message);

        message = "사용하실 닉네임을 입력해주세요";
        String nickname = ScannerUtil.nextLine(SCANNER, message);


        UserDTO attempt = new UserDTO();
        attempt.setUsername(username);
        attempt.setPassword(password);
        attempt.setNickname(nickname);

        UserController userController = new UserController(connectionMaker);
        if (!userController.register(attempt)) {
            System.out.println("잘못 입력하셨습니다");
        } else {
            System.out.println("정상적으로 회원가입이 되었습니다.");
        }
    }

    // 회원정보 수정
    private void showMenu() {
        String message = "1.게시판으로  2.회원정보확인 3.로그아웃";
        while (logIn != null) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                //
            } else if (userChoice == 2) {
                //delete(id);
            } else if (userChoice == 3) {
                System.out.println("정상적으로 로그아웃이 되셨습니다.");
                logIn = null;
            }
        }
    }

    private void update() {
        String message = "새로운 아이디를 설정해주세요";
        String newUsername = ScannerUtil.nextLine(SCANNER, message);

        message = "새로운 비밀번호를 설정해주세요";
        String newPassword = ScannerUtil.nextLine(SCANNER, message);

        message = "기존의 비밀번호를 입력해주세요";
        String oldPassword = ScannerUtil.nextLine(SCANNER, message);

        if (!(oldPassword == newPassword)) {
            System.out.println("비밀번호가 틀립니다. 다시 확인해주세요");
        } else {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(newUsername);
            userDTO.setPassword(newPassword);

        }
    }

    private void delete(int id) {
        String message = "정말 삭제하시겠습니까? Y/N";
        String answer = ScannerUtil.nextLine(SCANNER, message);

        if (answer.equalsIgnoreCase("y")) {
            System.out.println("=====삭제가 완료되었습니다.======");

        }
    }

}
