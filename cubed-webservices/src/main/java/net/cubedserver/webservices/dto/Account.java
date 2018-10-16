package net.cubedserver.webservices.dto;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
@ToString(of = { "id" , "username", "password" })
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    private String password;

    private Date created;

    @PrePersist
    public void prePersist() {
        created = new Date(System.currentTimeMillis());
    }

}
