package org.client.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Documents {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String uuid;

    private String value;

    @OneToOne(mappedBy = "documents", cascade = CascadeType.ALL)
    private Individual individual;

}
