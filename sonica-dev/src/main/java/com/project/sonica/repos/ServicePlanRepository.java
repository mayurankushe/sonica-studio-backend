package com.project.sonica.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.project.sonica.entity.ServicePlan;

@Repository
public interface ServicePlanRepository
		extends JpaRepository<ServicePlan, Integer>, JpaSpecificationExecutor<ServicePlan> {
	List<ServicePlan> findByEventType(String eventType);

}
