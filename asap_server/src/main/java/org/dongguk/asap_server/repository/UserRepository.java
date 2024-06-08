package org.dongguk.asap_server.repository;

import org.dongguk.asap_server.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.section = :section AND u.editedAt IS NOT NULL ORDER BY u.editedAt DESC")
    Page<User> findAllBySectionAndEditedAtNotNull(Pageable pageable, @Param("section") String section);
}
