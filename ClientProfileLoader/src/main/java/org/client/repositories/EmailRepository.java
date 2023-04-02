package org.client.repositories;

import org.client.common.entity.Contacts.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email,String> {
    Email findEmailByUuid(String uuid);
}
