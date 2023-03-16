package org.client.repository;

import org.client.model.JwtResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRepository extends CrudRepository<JwtResponse, String> {
}
