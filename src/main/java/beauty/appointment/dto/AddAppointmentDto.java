package beauty.appointment.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
public class AddAppointmentDto {

    Long saloonId;
    Long serviceId;
    Long customerId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Future
    @NotNull
    LocalDateTime time;

}
