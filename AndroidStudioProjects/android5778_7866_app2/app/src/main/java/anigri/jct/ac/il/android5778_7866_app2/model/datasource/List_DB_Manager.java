package anigri.jct.ac.il.android5778_7866_app2.model.datasource;

import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

import anigri.jct.ac.il.android5778_7866_app2.model.backend.DB_Manager;
import anigri.jct.ac.il.android5778_7866_app2.model.entities.*;

import static anigri.jct.ac.il.android5778_7866_app2.model.backend.EntitiesTools.*;

/**
 * Created by Bruno on 11/01/2018.
 */

public class List_DB_Manager implements DB_Manager {

    static List<Client> clients;
    static List<Branch> branches;
    static List<Model> models;
    static List<Car> cars;
    static List<Reservation> reservations;

    static {
        clients = new ArrayList<>();
        branches = new ArrayList<>();
        models = new ArrayList<>();
        cars = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    @Override
    public boolean clientAlreadyExists(long id) {

        for(Client c : clients){
            if(c.getId() == id)
                return true;
        }

        return false;
    }

    @Override
    public long addClient(ContentValues contentValues) {
        Client client = ContentValuesToClient(contentValues);

        long id = client.getId();

        if(clientAlreadyExists(id))
            return -1;

        clients.add(client);

        return id;
    }

    @Override
    public double updateCar(long id, double newKilometers) {
        Car car = null;

        for(Car c : cars){
            if(c.getId() == id){
                car = c;
                break;
            }
        }
        if(car == null)
            return -1.0;

        car.setKilometers(newKilometers);

        return newKilometers;
    }

    @Override
    public long updateClient(long id, String password) {
        return 0;
    }

    @Override
    public List<Client> getClients() {
        return null;
    }

    @Override
    public List<Branch> getBranches() {
        return null;
    }

    @Override
    public List<Car> getCars() {
        return null;
    }

    @Override
    public List<Car> getAvailableCars() {
        return null;
    }

    @Override
    public Branch findBranchById(long id) {
        return null;
    }

    @Override
    public Client findClientById(long id) {
        return null;
    }

    @Override
    public Car findCarById(long id) {
        return null;
    }

    @Override
    public Car findCarByClient(long clientId) {
        return null;
    }

    @Override
    public List<Reservation> findReservationsByClient(long clientId) {
        return null;
    }

    @Override
    public Model findModelById(long id) {
        return null;
    }

    @Override
    public Reservation findReservationById(long id) {
        return null;
    }

    @Override
    public List<Car> getAvailableCarsFromSpecificBranch(long branchId) {
        return null;
    }

    @Override
    public List<Car> getAvailableCarsFromRange(double kilometersDistance) {
        return null;
    }

    @Override
    public List<Model> getModels() {
        return null;
    }

    @Override
    public List<Branch> getBranchesWithAvailableCarOfEachModel() {
        return null;
    }

    @Override
    public List<Reservation> getOpenReservations() {
        return null;
    }

    @Override
    public long addReservation(ContentValues contentValues) {
        return 0;
    }

    @Override
    public double closeReservation(long id, double kilometersPerformed, boolean filledTank) {
        return 0;
    }

    @Override
    public boolean didReservationsChanged() {
        return false;
    }
}
