package dto;


import models.Entity;

import java.util.Arrays;

/**
 * Created by Alex on 16.08.2016.
 */
public class HallDTO extends Entity {
    private String name;
    private int[] structure;

    public HallDTO(String name, int[] structure) {
        setName(name);
        setStructure(structure);
    }

    public HallDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getStructure() {
        return structure;
    }

    public void setStructure(int[] structure) {
        this.structure = structure;
    }

    @Override
    public String toString() {
        return name + " " + Arrays.toString(structure);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HallDTO)) return false;
        if (!super.equals(o)) return false;

        HallDTO hall = (HallDTO) o;

        return getName().equals(hall.getName());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 43 * result + getName().hashCode();
        return result;
    }
}
