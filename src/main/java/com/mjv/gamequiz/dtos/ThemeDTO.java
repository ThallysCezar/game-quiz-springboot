package com.mjv.gamequiz.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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