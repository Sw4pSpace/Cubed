package net.cubedserver.webservices.repositories;

import net.cubedserver.webservices.dto.OpPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * JPA repository for the ops table
 *
 * @author jdesice
 */
public interface OpsRepository extends JpaRepository<OpPlayer, Long>, JpaSpecificationExecutor<OpPlayer> {
}
