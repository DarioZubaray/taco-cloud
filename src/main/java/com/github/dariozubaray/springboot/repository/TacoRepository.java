package com.github.dariozubaray.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import com.github.dariozubaray.springboot.models.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
