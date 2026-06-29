package com.anshbkeai.issuetracker.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anshbkeai.issuetracker.core.model.AppUser;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser , String> {

    Optional<AppUser> findByUsername(String username);
}
