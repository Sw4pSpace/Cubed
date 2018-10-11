package net.sw4pspace.cubedwebservices.repositories;

import net.sw4pspace.cubedwebservices.dto.BannedPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * JPA repository for the banned table
 *
 * @author jdesice
 */
public interface BannedRepository extends JpaRepository<BannedPlayer, Long>, JpaSpecificationExecutor<BannedPlayer> {

    BannedPlayer findByTarget(String target);

}
