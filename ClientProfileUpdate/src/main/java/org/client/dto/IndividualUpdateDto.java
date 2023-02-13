package org.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualUpdateDto {

    private String name;

    private String surname;

    private String patronymic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndividualUpdateDto that = (IndividualUpdateDto) o;
        return Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(patronymic, that.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, patronymic);
    }
}
