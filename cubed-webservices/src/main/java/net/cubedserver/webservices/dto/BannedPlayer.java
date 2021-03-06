package net.cubedserver.webservices.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Strings;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * DTO for the banned table
 *
 * @author jdesive
 */
@Entity
@Getter
@Setter
@Table(name = "banned")
@NoArgsConstructor
@ToString(of = { "id" , "target" })
public class BannedPlayer implements HasValidator{

    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String target;
    private Date created;
    private Date expires;
    private String source;
    private String reason;

    @PrePersist
    public void prePersist() {
        created = new Date(System.currentTimeMillis());
    }

    @Override
    @JsonIgnore
    public boolean isValid() {
        return !Strings.isNullOrEmpty(target);
    }

}
