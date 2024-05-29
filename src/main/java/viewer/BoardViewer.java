package viewer;

import controller.BoardController;
import lombok.Getter;
import model.BoardDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class BoardViewer {
    private Scanner scanner;
    @Getter
    private BoardController boardController;
    @Getter
    private BoardDTO boardDTO;

    // 보드 전체 선택
    public void showBoard(){
        /* ArrayList<boardDTO> list = boardController.selectAll();
        for(boardDTO b : list){
            System.out.printf("%s. %s", b.getId, b.getTitle);
        }

        String message = "상세보기할 번호나 뒤로가기는 0번을 눌러주세요";
        int userChoice = ScannerUtil.nextInt(scanner, message);
        while (! boardController.validate(userChoice) == 0){

        }*/
    }

    // 보드 하나만 보기

    // 보드 작성

    // 보드 수정

    // 보드 삭제
}
