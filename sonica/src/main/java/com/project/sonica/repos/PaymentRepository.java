package com.project.sonica.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.sonica.entity.Customer;
import com.project.sonica.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	Optional<Payment> findByTransactionId(String transactionId);

	List<Payment> findByStatus(String status);

	List<Payment> findByCustomer(Customer customer);

	long countByStatus(String status);

}
