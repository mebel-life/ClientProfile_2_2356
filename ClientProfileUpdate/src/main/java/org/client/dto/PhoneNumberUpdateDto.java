package org.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import org.intellij.lang.annotations.Pattern;

import java.util.Objects;


@Data

public class PhoneNumberUpdateDto {
    public static final String UUID_PATTERN = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";

    @Pattern(value = UUID_PATTERN)
    @JsonProperty(Fields.UUID)
    String uuid;
    @JsonProperty(Fields.VALUE)

    @Schema(example = "8911-111-11-11", description = "11-ти значный номер телефона")
    String value;
    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор контактной информации" +
            " по стандарту RFC4122")
    @Pattern(value = UUID_PATTERN)
    @JsonProperty(Fields.CONTACT_MEDIUM_UUID)
    private String contactMediumUuid;

    public static class Fields {

        public static final String UUID = "uuid";

        public static final String VALUE = "value";
        public static final String CONTACT_MEDIUM_UUID = "contactMediumUuid";



    }
    private Boolean verification;

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumberUpdateDto that = (PhoneNumberUpdateDto) o;
        return Objects.equals(value, that.value);
    }
}
