package com.mjv.gamequiz.dtos;

import com.mjv.gamequiz.domains.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {

    private Long id;
    private String nickName;
    private Integer score;
    private String theme;
    private UserDTO user;

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", score=" + score +
                ", theme='" + theme + '\'' +
                ", user=" + user +
                '}';
    }
}
