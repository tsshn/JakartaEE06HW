package com.yatsyshyn.JakartaEE06HW.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class FilterDto {
    private final String property;
    private final String input;
}
