package com.crudfunctionality.crud_functionality.repository;

import com.crudfunctionality.crud_functionality.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Integer> {
}
