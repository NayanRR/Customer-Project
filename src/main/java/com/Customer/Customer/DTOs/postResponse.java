package com.Customer.Customer.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class postResponse extends UserResponseDto{

    private int pageNumber;
    private int pageSize;

    private long totalElements;

    private long totalPages;

    private boolean lastPage;

}
