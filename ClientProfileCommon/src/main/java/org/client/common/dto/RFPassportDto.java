package org.client.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.intellij.lang.annotations.Pattern;

import java.util.Collection;
import java.util.UUID;

@Schema(description = "модель, описывающая паспорт РФ пользователя")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RFPassportDto {
    public static final String UUID_PATTERN = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор паспорта РФ клиента по стандарту RFC4122")
    @JsonProperty(Fields.UUID)
    private UUID uuid;
    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор клиента по стандарту RFC4122")
    @Pattern(value = UUID_PATTERN)
    @JsonProperty(Fields.INDIVIDUAL_UUID)
    private String individualUuid;
    @Schema(example = "11 04", description = "Серия паспорта, первые 4 цифры")
    private String series;
    @Schema(example = "123456", description = "6-ти значный номер паспорта")
    private String number;
    private String issued;
    private String departmentDoc;
    private String receiptDocDate;
    private String validateDateDoc;
    @Schema(example = "Иван", description = "Имя согласно паспорту")
    private String name;
    @Schema(example = "Иванов", description = "Фамилия согласно паспорту")
    private String surname;
    @Schema(example = "Иванович", description = "Отчество согласно паспорту")
    private String patronymic;
    @Schema(example = "Муж.", description = "Пол согласно паспорту")
    private String gender;
    @Schema(example = "12.12.2000", description = "Дата рождения согласно паспорту")
    private String birthdate;
    @Schema(example = "Москва", description = "Место рождения согласно паспорту")
    private String birthplace;
    @Schema(example = "Отделом внутренних дел", description = "Кем выдан согласно паспорту")
    private String issuedBy;
    @Schema(example = "123-123", description = "Код подразделения согласно паспорту")
    private String division;
    private String invalidityReason;
    private String message;
    private String legalForce;
    private String passportStatus;

    private DocumentType documentType = DocumentType.RFPassport;
    @Hidden
    @JsonProperty(Fields.INDIVIDUAL)
    private Collection<IndividualDto> individual;


    public static class Fields {
        public static final String UUID = "uuid";
        public static final String INDIVIDUAL_UUID = "individualUuid";
        public static final String INDIVIDUAL = "individual";

    }
}
