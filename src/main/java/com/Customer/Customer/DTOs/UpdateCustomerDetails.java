package com.Customer.Customer.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCustomerDetails {

    private Integer userId;

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

}
