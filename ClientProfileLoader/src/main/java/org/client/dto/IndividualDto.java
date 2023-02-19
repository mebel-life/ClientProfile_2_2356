package org.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.boot.Metadata;
import org.intellij.lang.annotations.Pattern;

import java.util.Collection;
import java.util.Date;

@Schema(description = "Модель, описывающая пользователя в ЛК банка")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IndividualDto {
    public static final String UUID_PATTERN = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор клиента по стандарту RFC4122")
    @Pattern(value = UUID_PATTERN)
    @JsonProperty(Fields.UUID)
    private String uuid;

    @Schema(example = "312", description = "Идентификатор Клиентского профиля")
    @JsonProperty(Fields.ICP)
    private String icp;

    @JsonProperty(Fields.UCN)
    private String ucn;

    @JsonProperty(Fields.MDM_ID)
    private String mdm_id;

    @JsonProperty(Fields.INDIVIDUAL_LIFE_CYCLE)
    private String individualLifeCycle;

    @JsonProperty(Fields.TITLE)
    private String title;

    @JsonProperty(Fields.NAME)
    private String name;

    @JsonProperty(Fields.SURNAME)
    private String surname;

    @JsonProperty(Fields.PATRONYMIC)
    private String patronymic;

    @JsonProperty(Fields.FULL_NAME)
    private String fullName;

    @JsonProperty(Fields.FORMATTED_NAME)
    private String formattedName;

    @JsonProperty(Fields.GENDER)
    private String gender;

    @JsonProperty(Fields.PLACE_OF_BIRTH)
    private String placeOfBirth;

    @JsonProperty(Fields.COUNTRY_OF_BIRTH)
    private String countryOfBirth;

    @JsonProperty(Fields.MARITAL_STATUS)
    private String maritalStatus;

    @JsonProperty(Fields.BIRTHDATE)
    private Date birthDate;


    @JsonProperty(Fields.REZIDENT_FLAG)
    private boolean rezidentFlag;

    @JsonProperty(Fields.TAX_REZIDENT)
    private boolean taxRezident;

    @JsonProperty(Fields.PUB_OFFICIAL_STATUS)
    private String pubOfficialStatus;

    @JsonProperty(Fields.NATIONALITY_COUNTRY_CODE)
    private String nationalityCountryCode;

    @JsonProperty(Fields.METADATA)
    private Metadata metadata;

    @JsonProperty(Fields.IS_UPRID_BY_SMEV)
    private boolean isUpridBySmev;

    @Hidden
    @JsonProperty(Fields.ADDRESS)
    private Collection<AddressDto> address;

    @Hidden
    @JsonProperty(Fields.WALLET)
    private Collection<WalletDto> wallet;


    public static class Fields {

        public static final String UUID = "uuid";

        public static final String ICP = "icp";

        private static final String UCN = "ucn";

        private static final String MDM_ID = "mdm_id";

        private static final String INDIVIDUAL_LIFE_CYCLE = "individualLifeCycle";

        private static final String TITLE = "title";

        private static final String NAME = "name";

        private static final String SURNAME = "surname";

        private static final String PATRONYMIC = "patronymic";

        private static final String FULL_NAME = "fullName";

        private static final String FORMATTED_NAME = "formattedName";

        private static final String GENDER = "gender";

        private static final String PLACE_OF_BIRTH = "placeOfBirth";

        private static final String COUNTRY_OF_BIRTH = "countryOfBirth";

        private static final String MARITAL_STATUS = "maritalStatus";

        private static final String BIRTHDATE = "birthday";

        private static final String REZIDENT_FLAG = "rezidentFlag";

        private static final String TAX_REZIDENT = "taxRezident";

        private static final String PUB_OFFICIAL_STATUS = "pubOfficialStatus";

        private static final String NATIONALITY_COUNTRY_CODE = "nationalityCountryCode";

        private static final String IS_UPRID_BY_SMEV = "isUpridBySmev";

        private static final String METADATA = "metadata";

        public static final String ADDRESS = "address";

        public static final String WALLET = "wallet";
    }
}
