package org.client.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;


@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="adr_uuid")
    private String uuid;

    private String notFormAddrName;

    private String addressName;

    private String country;

    private String zipCode;

    public Address(String adrUuid, String notFormAddrName, String addressName, String country, String zipCode) {
        this.uuid = adrUuid;
        this.notFormAddrName = notFormAddrName;
        this.addressName = addressName;
        this.country = country;
        this.zipCode = zipCode;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="individ_address",
            joinColumns=  @JoinColumn(name="address_uuid", referencedColumnName="adr_uuid"),
            inverseJoinColumns= @JoinColumn(name="individ_uuid", referencedColumnName="uuid") )
    private Collection<Individual> individuals;



}
