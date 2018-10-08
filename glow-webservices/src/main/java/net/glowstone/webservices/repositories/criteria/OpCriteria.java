package net.glowstone.webservices.repositories.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.glowstone.webservices.dto.BannedPlayer;
import net.glowstone.webservices.dto.Op;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

/**
 * Represents the search criteria for the ops repository
 *
 * @author jdesive
 */
@Setter
@Getter
@AllArgsConstructor
public class OpCriteria {

    Long id;
    String name;
    String uuid;

    public boolean isEmpty() {
        return id == null && name == null && uuid == null;
    }

    public static Specification<Op> withId(Long id) {
        if(id != null) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
        }
        return null;
    }

    public static Specification<Op> withName(String name) {
        if(name != null) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
        }
        return null;
    }

    public static Specification<Op> withUuid(String uuid) {
        if(uuid != null) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("uuid"), uuid);
        }
        return null;
    }

}
