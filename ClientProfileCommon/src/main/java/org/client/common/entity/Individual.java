package org.client.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.client.common.entity.Contacts.Email;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contactID")
    private ContactMedium contacts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="individ_address",
            joinColumns=  @JoinColumn(name="individ_uuid", referencedColumnName="uuid"),
            inverseJoinColumns= @JoinColumn(name="address_uuid", referencedColumnName="adr_uuid") )
    private Collection<Address> addresses;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)  //у одного клиента может быть много кошельков.
    @JoinColumn(name = "individ_uuid")                           // Но один кошелек может ссылаться только на одного клиента
    private Collection<Wallet> wallets;

}
