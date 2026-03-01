package com.project.sonica.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.sonica.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Optional<Customer> findByEmail(String email);

	List<Customer> findByRole(String role);
}
