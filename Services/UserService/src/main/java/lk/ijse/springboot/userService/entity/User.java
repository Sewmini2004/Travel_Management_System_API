package lk.ijse.springboot.userService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity

public class User implements Super {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    @NotNull(message = "Username shouldn't be null")
    @Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$" , message ="Invalid username")
    private String username;
    @NotNull(message = "UserNIC shouldn't be null ")
    @Pattern(regexp = "^([0-9]{9}[x|X|v|V]|[0-9]{12})$",message = "Invalid NIC Number")
    private String userNIC;
    @NotNull(message = "Address shouldn't be null ")
    @Pattern(regexp ="^[a-zA-Z0-9\\\\s,.'-]+[a-zA-Z0-9\\\\s,.'-]+[a-zA-Z0-9\\\\s,.'-]+$"
    , message = "Invalid address")
    private String address;
    @NotEmpty(message = "User's NIC Images FrontEnd shouldn't be empty")
    private String userNICImagesFrontEnd;
    @NotEmpty(message = " User's NIC images BackEnd shouldn't be empty")
    private String userNIC_imagesBackEnd;
    @NotEmpty(message = "Gender shouldn't be empty")
    private String gender;
    @Email(message = "Invalid email address")
    @Pattern(regexp ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$" , message = "Invalid Email address")
    private String email;
    @NotNull(message = "Password shouldn't be null")
    private String password;
    @NotNull(message = "Role shouldn't be null")
    @Pattern(message = "Role should be user or admin",
            regexp = "^(user|admin)$")
    private String role;
    @NotEmpty(message = "Remarks shouldn't be empty")
    private String remarks;


}
