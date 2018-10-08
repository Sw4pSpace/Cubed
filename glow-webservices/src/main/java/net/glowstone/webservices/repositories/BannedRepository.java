package net.glowstone.webservices.repositories;

import net.glowstone.webservices.dto.BannedPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BannedRepository extends JpaRepository<BannedPlayer, Long>, JpaSpecificationExecutor<BannedPlayer> {

    BannedPlayer findByTarget(String target);

}
