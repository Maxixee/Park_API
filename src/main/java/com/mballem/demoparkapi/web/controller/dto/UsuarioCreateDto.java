package com.mballem.demoparkapi.web.controller.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UsuarioCreateDto {

    private String username;
    private String password;
}
