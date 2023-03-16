package org.client.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private String uuid;

    private String notFormAddrName;

    private String addressName;

    private String country;

    private String zipCode;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="individ_address",
            joinColumns=  @JoinColumn(name="address_uuid", referencedColumnName="uuid"),
            inverseJoinColumns= @JoinColumn(name="individ_icp", referencedColumnName="icp") )
    private Collection<Individual> individuals;


}
