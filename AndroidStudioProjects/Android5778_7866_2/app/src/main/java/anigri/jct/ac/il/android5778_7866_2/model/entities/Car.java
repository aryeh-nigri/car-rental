package anigri.jct.ac.il.android5778_7866_2.model.entities;

/**
 * Created by Bruno on 11/01/2018.
 */

public class Car {

    private long id;
    private long modelId;
    private long branchId;
    private double kilometers;

    public Car() {
    }

    public Car(long id, long modelId, long branchId, double kilometers) {
        this.id = id;
        this.modelId = modelId;
        this.branchId = branchId;
        this.kilometers = kilometers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public long getBranchId() {
        return branchId;
    }

    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }

    public double getKilometers() {
        return kilometers;
    }

    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", modelId=" + modelId +
                ", branchId=" + branchId +
                '}';
    }
}
