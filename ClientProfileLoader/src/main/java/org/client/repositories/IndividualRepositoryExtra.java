package org.client.repositories;


import org.client.common.entity.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualRepositoryExtra extends JpaRepository<Individual, String> {
    Individual findByIcp(String icp);

}
