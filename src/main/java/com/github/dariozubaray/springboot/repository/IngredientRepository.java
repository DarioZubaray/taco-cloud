package com.github.dariozubaray.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import com.github.dariozubaray.springboot.models.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String>{

}
