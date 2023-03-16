package org.client.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Individual {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
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
    @JoinColumn(name = "documentID")
    private Documents documents;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rfPassport")
    private RFPassport rfPassport;

    //Двусторонний OneToOne
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contactID")
    private ContactMedium contacts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="individ_address",
            joinColumns=  @JoinColumn(name="individ_icp", referencedColumnName="icp"),
            inverseJoinColumns= @JoinColumn(name="address_id", referencedColumnName="uuid") )
    private Collection<Address> addresses;

}
