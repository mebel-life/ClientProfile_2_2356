package org.client.entity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Individual {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String uuid;

    private String icp;
    private String name;
    private String surname;
    private String patronymic;
    private String fullName;
    private String gender;
    private String placeOfBirth;
    private String countryOfBirth;
    private Date birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "documents")
    private Documents documents;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contacts")
    private ContactMedium contacts;

}
