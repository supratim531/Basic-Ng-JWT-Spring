package com.company.auth.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.auth.app.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}
