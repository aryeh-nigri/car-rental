package anigri.jct.ac.il.android5778_7866_2.model.entities;

/**
 * Created by Bruno on 11/01/2018.
 */

public class Branch {

    private long id;
    private String city;
    private String street;
    private int number;
    private int parkingSpace;

    public Branch() {
    }

    public Branch(long id, String city, String street, int number, int parkingSpace) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.number = number;
        this.parkingSpace = parkingSpace;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(int parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number=" + number +
                '}';
    }
}
