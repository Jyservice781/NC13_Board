package model;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String nickname;
    // 데이터베이스가 외부에 있기 때문에
    // equals 의 재정의가 필요하지 않음.
    // 유효성 체크는 데이터베이스에서 관리를 함.
}
