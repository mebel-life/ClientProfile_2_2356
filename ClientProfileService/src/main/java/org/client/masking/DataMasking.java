package org.client.masking;

public interface DataMasking {

    String maskName(String name, String surname, String patronymic);

    String maskPhone (String phone);

    String maskPassport(String series, String number);
}
