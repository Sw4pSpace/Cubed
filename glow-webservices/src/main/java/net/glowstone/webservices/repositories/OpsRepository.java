package net.glowstone.webservices.repositories;

import net.glowstone.webservices.dto.Op;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OpsRepository extends JpaRepository<Op, Long>, JpaSpecificationExecutor<Op> {
}
