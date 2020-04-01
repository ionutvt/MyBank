package tiv.exchange.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    @JsonFormat(pattern = "dd-MM-YYYY HH:mm")
    private LocalDateTime exceptionDate;
    private String message;
    private String details;

}
