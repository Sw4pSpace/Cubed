package net.glowstone.webservices.repositories.specification;

import net.glowstone.webservices.dto.OpPlayer;
import net.glowstone.webservices.dto.WhiteListPlayer;
import org.springframework.data.jpa.domain.Specification;

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
