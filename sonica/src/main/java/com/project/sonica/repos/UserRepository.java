package com.project.sonica.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.sonica.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);
}
