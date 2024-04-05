package com.Customer.Customer.Controller;

import com.Customer.Customer.DTOs.*;
import com.Customer.Customer.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
@RestController
@RequestMapping("/Customer")
@Slf4j
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/addCustomer")
    @Operation(summary = "Api to add Customer")
    public ResponseEntity<CommonApiResponse> addCustomer(@Valid @RequestBody AddCustomerRequest addCustomerRequest){

        return customerService.addCustomer(addCustomerRequest);

    }

    @GetMapping("/fetch")
    @Operation(summary = "Api to get User by using user id")
    public ResponseEntity<UserResponseDto> fetchCustomerById(@RequestParam(value = "customerId") int custId) {


            log.info("We have request : {}",custId);
            try{
                return customerService.fetchCustomerById(custId);
            } catch (Exception e) {
                UserResponseDto Response=new UserResponseDto();
                Response.setResponseMessage("User not found");
                Response.setSuccess(false);
                return new ResponseEntity<UserResponseDto>(Response, HttpStatus.OK);
            }


    }

    @GetMapping("/fetchAllCustomer")
    @Operation(summary = "Api to get User by using user id")
    public ResponseEntity<postResponse> fetchAllCustomer(@RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
                                                         @RequestParam(value = "pageSize",defaultValue = "5",required = false) int pageSize,
                                                         @RequestParam(value = "sortBy",defaultValue = "customerId",required = false) String sortBy
    )
    {
            return customerService.fetchAllCustomer(pageNumber,pageSize,sortBy);
    }

    @DeleteMapping("/deleteCustomer")
    @Operation(summary = "Api to delete the Customer")
    public ResponseEntity<CommonApiResponse> deleteCustomer(@RequestParam(value = "CustId") int custId) {
        try{
            return customerService.deleteCustomer(custId);
        }
        catch(Exception e){
            CommonApiResponse Response=new CommonApiResponse();
            Response.setResponseMessage("User not found");
            Response.setSuccess(false);
            return new ResponseEntity<CommonApiResponse>(Response, HttpStatus.OK);
        }
    }

    @PutMapping("/updateCustomer")
    @Operation(summary = "Api to Update the Customer")
    public ResponseEntity<CommonApiResponse> UpdateCustomer(@Valid @RequestBody UpdateCustomerDetails updateCustomerDetails) {
        try{
            return customerService.updateCustomer(updateCustomerDetails);
        }
        catch(Exception e){
            CommonApiResponse Response=new CommonApiResponse();
            Response.setResponseMessage("User not found");
            Response.setSuccess(false);
            return new ResponseEntity<CommonApiResponse>(Response, HttpStatus.OK);
        }
    }


}
