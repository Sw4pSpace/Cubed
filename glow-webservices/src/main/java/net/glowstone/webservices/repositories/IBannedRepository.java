package net.glowstone.webservices.repositories;

import net.glowstone.webservices.dto.BannedPlayer;
import net.glowstone.webservices.repositories.criteria.BannedPlayerCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBannedRepository {

    Page<BannedPlayer> findByCriteria(BannedPlayerCriteria criteria, Pageable pageable);

}
