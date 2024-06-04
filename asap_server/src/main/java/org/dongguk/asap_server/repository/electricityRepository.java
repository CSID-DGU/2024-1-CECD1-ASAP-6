package org.dongguk.asap_server.repository;

import org.dongguk.asap_server.domain.Electricity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface electricityRepository extends JpaRepository<Electricity, Long> {
}
