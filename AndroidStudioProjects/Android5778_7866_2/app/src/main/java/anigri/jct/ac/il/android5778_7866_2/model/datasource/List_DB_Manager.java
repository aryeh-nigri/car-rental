package anigri.jct.ac.il.android5778_7866_2.model.datasource;

import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

import anigri.jct.ac.il.android5778_7866_2.model.backend.DB_Manager;
import anigri.jct.ac.il.android5778_7866_2.model.backend.EntitiesTools;
import anigri.jct.ac.il.android5778_7866_2.model.entities.*;

/**
 * Created by Bruno on 15/01/2018.
 */

public class List_DB_Manager implements DB_Manager {


    static List<Client> clients;
    static List<Branch> branches;
    static List<Model> models;
    static List<Car> cars;

    static {
        clients = new ArrayList<>();
        branches = new ArrayList<>();
        models = new ArrayList<>();
        cars = new ArrayList<>();
    }

    @Override
    public boolean clientAlreadyExists(long id) {
        for(Client c : clients)
            if(c.getId() == id)
                return true;
        return false;
    }

    @Override
    public long addClient(ContentValues values) {
        Client client = EntitiesTools.ContentValuesToClient(values);
        clients.add(client);
        return client.getId();
    }

    @Override
    public long addModel(ContentValues values) {
        Model model = EntitiesTools.ContentValuesToModel(values);
        models.add(model);
        return model.getId();
    }

    @Override
    public long addCar(ContentValues values) {
        Car car = EntitiesTools.ContentValuesToCar(values);
        cars.add(car);
        return car.getId();
    }

    @Override
    public long addBranch(ContentValues values) {
        Branch branch = EntitiesTools.ContentValuesToBranch(values);
        branches.add(branch);
        return branch.getId();
    }

    @Override
    public List<Model> getModels() {
        return models;
    }

    @Override
    public List<Client> getClients() {
        return clients;
    }

    @Override
    public List<Branch> getBranches() {
        return branches;
    }

    @Override
    public List<Car> getCars() {
        return cars;
    }

    @Override
    public Branch getBranchById(long id) {
        List<Branch> branches = getBranches();

        for(Branch branch : branches){
            if(branch.getId() == id)
                return branch;
        }

        return null;
    }

    @Override
    public Model getModelById(long id) {
        List<Model> models = getModels();

        for(Model model : models){
            if(model.getId() == id)
                return model;
        }

        return null;
    }

}
