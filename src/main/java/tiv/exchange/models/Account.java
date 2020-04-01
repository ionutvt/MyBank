package tiv.exchange.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    private Long accountId;
    private String iban;
    private String currency;
    private BigDecimal balance;
    @JsonFormat(pattern = "dd-MM-YYYY HH:mm")
    private LocalDateTime lastUpdate;

}
