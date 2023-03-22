package org.client.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.intellij.lang.annotations.Pattern;


import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

@Schema(description = "Модель, описывающая пользоватяле в ЛК банка")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class IndividualDto {

    public static final String UUID_PATTERN = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор клиента по стандарту RFC4122")
    @Pattern(value = UUID_PATTERN)
    @JsonProperty(Fields.UUID)
    private String uuid;

    @Schema(example = "312", description = "Идентификатор Клиентского профиля")
    @JsonProperty(Fields.ICP)
    private String icp;
    @JsonProperty(Fields.NAME)
    private String name;
    @JsonProperty(Fields.SURNAME)
    private String surname;
    @JsonProperty(Fields.PATRONYMIC)
    private String patronymic;
    @JsonProperty(Fields.FULL_NAME)
    private String fullName;
    @JsonProperty(Fields.GENDER)
    private String gender;
    @JsonProperty(Fields.PLACE_OF_BIRTH)
    private String placeOfBirth;
    @JsonProperty(Fields.COUNTRY_OF_BIRTH)
    private String countryOfBirth;
    @JsonProperty(Fields.BIRTH_DATE)
    private Date birthDate;
    @JsonProperty(Fields.IS_ARCHIVED)
    private boolean isArchived = false;
    @JsonProperty(Fields.ARCHIVED_ICP)
    private String archivedIcp = "";

    private String documentsUuid;

    private UUID rfPassportUuid;

    private String contactsUuid;
    @Hidden
    @JsonProperty(Fields.ADDRESS)
    private Collection<AddressDto> address;

    @Hidden
    @JsonProperty(Fields.WALLET)
    private Collection<WalletDto> wallet;
    @Hidden
    @JsonProperty(Fields.DOCUMENTS)
    private Collection<DocumentsDto> documents;

    @Hidden
    @JsonProperty(Fields.RFPASSPORT)
    private Collection<RFPassportDto> passport;
    @Hidden
    @JsonProperty(Fields.CONTACT_MEDIUM)
    private Collection<ContactMediumDto> contactMedium;
    @JsonProperty(Fields.AVATAR)
    private Collection<AvatarDto> avatar;


    public static class Fields {
        public static final String UUID = "uuid";

        public static final String ICP = "icp";

        public static final String NAME = "name";

        public static final String SURNAME = "surname";

        public static final String PATRONYMIC = "patronymic";

        public static final String FULL_NAME = "fullName";

        public static final String GENDER = "gender";

        public static final String PLACE_OF_BIRTH = "placeOfBirth";

        public static final String COUNTRY_OF_BIRTH = "countryOfBirth";

        public static final String IS_ARCHIVED = "isArchived";

        public static final String ARCHIVED_ICP = "archivedIcp";

        public static final String BIRTH_DATE = "birthDate";

        public static final String ADDRESS = "address";

        public static final String WALLET = "wallet";

        public static final String DOCUMENTS = "documents";

        public static final String RFPASSPORT = "passport";

        public static final String CONTACT_MEDIUM = "contactMedium";

        public static final String AVATAR = "avatar";

    }

    // Serializing Dto To Json object.
    public static String Serializer(IndividualDto individualDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = null;
        try {
            json = objectMapper.writeValueAsString(individualDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;

    }

    //Deserializing from Json to IndividualDto.
    public static IndividualDto Deserializer(String json) {

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        IndividualDto individualDto = null;
        try {
            individualDto = objectMapper.readValue(json, IndividualDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return individualDto;

    }

    private static Logger logger = Logger.getLogger(IndividualDto.class.getName());


}
