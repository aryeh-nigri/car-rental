package anigri.jct.ac.il.android5778_7866_2.model.entities;

/**
 * Created by Bruno on 11/01/2018.
 */

public class Reservation {

    private long id;
    private long clientId;
    private long carId;
    private boolean isOpen;
    private String startDate;
    private String endDate;
    private double startKilometers;
    private double endKilometers;
    private boolean didFilledTank;
    private double litersFilled;
    private double finalAmountForBilling;

    public Reservation() {
    }

    public Reservation(long id, long clientId, long carId, boolean isOpen, String startDate, String endDate, double startKilometers, double endKilometers, boolean didFilledTank, double litersFilled, double finalAmountForBilling) {
        this.id = id;
        this.clientId = clientId;
        this.carId = carId;
        this.isOpen = isOpen;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startKilometers = startKilometers;
        this.endKilometers = endKilometers;
        this.didFilledTank = didFilledTank;
        this.litersFilled = litersFilled;
        this.finalAmountForBilling = finalAmountForBilling;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getStartKilometers() {
        return startKilometers;
    }

    public void setStartKilometers(double startKilometers) {
        this.startKilometers = startKilometers;
    }

    public double getEndKilometers() {
        return endKilometers;
    }

    public void setEndKilometers(double endKilometers) {
        this.endKilometers = endKilometers;
    }

    public boolean isDidFilledTank() {
        return didFilledTank;
    }

    public void setDidFilledTank(boolean didFilledTank) {
        this.didFilledTank = didFilledTank;
    }

    public double getLitersFilled() {
        return litersFilled;
    }

    public void setLitersFilled(double litersFilled) {
        this.litersFilled = litersFilled;
    }

    public double getFinalAmountForBilling() {
        return finalAmountForBilling;
    }

    public void setFinalAmountForBilling(double finalAmountForBilling) {
        this.finalAmountForBilling = finalAmountForBilling;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", carId=" + carId +
                '}';
    }

}
