package org.client.repositories;

import org.client.common.entity.Avatar;
import org.client.common.entity.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, String> {
    Avatar findByUuid(String uuid);
}
