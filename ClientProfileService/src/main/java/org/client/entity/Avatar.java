package org.client.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.client.common.dto.AvatarDto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Avatar {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "uuid", unique = true, length = 50)
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    private Individual individual;
    private String name;

    private String md5;
    private Long fileSize;
    @Lob
    @Column
    private byte [] byteSize;

}
