package net.glowstone.webservices.repositories;

import net.glowstone.webservices.dto.WhiteListPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WhiteListRepository extends JpaRepository<WhiteListPlayer, Long>, JpaSpecificationExecutor<WhiteListPlayer> {
}
