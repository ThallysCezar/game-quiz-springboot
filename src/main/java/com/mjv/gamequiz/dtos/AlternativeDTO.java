package com.mjv.gamequiz.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlternativeDTO {

    private Long id;
    private String alternative;
    private String content;

    @Override
    public String toString() {
        return "Alternative: " +
                "---> id= " + id +
                "---> Content= " + content +
                "---> Alternative= " + alternative;
    }

}