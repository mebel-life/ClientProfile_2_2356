package org.client.repositories;

import org.client.common.entity.Contacts.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber,String> {
    PhoneNumber findPhoneNumberByUuid(String uuid);
}
