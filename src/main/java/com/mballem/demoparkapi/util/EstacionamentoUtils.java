package com.mballem.demoparkapi.util;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EstacionamentoUtils {

    // 2024-03-19T15:23:48.616463500 padrao localdatetime
    // 20240319-152348 formato do meu recibo
    public static String gerarRecibo(){
        LocalDateTime date = LocalDateTime.now();
        String recibo = date.toString().substring(0, 19);

        return recibo.replace("-", "")
                .replace(":", "")
                .replace("T", "-");
    }
}
