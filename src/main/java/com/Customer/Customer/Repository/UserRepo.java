package com.Customer.Customer.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Customer.Customer.Models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findUserByLoginId(String loginId);

}
