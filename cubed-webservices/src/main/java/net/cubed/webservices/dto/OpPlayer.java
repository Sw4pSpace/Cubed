package net.cubed.webservices.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * DTO for the ops table
 *
 * @author jdesive
 */
@Entity
@Getter
@Setter
@Table(name = "ops")
@NoArgsConstructor
@ToString(of = { "id" , "name", "uuid" })
public class OpPlayer {

    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String uuid;

}
