package com.perscholas.studentlogin.repository;

import com.perscholas.studentlogin.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
