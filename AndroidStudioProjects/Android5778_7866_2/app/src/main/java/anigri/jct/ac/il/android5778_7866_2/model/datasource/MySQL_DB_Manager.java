package anigri.jct.ac.il.android5778_7866_2.model.datasource;

import android.content.ContentValues;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import anigri.jct.ac.il.android5778_7866_2.model.backend.DB_Manager;
import anigri.jct.ac.il.android5778_7866_2.model.backend.EntitiesTools;
import anigri.jct.ac.il.android5778_7866_2.model.entities.*;

/**
 * Created by Bruno on 15/01/2018.
 */

public class MySQL_DB_Manager implements DB_Manager {

    private final String UserName = "anigri";
    private final String WEB_URL = "http://" + UserName + ".vlab.jct.ac.il/CarRental/";

    private boolean updateFlag = false;

    private void SetUpdate()
    {
        updateFlag = true;
    }

    public void printLog(String message){
        Log.d(this.getClass().getName(), "\n" + message);
    }
    public void printLog(Exception message){
        Log.d(this.getClass().getName(), "Exception-->\n" + message);
    }


    @Override
    public boolean clientAlreadyExists(long id) {
        List<Client> clients = getClients();

        for(Client c : clients){
            if (id == c.getId())
                return true;   //   client exists
        }

        return false;   //   client do not exist
    }

    @Override
    public long addClient(ContentValues values) {
        try{
            String result = PHPTools.POST(WEB_URL + "addClient.php", values);
            long id = Long.parseLong(result);
            if(id > 0)
                SetUpdate();
            printLog("addClient:\n" + result);
            return id;
        } catch (IOException e){
            printLog("addClient:\n" + e);
            return -1;
        }
    }

    @Override
    public long addBranch(ContentValues values) {
        try{
            String result = PHPTools.POST(WEB_URL + "addBranch.php", values);
            long id = Long.parseLong(result);
            if(id > 0)
                SetUpdate();
            printLog("addBranch:\n" + result);
            return id;
        } catch (IOException e){
            printLog("addBranch:\n" + e);
            return -1;
        }
    }

    @Override
    public long addModel(ContentValues values) {
        try{
            String result = PHPTools.POST(WEB_URL + "addModel.php", values);
            long id = Long.parseLong(result);
            if(id > 0)
                SetUpdate();
            printLog("addModel:\n" + result);
            return id;
        } catch (IOException e){
            printLog("addModel:\n" + e);
            return -1;
        }
    }

    @Override
    public long addCar(ContentValues values) {
        try{
            String result = PHPTools.POST(WEB_URL + "addCar.php", values);
            long id = Long.parseLong(result);
            if(id > 0)
                SetUpdate();
            printLog("addCar:\n" + result);
            return id;
        } catch (IOException e){
            printLog("addCar:\n" + e);
            return -1;
        }
    }

    @Override
    public List<Model> getModels() {
        List<Model> result = new ArrayList<>();

        try{
            String str = PHPTools.GET(WEB_URL + "getModels.php");
            JSONArray array = new JSONObject(str).getJSONArray("models");

            for(int i = 0; i < array.length(); i++){
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues values = PHPTools.JsonToContentValues(jsonObject);
                Model model = EntitiesTools.ContentValuesToModel(values);
                result.add(model);
            }

            return result;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> getClients() {
        List<Client> result = new ArrayList<>();

        try{
            String str = PHPTools.GET(WEB_URL + "getClients.php");
            JSONArray array = new JSONObject(str).getJSONArray("clients");

            for(int i = 0; i < array.length(); i++){
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues values = PHPTools.JsonToContentValues(jsonObject);
                Client client = EntitiesTools.ContentValuesToClient(values);
                result.add(client);
            }

            return result;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Branch> getBranches() {
        List<Branch> result = new ArrayList<>();

        try{
            String str = PHPTools.GET(WEB_URL + "getBranches.php");
            JSONArray array = new JSONObject(str).getJSONArray("branches");

            for(int i = 0; i < array.length(); i++){
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues values = PHPTools.JsonToContentValues(jsonObject);
                Branch branch = EntitiesTools.ContentValuesToBranch(values);
                result.add(branch);
            }

            return result;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> getCars() {
        List<Car> result = new ArrayList<>();

        try{
            String str = PHPTools.GET(WEB_URL + "getCars.php");
            JSONArray array = new JSONObject(str).getJSONArray("cars");

            for(int i = 0; i < array.length(); i++){
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues values = PHPTools.JsonToContentValues(jsonObject);
                Car car = EntitiesTools.ContentValuesToCar(values);
                result.add(car);
            }

            return result;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //TODO implement below functions(with php if possible)
    public Branch getBranchById(long id){
        List<Branch> branches = getBranches();

        for(Branch branch : branches){
            if(branch.getId() == id)
                return branch;
        }

        return null;
    }

    public Model getModelById(long id){
        List<Model> models = getModels();

        for(Model model : models){
            if(model.getId() == id)
                return model;
        }

        return null;
    }

}
