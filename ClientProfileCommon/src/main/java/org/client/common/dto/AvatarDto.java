package org.client.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Schema(description = "Модель, описывающая аватар пользователя")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AvatarDto {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JsonProperty(Fields.UUID)
    @Column(name = "uuid", unique = true, length = 50)
    private String uuid;

    private String name;

    private String md5;
    private Long fileSize;
    @Lob
    @Column
    private byte [] byteSize;

    public static class Fields {

        public static final String UUID = "uuid";

    }

}

