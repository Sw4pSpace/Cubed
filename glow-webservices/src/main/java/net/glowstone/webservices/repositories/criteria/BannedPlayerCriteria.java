package net.glowstone.webservices.repositories.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.glowstone.webservices.dto.BannedPlayer;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

/**
 * Represents the search criteria for the banned repository
 *
 * @author jdesive
 */
@Setter
@Getter
@AllArgsConstructor
public class BannedPlayerCriteria {

    Long id;
    String target;
    Date created;
    Date expires;
    String source;
    String reason;

    public boolean isEmpty() {
        return id == null && target == null && created == null && expires == null && source == null && reason == null;
    }

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
