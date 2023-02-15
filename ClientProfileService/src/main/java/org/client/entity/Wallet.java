package org.client.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;


@Schema(description = "Модель, описывающая баланс пользоватяле в ЛК банка")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "wallet")
public class Wallet implements Serializable {

    @Schema(example = "1000000", description = "Идентификатор кошелька")
    @JsonProperty(Fields.ICP)
    @Column(name = "icp")
    @Size(min = 7, max = 100)
    private String icp;

    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор кошелька по стандарту RFC4122")
    @JsonProperty(Fields.UUID)
    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "rubwallet")
    @JsonProperty(Fields.RUB_WALLET)
    private String rubWallet;

    @Column(name = "eurowallet")
    private String euroWallet;

    @Column(name = "dollarwallet")
    private String dollarWallet;

    @OneToOne
    @JoinColumn(name = "individualuuid", referencedColumnName = "uuid")
    private Individual individual;

    public String getUuid() {
        return uuid.toString();
    }

    public void setUuid(String uuid) {
        this.uuid = UUID.fromString(uuid);
    }

    public static class Fields {
        public static final String UUID = "uuid";
        public static final String ICP = "icp";
        public static final String RUB_WALLET = "rubWallet";
    }

}
