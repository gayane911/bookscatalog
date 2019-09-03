package com.epam.bookscatalog.repository;

import com.epam.bookscatalog.model.Role;
import com.epam.bookscatalog.model.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByName(RoleName roleName);

}