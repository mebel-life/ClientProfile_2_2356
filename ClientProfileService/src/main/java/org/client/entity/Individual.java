package org.client.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
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
    private String uuid;

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

    @OneToOne(mappedBy = "individual", cascade = CascadeType.ALL)
    private Wallet wallet;

    @OneToOne(mappedBy = "individual", cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "documents")
    private Documents documents;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rfPassport")
    private RFPassport rfPassport;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contacts")
    private ContactMedium contacts;

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
        wallet.setIndividual(this);
    }

    public void setAddress(Address address) {
        this.address = address;
        address.setIndividual(this);
    }

    public static class Fields {
        public static final String UUID = "uuid";

        public static final String ICP = "icp";
    }

}
