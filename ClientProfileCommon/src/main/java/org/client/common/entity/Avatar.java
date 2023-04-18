package org.client.common.entity;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.client.common.entity.Contacts.Email;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Avatar {

    @Id
    //@GeneratedValue(generator="system-uuid")
    //@GenericGenerator(name="avatar_uuid", strategy = "uuid")
    private String uuid;

    private String individualUuid;

    private String name;

    private String md5;
    private Long fileSize;
    @Lob
    @Column
    private byte[] byteSize;

//    @OneToMany
//    @JoinColumn(name = "avatar_uuid")
//    private Collection<Individual> individuals;


}
