package net.sw4pspace.cubedwebservices.repositories.specification;

import net.sw4pspace.cubedwebservices.dto.WhiteListPlayer;
import org.springframework.data.jpa.domain.Specification;

/**
 * Specifications for the WhiteListPlayer criteria
 *
 * @author jdesive
 */
public class WhiteListPlayerSpecification {

    public static Specification<WhiteListPlayer> withId(Long id) {
        if(id != null) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
        }
        return null;
    }

    public static Specification<WhiteListPlayer> withName(String name) {
        if(name != null) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
        }
        return null;
    }

    public static Specification<WhiteListPlayer> withUuid(String uuid) {
        if(uuid != null) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("uuid"), uuid);
        }
        return null;
    }

}
