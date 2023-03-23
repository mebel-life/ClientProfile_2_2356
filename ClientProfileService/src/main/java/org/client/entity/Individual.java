package org.client.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.client.common.dto.*;
import org.hibernate.annotations.GenericGenerator;
import org.intellij.lang.annotations.Pattern;

import javax.persistence.*;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Individual {


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
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

    private boolean isArchived = false;

    private String archivedIcp = "";

    private String documentsUuid;

    private UUID rfPassportUuid;

    private String contactsUuid;

    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL)
    private Collection<Avatar> avatar;
    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL)
    private Collection<Wallet> wallet;

    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL)
    private Collection<Documents> documents;

    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL)
    private Collection<RFPassport> passport;

    //Двусторонний OneToOne
    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL)
    private Collection<ContactMedium> contacts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="individ_address",
            joinColumns=  @JoinColumn(name="individ_icp", referencedColumnName="icp"),
            inverseJoinColumns= @JoinColumn(name="address_id", referencedColumnName="uuid") )
    private Collection<Address> addresses;

}
