package com.project.gatekeeper.management.repository;

import com.project.gatekeeper.management.entity.ManagementUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagementUserRepository extends JpaRepository<ManagementUser, Long> {

    Optional<ManagementUser> findByUsername(String username);
}
