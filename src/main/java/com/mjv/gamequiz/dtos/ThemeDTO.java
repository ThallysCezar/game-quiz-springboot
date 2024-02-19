package com.mjv.gamequiz.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThemeDTO {

    private Long id;
    private String theme;

    @Override
    public String toString() {
        return "\n\nThemeDTO:" +
                "\nid = " + id +
                "\ntheme = " + theme;
    }

}