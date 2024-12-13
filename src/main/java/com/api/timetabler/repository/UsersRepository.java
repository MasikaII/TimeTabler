package com.api.timetabler.repository;

import com.api.timetabler.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    Users findByPhoneNumber(String phoneNumber);
}
