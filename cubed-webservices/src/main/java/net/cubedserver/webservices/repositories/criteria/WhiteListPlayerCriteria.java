package net.cubedserver.webservices.repositories.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents the search criteria for the whitelist repository
 *
 * @author jdesive
 */
@Setter
@Getter
@AllArgsConstructor
public class WhiteListPlayerCriteria {

    Long id;
    String name;
    String uuid;

    public boolean isEmpty() {
        return id == null && name == null && uuid == null;
    }

}
