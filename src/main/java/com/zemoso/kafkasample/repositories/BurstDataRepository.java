package com.zemoso.kafkasample.repositories;

import com.zemoso.kafkasample.datasource.BurstData;
import org.springframework.data.repository.CrudRepository;

public interface BurstDataRepository extends CrudRepository<BurstData, Integer> {

}
