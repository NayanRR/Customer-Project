package com.Customer.Customer.DTOs;

import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCustomerRequest {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;


    @NotNull(message = "Invalid Phone number: Number is NULL")
    @Pattern(regexp = "\\d{10}", message = "Invalid phone number")
    private String mobNo;

    @Email
    @NotNull
    @NotEmpty(message = "Email Id is not valid !!")
    private String emailId;

    @NotNull
    private String street;

    @NotNull
    private String address;

    @NotEmpty
    private String city;

    @NotEmpty
    private String state;

//    @NotEmpty
//    private Role role;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$")
//    Min 1 uppercase letter.
//    Min 1 lowercase letter.
//    Min 1 special character.
//    Min 1 number.
//    Min 8 characters.
    private String password;
}
