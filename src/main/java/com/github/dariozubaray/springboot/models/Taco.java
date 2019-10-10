package com.github.dariozubaray.springboot.models;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "created_at")
    private Date createdAt;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @ManyToMany(targetEntity=Ingredient.class)
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

    @PrePersist
    public void createdAt() {
        this.createdAt = new Date();
    }

    public Long getId() {
        return this.id;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public String getName() {
        return this.name;
    }

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setIngredients(final List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Taco))
            return false;
        final Taco other = (Taco) o;
        if (!other.canEqual((Object) this))
            return false;
        final Object thisId = this.getId();
        final Object otherId = other.getId();
        if (thisId == null ? otherId != null : !thisId.equals(otherId))
            return false;
        final Object thisCreatedAt = this.getCreatedAt();
        final Object otherCreatedAt = other.getCreatedAt();
        if (thisCreatedAt == null ? otherCreatedAt != null
                : !thisCreatedAt.equals(otherCreatedAt))
            return false;
        final Object thisName = this.getName();
        final Object otherName = other.getName();
        if (thisName == null ? otherName != null : !thisName.equals(otherName))
            return false;
        final Object thisIngredients = this.getIngredients();
        final Object otherIngredients = other.getIngredients();
        if (thisIngredients == null ? otherIngredients != null
                : !thisIngredients.equals(otherIngredients))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Taco;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object thisId = this.getId();
        result = result * PRIME + (thisId == null ? 43 : thisId.hashCode());
        final Object thisCreatedAt = this.getCreatedAt();
        result = result * PRIME + (thisCreatedAt == null ? 43 : thisCreatedAt.hashCode());
        final Object thisName = this.getName();
        result = result * PRIME + (thisName == null ? 43 : thisName.hashCode());
        final Object thisIngredients = this.getIngredients();
        result = result * PRIME + (thisIngredients == null ? 43 : thisIngredients.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Taco(id=" + this.getId() + ", createdAt=" + this.getCreatedAt() + ", name="
                + this.getName() + ", ingredients=" + this.getIngredients() + ")";
    }

}
