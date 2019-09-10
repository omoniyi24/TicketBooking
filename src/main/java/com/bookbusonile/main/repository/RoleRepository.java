package com.bookbusonile.main.repository;

import com.bookbusonile.main.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByRole(String user);
}
