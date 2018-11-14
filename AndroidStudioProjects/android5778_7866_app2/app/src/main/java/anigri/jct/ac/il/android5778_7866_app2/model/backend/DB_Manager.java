package anigri.jct.ac.il.android5778_7866_app2.model.backend;

import android.content.ContentValues;

import java.util.List;

import anigri.jct.ac.il.android5778_7866_app2.model.entities.*;


/**
 * Created by Bruno on 11/01/2018.
 */

public interface DB_Manager {

    boolean clientAlreadyExists(long id);
    long addClient(ContentValues contentValues);
    double updateCar(long id, double newKilometers);
    long updateClient(long id, String password);
    List<Client> getClients();
    List<Branch> getBranches();
    List<Car> getCars();
    List<Car> getAvailableCars();
    Branch findBranchById(long id);
    Client findClientById(long id);
    Car findCarById(long id);
    Car findCarByClient(long clientId);
    List<Reservation> findReservationsByClient(long clientId);
    Model findModelById(long id);
    Reservation findReservationById(long id);
    List<Car> getAvailableCarsFromSpecificBranch(long branchId);
    List<Car> getAvailableCarsFromRange(double kilometersDistance);
    List<Model> getModels();
    List<Branch> getBranchesWithAvailableCarOfEachModel();
    List<Reservation> getOpenReservations();
    long addReservation(ContentValues contentValues);
    double closeReservation(long id, double kilometersPerformed, boolean filledTank);
    boolean didReservationsChanged();

}
