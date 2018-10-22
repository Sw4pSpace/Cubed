package net.cubedserver.webservices.repositories.specification;

import net.cubedserver.webservices.dto.BannedPlayer;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

/**
 * Specifications for the BannedPlayer criteria
 *
 * @author jdesive
 */
public class BannedPlayerSpecification {

    public static Specification<BannedPlayer> withId(Long id) {
        if(id != null) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
        }
        return null;
    }

    public static Specification<BannedPlayer> withTarget(String target) {
        if(target != null) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("target"), target);
        }
        return null;
    }

    public static Specification<BannedPlayer> withCreated(Date created) {
        if(created != null) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("created"), created);
        }
        return null;
    }

    public static Specification<BannedPlayer> withExpires(Date expires) {
        if(expires != null) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("expires"), expires);
        }
        return null;
    }

    public static Specification<BannedPlayer> withSource(String source) {
        if(source != null) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("source"), source);
        }
        return null;
    }

    public static Specification<BannedPlayer> withReason(String reason) {
        if(reason != null) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("reason"), reason);
        }
        return null;
    }

}
