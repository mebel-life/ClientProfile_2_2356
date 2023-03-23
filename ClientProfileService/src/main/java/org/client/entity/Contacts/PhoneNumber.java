package org.client.entity.Contacts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.client.entity.ContactMedium;
import org.client.entity.Individual;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneNumber {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    private ContactMedium contacts;
    private String value;
}
