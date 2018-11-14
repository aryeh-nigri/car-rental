package anigri.jct.ac.il.android5778_7866_app2.model.entities;

/**
 * Created by Bruno on 11/01/2018.
 */

public class Model {

    private long id;
    private String companyName;
    private String modelName;
    private double tankVolume;
    private Gearbox gearbox;
    private int seats;
    private String pictureURL;

    public Model() {
    }

    public Model(long id, String companyName, String modelName, double tankVolume, Gearbox gearbox, int seats, String pictureURL) {
        this.id = id;
        this.companyName = companyName;
        this.modelName = modelName;
        this.tankVolume = tankVolume;
        this.gearbox = gearbox;
        this.seats = seats;
        this.pictureURL = pictureURL;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public double getTankVolume() {
        return tankVolume;
    }

    public void setTankVolume(double tankVolume) {
        this.tankVolume = tankVolume;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }


    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", modelName='" + modelName + '\'' +
                '}';
    }

}