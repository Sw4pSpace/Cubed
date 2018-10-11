package net.sw4pspace.cubedwebservices.repositories;

import net.sw4pspace.cubedwebservices.dto.WhiteListPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * JPA repository for the whitelist table
 *
 * @author jdesice
 */
public interface WhiteListRepository extends JpaRepository<WhiteListPlayer, Long>, JpaSpecificationExecutor<WhiteListPlayer> {
}
