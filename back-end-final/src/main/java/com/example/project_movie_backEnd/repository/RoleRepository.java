package com.example.project_movie_backEnd.repository;

import com.example.project_movie_backEnd.model.ERole;
import com.example.project_movie_backEnd.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
