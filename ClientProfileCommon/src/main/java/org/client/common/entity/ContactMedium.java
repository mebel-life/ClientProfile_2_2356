package org.client.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.client.common.entity.Contacts.Email;
import org.client.common.entity.Contacts.PhoneNumber;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ContactMedium {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;  // на один ай ди может ссылаться  и phone_number, и email

    //Двусторонний OneToOne
    @OneToOne(mappedBy = "contacts", cascade = CascadeType.ALL)
    private Individual individual;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) //на один uuid .ContactMedium может ссылаться много имейлов
    @JoinColumn(name = "contactMediumId")
    private Collection<Email> emails;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) //на один uuid .ContactMedium. может ссылаться много тлф-номеров
    @JoinColumn(name = "contactMediumId")
    private Collection<PhoneNumber> phoneNumbers;

}
