package com.poke.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poke.persistence.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}