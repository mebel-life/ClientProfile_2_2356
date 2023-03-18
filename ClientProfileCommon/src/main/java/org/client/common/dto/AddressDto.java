package org.client.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.intellij.lang.annotations.Pattern;


@Schema(description = "Модель, описывающая адрес")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDto {
    public static final String UUID_PATTERN = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";

    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор адреса по стандарту RFC4122")
    @Pattern(value = UUID_PATTERN)
    @JsonProperty(Fields.UUID)
    private String uuid;

//    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор клиента по стандарту RFC4122")
//    @Pattern(value = UUID_PATTERN)
//    @JsonProperty(Fields.INDIVIDUAL_UUID)
//    private String individualUuid;

    @Schema(example = "133256", description = "Идентификатор Клиентского профиля ")
    @JsonProperty(Fields.INDIVIDUAL_ICP)
    private String individualIcp;

    private String notFormAddrName;

    private String addressName;

    private String country;

    private String zipCode;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIndividualIcp() {
        return individualIcp;
    }

    public void setIndividualIcp(String individualIcp)  {
        this.individualIcp = individualIcp;
    }

    public static class Fields {

        public static final String UUID = "uuid";

        public static final String INDIVIDUAL_ICP = "individualIcp";
    }
}
