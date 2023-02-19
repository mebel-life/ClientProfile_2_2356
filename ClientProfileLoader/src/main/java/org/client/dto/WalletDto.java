package org.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.intellij.lang.annotations.Pattern;

@Schema(description = "Модель, описывающая баланс пользоватяле в ЛК банка")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WalletDto {
    public static final String UUID_PATTERN = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор кошелька по стандарту RFC4122")
    @Pattern(value = UUID_PATTERN)
    @JsonProperty(Fields.UUID)
    private String uuid;
    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор кошелька по стандарту RFC4122")
    @Pattern(value = UUID_PATTERN)
    @JsonProperty(Fields.INDIVIDUAL_UUID)
    private String individualUuid;

    @JsonProperty(Fields.RUB_WALLET)
    private String rubWallet;

    @JsonProperty(Fields.EURO_WALLET)
    private String euroWallet;

    @JsonProperty(Fields.DOLLAR_WALLET)
    private String dollarWallet;

    public static class Fields {

        public static final String UUID = "uuid";

        public static final String INDIVIDUAL_UUID = "individualUuid";

        public static final String RUB_WALLET = "rubWallet";

        private static final String EURO_WALLET = "euroWallet";

        private static final String DOLLAR_WALLET = "dollarWallet";
    }
}


