package org.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.boot.Metadata;
import org.intellij.lang.annotations.Pattern;

@Schema(description = "Модель, описывающая адрес")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AddressDto {
    public static final String UUID_PATTERN = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";

    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор адреса по стандарту RFC4122")
    @Pattern(value = UUID_PATTERN)
    @JsonProperty(Fields.UUID)
    private String uuid;

    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор клиента по стандарту RFC4122")
    @Pattern(value = UUID_PATTERN)
    @JsonProperty(Fields.INDIVIDUAL_UUID)
    private String individualUuid;

    @JsonProperty(value = Fields.NOT_FORM_ADDR_NAME)
    private String notFormAddrName;

    @JsonProperty(value = Fields.ADDRESS_NAME)
    private String addressName;

    @JsonProperty(value = Fields.COUNTRY)
    private String country;

    @JsonProperty(value = Fields.ZIP_CODE)
    private String zipCode;

    @JsonProperty(value = Fields.STATE)
    private String state;

    @JsonProperty(value = Fields.PROVINCE)
    private String province;

    @JsonProperty(value = Fields.PROVINCE_TYPE)
    private String provinceType;

    @JsonProperty(value = Fields.AREA)
    private String area;

    @JsonProperty(value = Fields.REGION)
    private String region;

    @JsonProperty(value = Fields.REGION_TYPE)
    private String regionType;

    @JsonProperty(value = Fields.ADDITION_AREA)
    private String additionArea;

    @JsonProperty(value = Fields.CITY)
    private String city;

    @JsonProperty(value = Fields.CITY_TYPE)
    private String cityType;

    @JsonProperty(value = Fields.SETTLEMENT)
    private String settlement;

    @JsonProperty(value = Fields.SETTLEMENT_TYPE)
    private String settlementType;

    @JsonProperty(value = Fields.STREET)
    private String street;

    @JsonProperty(value = Fields.STREET_TYPE)
    private String streetType;

    @JsonProperty(value = Fields.ADDITION_AREA_STREET)
    private String additionAreaStreet;

    @JsonProperty(value = Fields.HOUSE)
    private String house;

    @JsonProperty(value = Fields.HOUSING)
    private String housing;

    @JsonProperty(value = Fields.BUILDING)
    private String building;

    @JsonProperty(value = Fields.FLAT)
    private String flat;

    @JsonProperty(value = Fields.FIAS_CODE)
    private String fiasCode;

    @JsonProperty(AddressDto.Fields.METADATA)
    private Metadata metadata;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIndividualUUID() {
        return individualUuid;
    }

    public void setIndividualUUID(String individualUUID) {
        this.individualUuid = individualUUID;
    }
    public static class Fields {
        public static final String UUID = "uuid";

        public static final String INDIVIDUAL_UUID = "individualUuid";

        public static final String NOT_FORM_ADDR_NAME = "notFormAddrName";

        public static final String ADDRESS_NAME = "addressName";

        public static final String COUNTRY = "country";

        public static final String ZIP_CODE = "zipCode";

        private static final String STATE = "state";

        private static final String PROVINCE = "province";

        private static final String PROVINCE_TYPE = "provinceType";

        private static final String AREA = "area";

        private static final String REGION = "region";

        private static final String REGION_TYPE = "regionType";

        private static final String ADDITION_AREA = "additionArea";

        private static final String CITY = "city";

        private static final String CITY_TYPE = "cityType";

        private static final String SETTLEMENT = "settlement";

        private static final String SETTLEMENT_TYPE = "settlementType";

        private static final String STREET = "street";

        private static final String STREET_TYPE = "streetType";

        private static final String ADDITION_AREA_STREET = "additionAreaStreet";

        private static final String HOUSE = "house";

        private static final String HOUSING = "housing";

        private static final String BUILDING = "building";

        private static final String FLAT = "flat";

        private static final String FIAS_CODE = "fiasCode";

        private static final String METADATA = "metadata";
    }
}

