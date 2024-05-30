package viewer;

import connector.ConnectionMaker;
import controller.BoardController;
import model.BoardDTO;
import model.UserDTO;
import util.ScannerUtil;

import java.util.Scanner;

public class BoardViewer {

    private final Scanner SCANNER;
    private ConnectionMaker connectionMaker;
    private UserDTO logIn;

    // userViewer 에 있는 connectionMaker 의 값을 boardViewer 에서도 사용하기 위해서 설정함.
    // 매개값으로 logIn, scanner, connectionMaker 를 받아옴.
    public BoardViewer(ConnectionMaker connectionMaker, Scanner scanner, UserDTO logIn) {
        // 상수들에 매개값을 넣어줌.
        this.connectionMaker = connectionMaker;
        SCANNER = scanner;
        this.logIn = logIn;
    }

    public void showMenu() {
        String message = "1. 글 작성하기 2. 글 목록보기 3. 뒤로가기";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                insert();
            } else if (userChoice == 2) {
                printList();
            } else if (userChoice == 3) {
                System.out.println("뒤로갑니다.");
                break;
            }
        }
    }

    private void insert() {
        // 제목, 내용,
        String message = "제목을 입력해주세요";
        String title = ScannerUtil.nextLine(SCANNER, message);

        message = "내용을 입력해주세요";
        String content = ScannerUtil.nextLine(SCANNER, message);

        message = "별명을 입력해주세요";
        String nickname = ScannerUtil.nextLine(SCANNER, message);

        BoardDTO temp = new BoardDTO();
        temp.setTitle(title);
        temp.setContent(content);
        temp.setNickname(nickname);

        BoardController boardController = new BoardController(connectionMaker);
        boardController.insert(temp);
        System.out.println("정상적으로 입력이 완료되었습니다.");
    }

    // 리스트 출력
    private void printList() {
        // 리스트 반복해서 돌리고 출력
        // controller 에 selectAll 사용해서 돌리기
        BoardController boardController = new BoardController(connectionMaker);
        boardController.selectAll();
        if (boardController.selectAll().isEmpty()) {
            System.out.println("입력한 값이 없습니다.");
        } else {
            for (int i = 0; i < 4; i++) {
                System.out.println("=================================");
                // 여기에 리스트 출력할 sout 하기
                System.out.println("=================================");
            }
        }
        String message = "1. 수정 2. 삭제 3. 뒤로가기";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                update();
            } else if (userChoice == 2) {
                delete();
            } else if (userChoice == 3) {
                showMenu();
                break;
            }
        }
    }

    private void update() {
        String message = "새로운 제목을 입력해주세요";
        String newTitle = ScannerUtil.nextLine(SCANNER, message);

        message = "새로운 내용을 입력해주세요";
        String newContent = ScannerUtil.nextLine(SCANNER, message);

        message = "새로운 별명을 입력해주세요";
        String newNickname = ScannerUtil.nextLine(SCANNER, message);

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle(newTitle);
        boardDTO.setTitle(newContent);
        boardDTO.setTitle(newNickname);

        BoardController boardController = new BoardController(connectionMaker);
        boardController.update(boardDTO);
        System.out.println("---------수정이 완료되었습니다-----------------");
    }

    private void delete() {
        String message = "정말로 삭제 하시겠습니까? ( Y / N )";
        String answer = ScannerUtil.nextLine(SCANNER, message);
        if (answer.equalsIgnoreCase("y")) {
            // 보드 컨트롤러에 있는 delete 를 받아옴.
            BoardController boardController = new BoardController(connectionMaker);
            boardController.delete(logIn.getId());//매개변수 설정 어떻게 할지 생각해보기
        } else if (answer.equalsIgnoreCase("N")) {
            System.out.println("--------------뒤로 돌아갑니다--------------");
            printList();
        }
    }
}
