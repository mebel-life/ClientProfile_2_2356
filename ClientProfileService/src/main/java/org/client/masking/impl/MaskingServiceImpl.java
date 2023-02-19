package org.client.masking.impl;

import org.client.masking.MaskingService;

import java.util.Arrays;


public class MaskingServiceImpl implements MaskingService {



    @Override
    public String maskName(String name, String surname, String patronymic) {

        /*
        Если отчество не null (отчество есть), то маскируем отчество используя цикл
        Далее, рассматриваем случаи:
        1) Если имя и фамилия null
        2) Если только имя null
        3) Если только фамилия null
        Если отчества нет, то передаем только имя и фамилию

         */
        String maskedPatronymic;

        if (!(patronymic.isEmpty())) {
            char[] charPatronymic = patronymic.toCharArray();
            for (int i = 0; i < charPatronymic.length - 1; i++) {
                charPatronymic[i + 1] = '*';
            }
            maskedPatronymic = Arrays.toString(charPatronymic);
            if (name == null && surname == null) {
                return maskedPatronymic;
            } else if (name == null) {
                return surname + " " + maskedPatronymic;
            } else if (surname == null) {
                return name + maskedPatronymic;
            }
            return name + " " + surname + " " + Arrays.toString(charPatronymic);

        } else
            return name + " " + surname;

    }

    @Override
    public String maskPhone(String phone) {
        //1. Очищаем номер от ненужных символом
        phone = phone.replaceAll("\\(", "");
        phone = phone.replaceAll("\\)", "");
        phone = phone.replaceAll(" ", "");
        phone = phone.replaceAll("\\+", "");
        phone = phone.replaceAll("-", "");
        int digits = phone.length();

        //2. Проверка все ли символы - цифры
        boolean isOnlyDigits = true;
        for(int i = 0; i < phone.length() && isOnlyDigits; i++) {
            if(!Character.isDigit(phone.charAt(i))) {
                isOnlyDigits = false;
            }
        }
        String firstDigits;
        String lastDigits;
        String countryCode;
        /*
        Если нам передали только числа, то далее рассматриваем различные варианты кода стран,
        максимальное количество цифр в коде страны - 4 (посмотрела на коды всех стран)
        разбиваем номер телефона на три составляющие
        1 - код страны
        2 - первые цифры
        3 - последние цифры
        Далее, составляем из них маскированный номер
         */
        if (isOnlyDigits) {
            switch (digits) {
                case 11: // 8 9150065422
                    countryCode = phone.substring(0, digits - 10);
                    firstDigits = phone.substring(1, 2);
                    lastDigits = phone.substring(digits - 2);
                    phone = countryCode + firstDigits + "*******-" + lastDigits;
                    break;
                case 12:       // 996 201234567
                    countryCode = phone.substring(0, digits - 11);
                    firstDigits = phone.substring(1, 3);
                    lastDigits = phone.substring(digits - 3);
                    phone = countryCode + firstDigits + "********-" + lastDigits;
                    break;
                case 13: // 1869 23556632
                    countryCode = phone.substring(0, digits - 12);
                    firstDigits = phone.substring(1, 4);
                    lastDigits = phone.substring(digits - 4);
                    phone = countryCode + firstDigits + "********-" + lastDigits;
                    break;
                default:
                    phone = "****";
            }
        }

        return phone;
    }

    @Override
    public String maskPassport(String series, String number) {
        String maskedSeries;
        String maskedNumber;

        //Маскируем НОМЕР паспорта. Чтобы замаскировать цифры, расположенные в середине номера, делим длину на 2
        char charNumber[]=number.toCharArray();
        int length=charNumber.length;
        int halfLength=length/2;
        charNumber[halfLength]='*';
        if(length%2==0)
        {
            charNumber[halfLength-1]='*';
        }
        maskedNumber = new String(charNumber);

        /*
        Маскируем серию паспорта, если в серии меньше 4 цифр, то шифруем все цифры. А если их 4 и более, то
        маскируем последние два числа

         */
        if (!(series == null)) {
            if (series.length() == 1) {
                maskedSeries = "*" ;
            } else if (series.length() == 2) {
                maskedSeries = "**";
            } else if (series.length() == 3) {
                maskedSeries = "***";
            } else {
                maskedSeries = series.substring(0, 2) + "**";
            }
        } else {
            return maskedNumber;
        }
        return maskedSeries + " " + maskedNumber;
    }

}

