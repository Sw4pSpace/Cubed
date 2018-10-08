package net.glowstone.webservices.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "banned")
@NoArgsConstructor
@ToString(of = { "id" , "target" })
public class BannedPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
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

}
