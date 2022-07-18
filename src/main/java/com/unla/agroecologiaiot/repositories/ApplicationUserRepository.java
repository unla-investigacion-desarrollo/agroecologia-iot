package com.unla.agroecologiaiot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.agroecologiaiot.entities.ApplicationUser;

@Repository("applicationUserRepository")
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    Optional<ApplicationUser> findByUsername(String username);

    @Query("SELECT u FROM ApplicationUser u JOIN FETCH u.role where u.userId = (:id)")
    Optional<ApplicationUser> findByIdAndFetchRoleEagerly(@Param("id") long id);

}
