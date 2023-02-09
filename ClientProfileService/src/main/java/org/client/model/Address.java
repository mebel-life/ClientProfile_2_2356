package org.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.client.dto.WalletDto;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Schema(description = "Модель, описывающая адрес")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Schema(example = "1000000", description = "Идентификатор адреса")
    @JsonProperty(Wallet.Fields.ICP)
    @Column(name = "icp")
    @Size(min = 7, max = 100)
    private String icp;

    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор адреса по стандарту RFC4122")
    @JsonProperty(Fields.UUID)
    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "notformaddrname")
    private String notFormAddrName;

    @Column(name = "addressname")
    private String addressName;

    @Column(name = "country")
    private String country;

    @Column(name = "zipcode")
    private String zipCode;

    @ManyToOne
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
    }
}
