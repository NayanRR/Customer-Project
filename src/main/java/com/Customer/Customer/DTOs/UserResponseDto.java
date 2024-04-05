package com.Customer.Customer.DTOs;

import com.Customer.Customer.Models.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class UserResponseDto  extends CommonApiResponse{

    private List<Customer> customers = new ArrayList<>();

}
