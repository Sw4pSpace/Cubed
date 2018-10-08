package net.cubed.webservices.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * DTO for the whitelist table
 *
 * @author jdesive
 */
@Entity
@Getter
@Setter
@Table(name = "whitelist")
@NoArgsConstructor
@ToString(of = { "id" , "name", "uuid" })
public class WhiteListPlayer {

    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String uuid;

}
