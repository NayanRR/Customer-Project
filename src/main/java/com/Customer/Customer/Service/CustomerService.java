package com.Customer.Customer.Service;

import com.Customer.Customer.DTOs.*;
import com.Customer.Customer.Models.Customer;
import com.Customer.Customer.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    private final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    public ResponseEntity<CommonApiResponse> addCustomer(AddCustomerRequest addCustomerRequest){

        LOG.info("Request received for Adding Customer");

        CommonApiResponse Response=new CommonApiResponse();

        if (addCustomerRequest == null) {
            Response.setResponseMessage("user is null");
            Response.setSuccess(false);

            return new ResponseEntity<CommonApiResponse>(Response, HttpStatus.BAD_REQUEST);
        }



        //password pending;
        Customer customer=new Customer().builder()
                            .firstName(addCustomerRequest.getFirstName())
                            .lastName(addCustomerRequest.getLastName())
                            .state(addCustomerRequest.getState())
                            .street(addCustomerRequest.getStreet())
                            .address(addCustomerRequest.getAddress())
                            .email(addCustomerRequest.getEmailId())
                            .phoneNo(addCustomerRequest.getMobNo())
                            .city(addCustomerRequest.getCity())
                            .state(addCustomerRequest.getState())
                            .build();

        customerRepo.save(customer);
        Response.setResponseMessage("Customer Added Successfully");
        Response.setSuccess(true);

        LOG.info("Response Sent!!!");

        return new ResponseEntity<CommonApiResponse>(Response, HttpStatus.OK);

    }

    public ResponseEntity<UserResponseDto> fetchCustomerById(int custId) throws Exception {

        LOG.info("Request received for Searching Customer By Id");

        UserResponseDto Response=new UserResponseDto();

        if (custId == 0) {
            Response.setResponseMessage("user id is missing");
            Response.setSuccess(false);
            return new ResponseEntity<UserResponseDto>(Response, HttpStatus.BAD_REQUEST);
        }

        Customer optionalCustomer=customerRepo.findById(custId).get();
        LOG.info("We have request : {}",optionalCustomer.toString());


        if(optionalCustomer==null){
            throw new Exception("User not Found");

        }
            Customer customer=optionalCustomer;
            Response.setCustomers(Arrays.asList(customer));
            Response.setResponseMessage("Customer Fetched Successfully");
            Response.setSuccess(true);
            return new ResponseEntity<UserResponseDto>(Response, HttpStatus.OK);


    }

    public ResponseEntity<CommonApiResponse> deleteCustomer(int custId) throws Exception {

        LOG.info("Request received for Deleting Customer By Id");

        CommonApiResponse Response = new CommonApiResponse();

        if (custId == 0) {
            Response.setResponseMessage("Customer id is missing");
            Response.setSuccess(false);
            return new ResponseEntity<CommonApiResponse>(Response, HttpStatus.BAD_REQUEST);
        }

        Optional<Customer> optionalCustomer=customerRepo.findById(custId);

        if(!optionalCustomer.isPresent()){
            throw new Exception("User not Found");

        }
        Customer customer=optionalCustomer.get();
        customerRepo.deleteById(custId);
        Response.setResponseMessage("User Deleted Successfully");
        Response.setSuccess(true);

        return new ResponseEntity<CommonApiResponse>(Response, HttpStatus.OK);

    }

    public ResponseEntity<CommonApiResponse> updateCustomer(UpdateCustomerDetails updateCustomerDetails) {

        LOG.info("Request received for updating Customer ");

        CommonApiResponse Response = new CommonApiResponse();

        if (updateCustomerDetails == null || updateCustomerDetails.getUserId() == 0) {
            Response.setResponseMessage("missing input");
            Response.setSuccess(false);
            return new ResponseEntity<CommonApiResponse>(Response, HttpStatus.BAD_REQUEST);
        }

        //Updating the customer

        Customer customer=customerRepo.findById(updateCustomerDetails.getUserId()).get();
        customer.setAddress(updateCustomerDetails.getAddress());
        customer.setFirstName(updateCustomerDetails.getFirstName());
        customer.setLastName(updateCustomerDetails.getLastName());
        customer.setPhoneNo(updateCustomerDetails.getMobNo());
        customer.setEmail(updateCustomerDetails.getEmailId());
        customer.setState(updateCustomerDetails.getState());
        customer.setCity(updateCustomerDetails.getCity());
        customer.setStreet(updateCustomerDetails.getStreet());

        customerRepo.save(customer);

        Response.setResponseMessage("User Updated Successfully");
        Response.setSuccess(true);

        return new ResponseEntity<CommonApiResponse>(Response, HttpStatus.OK);

    }

    public ResponseEntity<postResponse> fetchAllCustomer(Integer pageNumber, Integer pageSize, String sortBy) {

        LOG.info("request received for fetching all posted job");

        Pageable p= PageRequest.of(pageNumber,pageSize,Sort.by(sortBy));

        postResponse Response=new postResponse();

        Page<Customer> customerPage= customerRepo.findAll(p);

        List<Customer> customerList=customerPage.getContent();

        if(customerList.size()==0){
            Response.setResponseMessage("No Customers found");
            Response.setSuccess(false);

            return new ResponseEntity<postResponse>(Response, HttpStatus.OK);

        }

        Response.setCustomers(customerList);

        Response.setPageNumber(customerPage.getNumber());
        Response.setPageSize(customerPage.getSize());
        Response.setTotalElements(customerPage.getTotalElements());
        Response.setTotalPages(customerPage.getTotalPages());
        Response.setLastPage(customerPage.isLast());

        Response.setResponseMessage("All Customer Fetched successfully");
        Response.setSuccess(true);

        return new ResponseEntity<postResponse>(Response, HttpStatus.OK);


    }
}
