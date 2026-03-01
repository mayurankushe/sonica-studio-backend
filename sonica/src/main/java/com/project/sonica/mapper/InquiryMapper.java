package com.project.sonica.mapper;

import org.springframework.stereotype.Component;

import com.project.sonica.DTO.InquiryRequest;
import com.project.sonica.DTO.InquiryResponse;
import com.project.sonica.entity.Customer;
import com.project.sonica.entity.Inquiry;

@Component
public class InquiryMapper {
	public InquiryResponse toResponse(Inquiry inquiry) {
		InquiryResponse dto = new InquiryResponse();
		dto.setInquiryId(inquiry.getInquiryId());
		dto.setEventDate(inquiry.getEventDate());
		dto.setLocation(inquiry.getLocation());
		dto.setBudget(inquiry.getBudget());
		dto.setStatus(inquiry.getStatus());
		return dto;
	}

	public Inquiry toEntity(InquiryRequest request, Customer customer) {
		Inquiry inquiry = new Inquiry();
		inquiry.setCustomer(customer);
		inquiry.setEventDate(request.getEventDate());
		inquiry.setLocation(request.getLocation());
		inquiry.setBudget(request.getBudget());
		inquiry.setStatus("New");
		return inquiry;
	}
}