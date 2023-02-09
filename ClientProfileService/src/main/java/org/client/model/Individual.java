package org.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Schema(description = "Модель, описывающая пользоватяле в ЛК банка")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "individual")
public class Individual implements Serializable {

    @Schema(example = "1000000", description = "Идентификатор Клиентского профиля")
    @JsonProperty(Fields.ICP)
    @Column(name = "icp")
    @Size(min = 7, max = 100)
    private String icp;

    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор клиента по стандарту RFC4122")
    @JsonProperty(Fields.UUID)
    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "gender")
    private String gender;

    @Column(name = "placeofbirth")
    private String placeofbirth;

    @Column(name = "countryofbirth")
    private String countryofbirth;

    @Column(name = "birthdate")
    private Date birthdate;

    @OneToOne(mappedBy = "individual")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Wallet wallet;

    @OneToMany(mappedBy = "individual")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Address> address = new HashSet<>();

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
        wallet.setIndividual(this);
    }

    public String getUuid() {
        return uuid.toString();
    }

    public void setUuid(String uuid) {
        this.uuid = UUID.fromString(uuid);
    }

    public void addAddress(Address address) {
        this.address.add(address);
    }

    public void deleteAddress(Address address) {
        this.address.remove(address);
    }

    public static class Fields {
        public static final String UUID = "uuid";

        public static final String ICP = "icp";
    }

}
