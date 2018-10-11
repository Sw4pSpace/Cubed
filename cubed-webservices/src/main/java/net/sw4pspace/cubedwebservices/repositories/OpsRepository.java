package net.sw4pspace.cubedwebservices.repositories;

import net.sw4pspace.cubedwebservices.dto.OpPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * JPA repository for the ops table
 *
 * @author jdesice
 */
public interface OpsRepository extends JpaRepository<OpPlayer, Long>, JpaSpecificationExecutor<OpPlayer> {
}
