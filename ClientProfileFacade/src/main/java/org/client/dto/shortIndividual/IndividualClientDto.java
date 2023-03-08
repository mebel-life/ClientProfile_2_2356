package org.client.dto.shortIndividual;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.client.common.dto.*;
import org.intellij.lang.annotations.Pattern;

import java.util.Collection;
import java.util.Date;

@Data
public class IndividualClientDto {
    public static final String UUID_PATTERN = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор клиента по стандарту RFC4122")
    @Pattern(value = UUID_PATTERN)
    @JsonProperty(Fields.UUID)
    private String uuid;

    @Schema(example = "312", description = "Идентификатор Клиентского профиля")
    @JsonProperty(Fields.ICP)
    private String icp;

    private String name;

    private String surname;

    private String patronymic;

    private String fullName;
    @Hidden
    @JsonProperty(Fields.ADDRESS)
    private Collection<AddressDto> address;
    @Hidden
    @JsonProperty(Fields.DOCUMENTS)
    private Collection<DocumentsDto> documents;
    @JsonProperty(Fields.AVATAR)
    private Collection<AvatarDto> avatar;


    public static class Fields {
        public static final String UUID = "uuid";

        public static final String ICP = "icp";

        public static final String ADDRESS = "address";

        public static final String WALLET = "wallet";

        public static final String DOCUMENTS = "documents";

        public static final String AVATAR = "avatar";

    }
}
