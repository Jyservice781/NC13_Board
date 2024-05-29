package model;

import lombok.Data;

import java.util.Date;

@Data
public class BoardDTO {
    private int id;
    private String title;
    private String content;
    private Date entryDate;
    private Date modifyDate;
    private int writerId;
    private String nickname;
    // INNER JOIN 을 사용하여 다른 테이블의 값을 가져옴
    // 테이블과 완벽히 일치하지 않지만 쿼리상으로 뽑아올 수도  있음.
}
