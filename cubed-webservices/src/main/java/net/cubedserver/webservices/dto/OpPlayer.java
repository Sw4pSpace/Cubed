package net.cubedserver.webservices.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Strings;
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
public class OpPlayer implements HasValidator{

    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String uuid;

    @Override
    @JsonIgnore
    public boolean isValid() {
        return !Strings.isNullOrEmpty(name) && uuid != null;
    }

}
