package beauty.customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerRegisterDto {
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "First Name is mandatory")
    @Size(min = 2, max = 50, message = "Size should be min 2 symbols and max 50")
    private String firstName;

    @NotBlank(message = "Last Name is mandatory")
    @Size(min = 2, max = 50, message = "Size should be min 2 symbols and max 50")
    private String lastName;

    @NotBlank(message = "password is mandatory")
    private String password;

}
