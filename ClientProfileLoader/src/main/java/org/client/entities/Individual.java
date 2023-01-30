package org.client.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Individual entity
 */

@Schema(description = "Model describing user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="clients")
public class Individual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "312", description = "Идентификатор Клиентского профиля")
    @JsonProperty(Fields.ICP)
    private String icp;

    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор клиента по стандарту RFC4122")
    @JsonProperty(Fields.UUID)
    private UUID uuid;
    private String name;
    private String surname;
    private String patronymic;
    private String fullName;
    private String gender;
    private String placeOfBirth;
    private String countryOfBirth;
    private Date birthDate;

    public static class Fields {
        public static final String UUID = "uuid";

        public static final String ICP = "icp";
    }
}
