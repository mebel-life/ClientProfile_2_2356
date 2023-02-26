package org.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.intellij.lang.annotations.Pattern;

@Schema(description = "модель, описывающая электронную почту пользователя")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneNumberDto {
    public static final String UUID_PATTERN = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор номера телефона" +
            " пользователя по стандарту RFC4122")
    @Pattern(value = UUID_PATTERN)
    @JsonProperty(Fields.UUID)
    private String uuid;

    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор контактной информации" +
            " по стандарту RFC4122")
    @Pattern(value = UUID_PATTERN)
    @JsonProperty(Fields.CONTACT_MEDIUM_UUID)
    private String contactMediumUuid;

    private String value;



    public static class Fields {
        public static final String UUID = "uuid";
        public static final String CONTACT_MEDIUM_UUID = "contactMediumUuid";

    }

}
