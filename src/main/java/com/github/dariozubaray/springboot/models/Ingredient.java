package com.github.dariozubaray.springboot.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ingredient {
    @Id
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE;
    }

    public Ingredient(final String id, final String name, final Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Type getType() {
        return this.type;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Ingredient))
            return false;
        final Ingredient other = (Ingredient) o;
        if (!other.canEqual((Object) this))
            return false;
        final Object thisId = this.getId();
        final Object otherId = other.getId();
        if (thisId == null ? otherId != null : !thisId.equals(otherId))
            return false;
        final Object thisName = this.getName();
        final Object otherName = other.getName();
        if (thisName == null ? otherName != null : !thisName.equals(otherName))
            return false;
        final Object thisType = this.getType();
        final Object otherType = other.getType();
        if (thisType == null ? otherType != null : !thisType.equals(otherType))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Ingredient;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object thisId = this.getId();
        result = result * PRIME + (thisId == null ? 43 : thisId.hashCode());
        final Object thisName = this.getName();
        result = result * PRIME + (thisName == null ? 43 : thisName.hashCode());
        final Object thisType = this.getType();
        result = result * PRIME + (thisType == null ? 43 : thisType.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Ingredient(id=" + this.getId() + ", name=" + this.getName() + ", type="
                + this.getType() + ")";
    }

}
