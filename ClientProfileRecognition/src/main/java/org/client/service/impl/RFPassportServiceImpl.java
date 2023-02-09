package org.client.service.impl;

import org.client.Entity.Enums.Name.DictionaryNameMen;
import org.client.Entity.Enums.Name.DictionaryNameWomen;
import org.client.Entity.Enums.Patronymic.DictionaryPatronymicMen;
import org.client.Entity.Enums.Patronymic.DictionaryPatronymicWomen;
import org.client.Entity.Enums.Surname.DictionarySurnameFromAtoZ;
import org.client.Entity.Enums.Surname.DictionarySurnameFromItoN;
import org.client.Entity.Enums.Surname.DictionarySurnameFromOtoT;
import org.client.Entity.Enums.Surname.DictionarySurnameFromUtoEnd;
import org.client.Entity.RFPassport;
import org.client.service.RFPassportService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RFPassportServiceImpl implements RFPassportService {

    static final String deleteRFfromString = "РОС[А-ЯЁ]+ ФЕД[А-ЯЁ]+";
    static final String deletePassportIssuedFromString = "ПАС[А-ЯЁ]+ ВЫД[А-ЯЁ]+";
    static final String deleteAllEmptyLines = "(?m)^[ \\t]*\\r?\\n";
    static final String deleteAllNewLines = "\\n";
    static final String deleteAllExceptRusAndSpaces = "[^А-ЯЁа-яё ]";
    static final String deleteSpacesExceptOne = "( )+";


    public void FillSeriesAndNumber(RFPassport rfPassport, String stringFromScanForSeriesAndNumber) {
        Pattern series = Pattern.compile("\\d{2} \\d{2}");
        Pattern number = Pattern.compile("\\d{6}");

        Matcher matcherSeries = series.matcher(stringFromScanForSeriesAndNumber);
        Matcher matcherNumber = number.matcher(stringFromScanForSeriesAndNumber);

        while (matcherSeries.find()) {
            rfPassport.setSeries(matcherSeries.group());
            break;
        }

        while (matcherNumber.find()) {
            rfPassport.setNumber(matcherNumber.group());
            break;
        }
    }

    public void FillGender(RFPassport rfPassport, String stringFromScanForRemainder) {
        Pattern genderM = Pattern.compile("\\МУЖ.");
        Pattern genderW = Pattern.compile("\\ЖЕН.");

        Matcher matcherGenderM = genderM.matcher(stringFromScanForRemainder);
        Matcher matcherGenderW = genderW.matcher(stringFromScanForRemainder);

        while (matcherGenderM.find()) {
            rfPassport.setGender("МУЖ");
            break;
        }
        while (matcherGenderW.find()) {
            rfPassport.setGender("ЖЕН");
            break;
        }
    }

    public void FillReceiptDocDateBirthdateDepartmentDoc(RFPassport rfPassport, String stringFromScanForRemainder) {
        List<String> dates = new ArrayList<>();
        List<Integer> positionDates = new ArrayList<>();

        Pattern date = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
        Matcher dateMatcher = date.matcher(stringFromScanForRemainder);

        while (dateMatcher.find()) {
            positionDates.add(dateMatcher.start());
            positionDates.add(dateMatcher.end());
            dates.add(dateMatcher.group());
        }

        if (dates.size() == 2) {
            rfPassport.setReceiptDocDate(dates.get(0));
            rfPassport.setBirthdate(dates.get(1));
            rfPassport.setDepartmentDoc(stringFromScanForRemainder
                    .substring(0, positionDates.get(0))
                    .replaceAll(deleteRFfromString, "")
                    .replaceAll(deletePassportIssuedFromString, "")
                    .replaceAll(deleteAllEmptyLines, "")
                    .replaceAll(deleteAllNewLines, " ")); // Паспорт выдан кем:
            rfPassport.setBirthplace(stringFromScanForRemainder
                    .substring(positionDates.get(3))
                    .replaceAll(deletePassportIssuedFromString, "")
                    .replaceAll(deleteAllNewLines, " "));
        }
        if (dates.size() == 1) {
            if (positionDates.get(0) > (stringFromScanForRemainder.length() / 2)) {
                rfPassport.setBirthdate(dates.get(0));
                rfPassport.setBirthplace(stringFromScanForRemainder
                        .substring(positionDates.get(1))
                        .replaceAll(deletePassportIssuedFromString, "")
                        .replaceAll(deleteAllNewLines, " "));
            } else {
                rfPassport.setReceiptDocDate(dates.get(0));
                rfPassport.setDepartmentDoc(stringFromScanForRemainder
                        .substring(0, positionDates.get(0))
                        .replaceAll(deleteRFfromString, "")
                        .replaceAll(deletePassportIssuedFromString, "")
                        .replaceAll(deleteAllNewLines, " ")); // Паспорт выдан кем:
            }
        }
    }

    public void FillDivision(RFPassport rfPassport, String stringFromScanForRemainder) {
        Pattern division = Pattern.compile("\\d{3}\\-\\d{3}");
        Matcher divisionMatcher = division.matcher(stringFromScanForRemainder);
        while (divisionMatcher.find()) {
            rfPassport.setDivision(divisionMatcher.group());
            break;
        }
    }

    public void FillSNP(RFPassport rfPassport, String stringFromScanForRemainder) {
        int borderStart = 0;
        int borderEnd = stringFromScanForRemainder.length() - 1;

        if (rfPassport.getDivision() != null) {
            borderStart = stringFromScanForRemainder.lastIndexOf(rfPassport.getDivision());
        } else if (rfPassport.getReceiptDocDate() != null) {
            borderStart = stringFromScanForRemainder.lastIndexOf(rfPassport.getReceiptDocDate());
        } else if (rfPassport.getDepartmentDoc() != null) {
            borderStart = stringFromScanForRemainder.lastIndexOf(rfPassport.getDepartmentDoc());
        }
        if (rfPassport.getGender() != null) {
            borderEnd = stringFromScanForRemainder.indexOf(rfPassport.getGender());
        } else if (rfPassport.getBirthdate() != null) {
            borderEnd = stringFromScanForRemainder.indexOf(rfPassport.getBirthdate());
        } else if (rfPassport.getBirthplace() != null) {
            borderEnd = stringFromScanForRemainder.indexOf(rfPassport.getBirthplace());
        }

        String trimString = stringFromScanForRemainder.substring(borderStart, borderEnd)
                .replaceAll(deleteAllEmptyLines, "")
                .replaceAll(deleteAllNewLines, " ")
                .replaceAll(deleteAllExceptRusAndSpaces, "")
                .replaceAll(deleteSpacesExceptOne, " ");

        List<String> SNP = List.of(trimString.split(" "));
        SNP = SNP.stream().filter(x-> x.length()>0).toList();

        for (int i = 0; i < SNP.size(); i++) {

            if (rfPassport.getGender() == "ЖЕН" && rfPassport.getName() != null) {
                try {
                    DictionaryNameWomen.valueOf(SNP.get(i));
                    rfPassport.setName(SNP.get(i));
                } catch (IllegalArgumentException e) {
                }
            } else if (rfPassport.getGender() == "МУЖ" && rfPassport.getName() != null) {
                try {
                    DictionaryNameMen.valueOf(SNP.get(i));
                    rfPassport.setName(SNP.get(i));
                } catch (IllegalArgumentException e) {
                }
            } else {
                try {
                    DictionaryNameWomen.valueOf(SNP.get(i));
                    rfPassport.setName(SNP.get(i));
                } catch (IllegalArgumentException e) {
                }
                try {
                    DictionaryNameMen.valueOf(SNP.get(i));
                    rfPassport.setName(SNP.get(i));
                } catch (IllegalArgumentException e) {
                }
            }

            if (rfPassport.getGender() == "ЖЕН" && rfPassport.getPatronymic() != null) {
                try {
                    DictionaryPatronymicWomen.valueOf(SNP.get(i));
                    rfPassport.setPatronymic(SNP.get(i));
                } catch (IllegalArgumentException e) {
                }
            } else if (rfPassport.getGender() == "МУЖ" && rfPassport.getPatronymic() != null) {
                try {
                    DictionaryPatronymicMen.valueOf(SNP.get(i));
                    rfPassport.setPatronymic(SNP.get(i));
                } catch (IllegalArgumentException e) {
                }
            } else {
                try {
                    DictionaryPatronymicWomen.valueOf(SNP.get(i));
                    rfPassport.setPatronymic(SNP.get(i));
                } catch (IllegalArgumentException e) {
                }
                try {
                    DictionaryPatronymicMen.valueOf(SNP.get(i));
                    rfPassport.setPatronymic(SNP.get(i));
                } catch (IllegalArgumentException e) {
                }
            }

            if (SNP.get(i).charAt(0) <= 'З') {
                try {
                    DictionarySurnameFromAtoZ.valueOf(SNP.get(i));
                    rfPassport.setSurname(SNP.get(i));
                } catch (IllegalArgumentException e) {
                }
            } else if (SNP.get(i).charAt(0) <= 'Н') {
                try {
                    DictionarySurnameFromItoN.valueOf(SNP.get(i));
                    rfPassport.setSurname(SNP.get(i));
                } catch (IllegalArgumentException e) {
                }
            } else if (SNP.get(i).charAt(0) <= 'T') {
                try {
                    DictionarySurnameFromOtoT.valueOf(SNP.get(i));
                    rfPassport.setSurname(SNP.get(i));
                } catch (IllegalArgumentException e) {
                }
            } else {
                try {
                    DictionarySurnameFromUtoEnd.valueOf(SNP.get(i));
                    rfPassport.setSurname(SNP.get(i));
                } catch (IllegalArgumentException e) {
                }
            }
        }
    }
}
