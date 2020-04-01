package tiv.exchange.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Currency {

    private Map<String, BigDecimal> rates;
    private LocalDate date;
    private String base;

}
