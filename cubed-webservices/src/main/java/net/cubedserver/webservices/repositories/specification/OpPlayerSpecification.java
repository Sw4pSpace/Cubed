package net.cubedserver.webservices.repositories.specification;

import net.cubedserver.webservices.dto.OpPlayer;
import org.springframework.data.jpa.domain.Specification;

/**
 * Specifications for the OpPlayer criteria
 *
 * @author jdesive
 */
public class OpPlayerSpecification {

    public static Specification<OpPlayer> withId(Long id) {
        if(id != null) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
        }
        return null;
    }

    public static Specification<OpPlayer> withName(String name) {
        if(name != null) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
        }
        return null;
    }

    public static Specification<OpPlayer> withUuid(String uuid) {
        if(uuid != null) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("uuid"), uuid);
        }
        return null;
    }

}
