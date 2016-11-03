package models;

/**
 * Created by Alexandr on 16.09.2016.
 */
public class Structure extends Entity {
    int[] structure;
    int halllD;

    public Structure(int[] structure, int halllD) {
        setStructure(structure);
        setHalllD(halllD);
    }

    public Structure() {
    }

    public int[] getStructure() {
        return structure;
    }

    public void setStructure(int[] structure) {
        this.structure = structure;
    }

    public int getHalllD() {
        return halllD;
    }

    public void setHalllD(int halllD) {
        this.halllD = halllD;
    }
}
