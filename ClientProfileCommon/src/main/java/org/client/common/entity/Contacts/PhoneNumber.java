package org.client.common.entity.Contacts;

import lombok.*;
import org.client.common.entity.ContactMedium;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneNumber {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;

    private String value;

    @ManyToOne
    @JoinColumn(name = "contact_medium_id1")
    @ToString.Exclude
    private ContactMedium contactMedium;
    private Boolean verification;
}
