package org.client.service.impl;


import org.client.common.dto.ContactMediumDto;
import org.client.common.dto.EmailDto;
import org.client.common.dto.IndividualDto;
import org.client.common.dto.PhoneNumberDto;
import org.client.common.dto.RFPassportDto;
import org.client.dto.shortIndividual.*;
import org.client.service.MaskingService;
import org.client.util.ClientContactsException;
import org.client.util.ClientInfoException;
import org.client.util.DocumentException;
import org.springframework.stereotype.Service;

import java.util.Collection;


/**
 * Masking full name, contacts and passport info
 */
@Service
public class MaskingServiceImpl implements MaskingService {

    /**
     * Replaces string characters to asterisk
     * @param text meant to be masked
     * @return masked text
     */

    @Override
    public String maskTextInfo(String text) {
        return text.replaceAll("[^.@+]", "*");
    }


    /**
     * Masks argument InvididualDto object fields and returns masked IndividualDto object
     * @param
     * @return masked IndividualDto object
     */
    @Override
    public IndividualDto maskIndividual(IndividualDto individual) {
        IndividualDto maskedIndividual = individual;
        if (individual != null) {
            maskedIndividual.setFullName(maskFullName(individual.getFullName(), individual.getSurname()));
            maskedIndividual.setSurname(maskSurname(individual.getSurname()));
//            if (individual.getContactMedium() != null) {
//                individual.getContactMedium().forEach(this::maskContacts);
//            }
//            maskedIndividual.setContactMedium(individual.getContactMedium());
            if (individual.getPassport() != null) {
                individual.getPassport().forEach(this::maskRFPassport);
            }
            maskedIndividual.setPassport(individual.getPassport());
        } else {
            throw new ClientInfoException();
        }
        return maskedIndividual;
    }

    @Override
    public IndividualShortDto maskIndividual(IndividualShortDto individual) {
        if (individual != null) {
            individual.setFullName(maskFullName(individual.getFullName(), individual.getSurname()));
            individual.setSurname(maskSurname(individual.getSurname()));
        } else {
            throw new ClientInfoException();
        }
        return individual;
    }

    @Override
    public IndividualDocStatusDto maskIndividual(IndividualDocStatusDto individual) {
        if (individual != null) {
            individual.setFullName(maskFullName(individual.getFullName(), individual.getSurname()));
            individual.setSurname(maskSurname(individual.getSurname()));
            individual.getPassport().forEach(this::maskRFPassport);
            individual.setPassport(individual.getPassport());
        } else {
            throw new ClientInfoException();
        }
        return individual;
    }

    @Override
    public IndividualClientDto maskIndividual(IndividualClientDto individual) {
        if (individual != null) {
            individual.setFullName(maskFullName(individual.getFullName(), individual.getSurname()));
            individual.setSurname(maskSurname(individual.getSurname()));
        } else {
            throw new ClientInfoException();
        }
        return individual;
    }

    /**
     * Masks client surname
     * @param surname
     * @return masked surname
     */
    @Override
    public String maskSurname(String surname) {
        if (surname != null) {
            return maskTextInfo(surname);
        } else {
            throw new ClientInfoException("Null surname");
        }
    }

    /**
     * Masks client fullName
     * @param fullName client full name
     * @param surname client surname to be masked
     * @return masked fullName
     */
    @Override
    public String maskFullName(String fullName, String surname) {
        if (fullName != null) {
            return fullName.replace(surname, maskTextInfo(surname));
        } else {
            throw new ClientInfoException("Null fullName");
        }
    }

    /**
     * Masks client emails and phone numbers
     * @param contactMedium client contacts
     * @return masked contactMedium object
     */
    @Override
    public ContactMediumDto maskContacts(ContactMediumDto contactMedium) {
        if (contactMedium != null) {
            if (contactMedium.getPhoneNumbers() != null) {
                Collection<PhoneNumberDto> phoneNumbers = contactMedium.getPhoneNumbers();
                phoneNumbers.forEach(phoneNumber -> {
                    String value = phoneNumber.getValue();
                    phoneNumber.setValue(value.substring(0, 5) + maskTextInfo(value.substring(5)));
                });
                contactMedium.setPhoneNumbers(phoneNumbers);
            } else {
                throw new ClientContactsException("No phone numbers found");
            }
            if (contactMedium.getEmails() != null) {
                Collection<EmailDto> emails = contactMedium.getEmails();
                emails.forEach(email -> {
                    String value = email.getValue();
                    email.setValue(value.substring(0, 4) + maskTextInfo(value.substring(4)));
                });
                contactMedium.setEmails(emails);
            } else {
                throw new ClientContactsException("No emails found");
            }
        } else {
            throw new ClientContactsException("No contacts found");
        }
        return contactMedium;
    }

    /**
     * Masks client passport surname, series and number
     * @param passport
     * @return masked RFPassport object
     */
    @Override
    public RFPassportDto maskRFPassport(RFPassportDto passport) {
        if (passport != null) {
            maskSurname(passport.getSurname());
            if (passport.getSeries() != null) {
                StringBuilder sb = new StringBuilder(passport.getSeries());
                sb.setCharAt(2,'*');
                sb.setCharAt(3, '*');
                passport.setSeries(sb.toString());
            } else {
                throw new DocumentException("No passport series found");
            }
            if (passport.getNumber() != null) {
                StringBuilder sb = new StringBuilder(passport.getNumber());
                sb.setCharAt(0, '*');
                sb.setCharAt(1, '*');
                sb.setCharAt(2, '*');
                passport.setNumber(sb.toString());
            } else {
                throw new DocumentException("No passport number found");
            }
        } else {
            throw new DocumentException("No passport found");
        }
        return passport;
    }

    @Override
    public RFPassportShortDto maskRFPassport(RFPassportShortDto passport) {
        if (passport != null) {
            if (passport.getSeries() != null) {
                StringBuilder sb = new StringBuilder(passport.getSeries());
                sb.setCharAt(2,'*');
                sb.setCharAt(3, '*');
                passport.setSeries(sb.toString());
            } else {
                throw new DocumentException("No passport series found");
            }
            if (passport.getNumber() != null) {
                StringBuilder sb = new StringBuilder(passport.getNumber());
                sb.setCharAt(0, '*');
                sb.setCharAt(1, '*');
                sb.setCharAt(2, '*');
                passport.setNumber(sb.toString());
            } else {
                throw new DocumentException("No passport number found");
            }
        } else {
            throw new DocumentException("No passport found");
        }
        return passport;
    }
    @Override
    public IndividualWalletDto maskIndividual(IndividualWalletDto individual) {
        if (individual.getWallet() != null ) {
            individual.setFullName(maskFullName(individual.getFullName(), individual.getSurname()));
            individual.setSurname(maskSurname(individual.getSurname()));
            //individual.setWallet(maskWallet(individual.getWallet()));
        } else {
            throw new ClientInfoException("User not wallet");
        }
        return individual;
    }

}

