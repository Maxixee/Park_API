package com.mballem.demoparkapi.util;

import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

    public static BigDecimal calcularCusto(LocalDateTime entrada, LocalDateTime saida) {
        long minutes = entrada.until(saida, ChronoUnit.MINUTES);
        double total = 0.0;

        if (minutes <= 15) {
            // preço até 15 minutos
            total = 5.00;
        } else if (minutes <= 60) {
            // preço até 1 hora
            total = 9.25;
        } else {
            // preço acima de 1 hora
            long minAdicionais = minutes - 60;
            int faixas = (int) Math.ceil((double) minAdicionais / 15);
            total = 9.25 + (faixas * 1.75);
        }

        return new BigDecimal(total).setScale(2, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal calcularDesconto(BigDecimal custo, long numeroDeVezes) {
        BigDecimal desconto = BigDecimal.ZERO;

        // Verifica se o cliente tem direito ao desconto
        if (numeroDeVezes> 0 && numeroDeVezes % 10 == 0) {
            // Calcula o desconto
            desconto = custo.multiply(BigDecimal.valueOf(0.3));
        }

        return desconto.setScale(2, RoundingMode.HALF_EVEN);
    }
}

