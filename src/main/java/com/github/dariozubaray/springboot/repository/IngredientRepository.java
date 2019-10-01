package com.github.dariozubaray.springboot.repository;

import com.github.dariozubaray.springboot.models.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save(Ingredient ingredient);
}
