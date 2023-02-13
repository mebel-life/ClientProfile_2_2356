package org.client.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.client.entity.Contacts.Email;
import org.client.entity.Contacts.PhoneNumber;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Collection;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ContactMedium {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String uuid;

    @OneToOne(mappedBy = "contacts", cascade = CascadeType.ALL)
    private Individual individual;

    @OneToMany(mappedBy = "contactMedium")
    private Collection<Email> emails;
    @OneToMany(mappedBy = "contactMedium")
    private Collection<PhoneNumber> phoneNumbers;

}
