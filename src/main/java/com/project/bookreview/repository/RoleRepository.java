package com.project.bookreview.repository;

import com.project.bookreview.entity.Role;
import com.project.bookreview.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {



}
