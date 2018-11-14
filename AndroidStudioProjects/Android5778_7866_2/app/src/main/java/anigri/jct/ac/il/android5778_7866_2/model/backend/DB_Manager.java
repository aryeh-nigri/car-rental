package anigri.jct.ac.il.android5778_7866_2.model.backend;

import android.content.ContentValues;

import java.util.List;

import anigri.jct.ac.il.android5778_7866_2.model.entities.*;

/**
 * Created by Bruno on 15/01/2018.
 */

public interface DB_Manager {

    //Check whether a user already exists in the data source.
    boolean clientAlreadyExists(long id);

    /**Add a new Client.
     * @param values
     * @return Client id
     */
    long addClient(ContentValues values);

    //Add a new branch.
    //void addBranch(ContentValues values);
    long addBranch(ContentValues values);

    //Add a model
    //void addModel(ContentValues values);
    long addModel(ContentValues values);

    //Add a car.
    //void addCar(ContentValues values);
    long addCar(ContentValues values);

    //Return a list of all existing models.
    //Cursor getModels();
    List<Model> getModels();

    //Return the entire list of users.
    //Cursor getClients();
    List<Client> getClients();

    //Return the list of all branches.
    //Cursor getBranches();
    List<Branch> getBranches();

    //Return the list of all cars.
    //Cursor getCars();
    List<Car> getCars();

    Branch getBranchById(long id);

    Model getModelById(long id);

}
