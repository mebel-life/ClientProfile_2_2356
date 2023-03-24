package org.client.common.entity;

import lombok.*;
import org.client.common.entity.Contacts.Email;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Individual {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
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

    private String actualIcp = icp;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "documentID")
    private Documents documents;

    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL)
    private Collection<RFPassport> passport;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contactID")
    private ContactMedium contacts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="individ_address",
            joinColumns=  @JoinColumn(name="individ_uuid", referencedColumnName="uuid"),
            inverseJoinColumns= @JoinColumn(name="address_uuid", referencedColumnName="uuid") )
    private Collection<Address> addresses;

    //двусторонний  @OneToMany
    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL, orphanRemoval = true)  //у одного клиента может быть много кошельков. Но один кошелек может ссылаться только на одного клиента
    private Collection<Wallet> wallets;

}
