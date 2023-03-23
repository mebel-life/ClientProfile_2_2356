package org.client.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.client.entity.Contacts.Email;
import org.client.entity.Contacts.PhoneNumber;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ContactMedium {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;  // на один ай ди может ссылаться  и phone_number, и email

    //Двусторонний OneToOne
    @ManyToOne(fetch = FetchType.LAZY)
    private Individual individual;

    @OneToMany(mappedBy = "contacts",cascade = CascadeType.ALL, fetch = FetchType.LAZY) //на один uuid .ContactMedium может ссылаться много имейлов
    private Collection<Email> emails;

    @OneToMany(mappedBy = "contacts",cascade = CascadeType.ALL, fetch = FetchType.LAZY) //на один uuid .ContactMedium. может ссылаться много тлф-номеров
    private Collection<PhoneNumber> phoneNumbers;

}
