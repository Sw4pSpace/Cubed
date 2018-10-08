package net.glowstone.webservices.repositories;

import net.glowstone.webservices.dto.BannedPlayer;
import net.glowstone.webservices.repositories.criteria.BannedPlayerCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static net.glowstone.webservices.repositories.criteria.BannedPlayerCriteria.*;
import static org.springframework.data.jpa.domain.Specifications.where;

public class BannedRepositoryImpl implements IBannedRepository {

    @Autowired private BannedRepository bannedRepository;

    @Override
    public Page<BannedPlayer> findByCriteria(BannedPlayerCriteria criteria, Pageable pageable) {
        if(criteria.isEmpty()) {
            return bannedRepository.findAll(pageable);
        }
        return bannedRepository.findAll(where(withId(criteria.getId()))
                .and(withTarget(criteria.getTarget()))
                .and(withCreated(criteria.getCreated()))
                .and(withExpires(criteria.getExpires()))
                .and(withSource(criteria.getSource()))
                .and(withReason(criteria.getReason())), pageable);
    }
}
