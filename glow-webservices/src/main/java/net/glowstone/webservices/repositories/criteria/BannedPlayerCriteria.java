package net.glowstone.webservices.repositories.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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

}
