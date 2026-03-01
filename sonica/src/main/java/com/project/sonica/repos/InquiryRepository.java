package com.project.sonica.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.sonica.entity.Inquiry;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Integer> {
	List<Inquiry> findByStatus(String status);

	List<Inquiry> findByEventDate(LocalDate eventDate);
}
