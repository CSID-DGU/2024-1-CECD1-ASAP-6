package org.dongguk.asap_server.repository;

import org.dongguk.asap_server.domain.User;
import org.dongguk.asap_server.type.EStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.section = :section AND u.editedAt IS NOT NULL ORDER BY u.editedAt DESC")
    Page<User> findAllBySectionAndEditedAtNotNull(Pageable pageable, @Param("section") String section);

    @Query("select u from User u " +
            "where u.section = :section AND " +
            "(:status is null or u.status = :status) AND" +
            "(:text IS NULL OR u.address LIKE CONCAT('%', :text, '%')) " +
            "order by u.address")
    Page<User> findAllBySectionAndStatusWithAddress(Pageable pageable,
                                                    @Param("status") EStatus status,
                                                    @Param("section") String section,
                                                    @Param("text") String text);

    List<User> findAllBySection(String section);
}
