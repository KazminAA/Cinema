package models;

import java.util.Arrays;

/**
 * Created by Alex on 16.08.2016.
 */
public class Hall extends Entity {
    private String name;
    private int[] structure;

    public Hall(String name, int[] structure) {
        setName(name);
        setStructure(structure);
    }

    public Hall() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hall)) return false;
        if (!super.equals(o)) return false;

        Hall hall = (Hall) o;

        return getName().equals(hall.getName());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 43 * result + getName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return name + " " + Arrays.toString(structure);
    }
}
