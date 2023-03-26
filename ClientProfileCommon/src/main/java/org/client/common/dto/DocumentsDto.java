package org.client.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.intellij.lang.annotations.Pattern;

import java.util.Collection;


@Schema(description = "модель, описывающая документы пользователя")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentsDto {
    public static final String UUID_PATTERN = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор документа по стандарту RFC4122")
    @Pattern(value = UUID_PATTERN)
    @JsonProperty(Fields.UUID)
    private String uuid;

    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор клиента по стандарту RFC4122")
    @Pattern(value = UUID_PATTERN)
    @JsonProperty(Fields.INDIVIDUAL_UUID)
    private String individualUuid;

    @Hidden
    @JsonProperty(Fields.INDIVIDUAL)
    private Collection<IndividualDto> individual;

    private Collection<DocumentType> documentTypes;


    public static class Fields {
        public static final String UUID = "uuid";
        public static final String INDIVIDUAL_UUID = "individualUuid";
        public static final String INDIVIDUAL = "individual";

    }

}
