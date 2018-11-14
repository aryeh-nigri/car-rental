package anigri.jct.ac.il.android5778_7866_app2.model.datasource;

import android.content.ContentValues;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import anigri.jct.ac.il.android5778_7866_app2.model.backend.DB_Manager;
import anigri.jct.ac.il.android5778_7866_app2.model.backend.EntitiesTools;
import anigri.jct.ac.il.android5778_7866_app2.model.entities.Branch;
import anigri.jct.ac.il.android5778_7866_app2.model.entities.Car;
import anigri.jct.ac.il.android5778_7866_app2.model.entities.Client;
import anigri.jct.ac.il.android5778_7866_app2.model.entities.Model;
import anigri.jct.ac.il.android5778_7866_app2.model.entities.Reservation;

/**
 * Created by Bruno on 11/01/2018.
 */

public class MySQL_DB_Manager implements DB_Manager {

    private final String UserName = "anigri";
    private final String WEB_URL = "http://" + UserName + ".vlab.jct.ac.il/CarRental/";

    private boolean updateFlag = false;

    private static String URL = "http://" + "anigri" + ".vlab.jct.ac.il/CarRental/";
    private static int carsCount;

    static {
        carsCount = getAvailableCarsCount();
    }

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

    private void updateCarsCount(){
        carsCount = getAvailableCarsCount();
    }

    //IMPLEMENTATIONS
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
    public double updateCar(long id, double newKilometers) {
        try{
            ContentValues values = new ContentValues();
            values.put(EntitiesTools.CarConst.ID, id);
            values.put("newKilometers", newKilometers);

            String result = PHPTools.POST(WEB_URL + "updateCar.php", values);
            long idReturned = Long.parseLong(result);
            if(idReturned == id)
                SetUpdate();
            printLog("updateCar:\n" + result);
            return newKilometers;
        } catch (IOException e){
            printLog("updateCar:\n" + e);
        }
        return 0;
    }

    @Override
    public long updateClient(long id, String password) {
        try{
            ContentValues values = new ContentValues();
            values.put(EntitiesTools.ClientConst.ID, id);
            values.put(EntitiesTools.ClientConst.PASSWORD, password);

            String result = PHPTools.POST(WEB_URL + "updateClient.php", values);
            long idReturned = Long.parseLong(result);
            if(idReturned == id)
                SetUpdate();
            printLog("updateCar:\n" + result);
            return id;
        } catch (IOException e){
            printLog("updateCar:\n" + e);
        }
        return 0;
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

    private static int getAvailableCarsCount(){
        List<Car> result = new ArrayList<>();

        try{
            String str = PHPTools.GET(URL + "getAvailableCars.php");
            JSONArray array = new JSONObject(str).getJSONArray("cars");

            for(int i = 0; i < array.length(); i++){
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues values = PHPTools.JsonToContentValues(jsonObject);
                Car car = EntitiesTools.ContentValuesToCar(values);
                result.add(car);
            }

            return result.size();
        } catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Car> getAvailableCars() {
        List<Car> result = new ArrayList<>();

        try{
            String str = PHPTools.GET(WEB_URL + "getAvailableCars.php");
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

    @Override
    public Branch findBranchById(long id) {
        Branch branch = null;
        try{
            ContentValues values = new ContentValues();

            values.put(EntitiesTools.ModelConst.ID, id);

            String str = PHPTools.POST(WEB_URL + "getBranch.php", values);

            JSONArray array = new JSONObject(str).getJSONArray("branch");

            for(int i = 0; i < array.length(); i++){
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues v = PHPTools.JsonToContentValues(jsonObject);
                branch = EntitiesTools.ContentValuesToBranch(v);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return branch;
    }

    @Override
    public Client findClientById(long id) {
        Client client = null;
        try{
            ContentValues values = new ContentValues();

            values.put(EntitiesTools.ModelConst.ID, id);

            String str = PHPTools.POST(WEB_URL + "getClient.php", values);

            JSONArray array = new JSONObject(str).getJSONArray("client");

            for(int i = 0; i < array.length(); i++){
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues v = PHPTools.JsonToContentValues(jsonObject);
                client = EntitiesTools.ContentValuesToClient(v);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return client;
    }

    @Override
    public Car findCarById(long id) {
        Car car = null;
        try{
            ContentValues values = new ContentValues();

            values.put(EntitiesTools.CarConst.ID, id);

            String str = PHPTools.POST(WEB_URL + "getCar.php", values);

            JSONArray array = new JSONObject(str).getJSONArray("car");

            for(int i = 0; i < array.length(); i++){
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues v = PHPTools.JsonToContentValues(jsonObject);
                car = EntitiesTools.ContentValuesToCar(v);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return car;
    }

    @Override
    public Car findCarByClient(long clientId) {
        Car car = null;
        try{
            ContentValues values = new ContentValues();

            values.put(EntitiesTools.ReservationConst.CLIENT_ID, clientId);

            String str = PHPTools.POST(WEB_URL + "getCarOfClient.php", values);

            JSONArray array = new JSONObject(str).getJSONArray("car");

            for(int i = 0; i < array.length(); i++){
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues v = PHPTools.JsonToContentValues(jsonObject);
                car = EntitiesTools.ContentValuesToCar(v);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return car;
    }

    @Override
    public List<Reservation> findReservationsByClient(long clientId) {
        List<Reservation> result = new ArrayList<>();

        try{
            ContentValues values = new ContentValues();

            values.put(EntitiesTools.ReservationConst.CLIENT_ID, clientId);

            String str = PHPTools.POST(WEB_URL + "getReservationsOfClient.php", values);

            JSONArray array = new JSONObject(str).getJSONArray("reservations");

            for(int i = 0; i < array.length(); i++){
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues v = PHPTools.JsonToContentValues(jsonObject);
                Reservation reservation = EntitiesTools.ContentValuesToReservation(v);
                result.add(reservation);
            }

            return result;

        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Model findModelById(long id) {
        Model model = null;
        try{
            ContentValues values = new ContentValues();

            values.put(EntitiesTools.ModelConst.ID, id);

            String str = PHPTools.POST(WEB_URL + "getModel.php", values);

            JSONArray array = new JSONObject(str).getJSONArray("model");

            for(int i = 0; i < array.length(); i++){
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues v = PHPTools.JsonToContentValues(jsonObject);
                model = EntitiesTools.ContentValuesToModel(v);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return model;
    }

    @Override
    public Reservation findReservationById(long id) {
        Reservation reservation = null;
        try{
            ContentValues values = new ContentValues();

            values.put(EntitiesTools.ModelConst.ID, id);

            String str = PHPTools.POST(WEB_URL + "getReservation.php", values);

            JSONArray array = new JSONObject(str).getJSONArray("reservation");

            for(int i = 0; i < array.length(); i++){
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues v = PHPTools.JsonToContentValues(jsonObject);
                reservation = EntitiesTools.ContentValuesToReservation(v);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return reservation;
    }

    @Override
    public List<Car> getAvailableCarsFromSpecificBranch(long branchId) {
        List<Car> result = new ArrayList<>();
        try{
            ContentValues values = new ContentValues();

            values.put(EntitiesTools.CarConst.BRANCH_ID, branchId);

            String str = PHPTools.POST(WEB_URL + "getAvailableCarsFromBranch.php", values);
            JSONArray array = new JSONObject(str).getJSONArray("cars");

            for(int i = 0; i < array.length(); i++){
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues v = PHPTools.JsonToContentValues(jsonObject);
                Car car = EntitiesTools.ContentValuesToCar(v);
                result.add(car);
            }

            return result;

        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Car> getAvailableCarsFromRange(double kilometersDistance) {
        return null;//TODO discover what its supposed to do!
    }

    @Override
    public List<Model> getModels() {
        List<Model> result = new ArrayList<>();

        try{
            String str = PHPTools.GET(WEB_URL + "getModels.php");
            JSONArray array = new JSONObject(str).getJSONArray("carModels");

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
    } //bonus

    @Override
    public List<Branch> getBranchesWithAvailableCarOfEachModel() {
        return null;
    } //bonus

    @Override
    public List<Reservation> getOpenReservations() {
        List<Reservation> result = new ArrayList<>();

        try{
            String str = PHPTools.GET(WEB_URL + "getOpenReservations.php");
            JSONArray array = new JSONObject(str).getJSONArray("reservations");

            for(int i = 0; i < array.length(); i++){
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues values = PHPTools.JsonToContentValues(jsonObject);
                Reservation reservation = EntitiesTools.ContentValuesToReservation(values);
                result.add(reservation);
            }

            return result;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long addReservation(ContentValues values) {
        try{
            String result = PHPTools.POST(WEB_URL + "addReservation.php", values);
            long id = Long.parseLong(result);
            if(id > 0)
                SetUpdate();
            printLog("addReservation:\n" + result);
            return id;
        } catch (IOException e){
            printLog("addReservation:\n" + e);
            return -1;
        }
    }

    private void updateReservation(Reservation reservation){
        try{
            ContentValues values = new ContentValues();

            values.put(EntitiesTools.ReservationConst.ID, reservation.getId());
            values.put(EntitiesTools.ReservationConst.IS_OPEN, reservation.isOpen());
            values.put(EntitiesTools.ReservationConst.END_DATE, reservation.getEndDate());
            values.put(EntitiesTools.ReservationConst.END_KILOMETERS, reservation.getEndKilometers());
            values.put(EntitiesTools.ReservationConst.DID_FILLED_TANK, reservation.isDidFilledTank());
            values.put(EntitiesTools.ReservationConst.LITERS_FILLED, reservation.getLitersFilled());
            values.put(EntitiesTools.ReservationConst.FINAL_AMOUNT_FOR_BILLING, reservation.getFinalAmountForBilling());

            String result = PHPTools.POST(WEB_URL + "updateReservation.php", values);
            long id = Long.parseLong(result);
            if(id > 0)
                SetUpdate();
            printLog("updateReservation:\n" + result);
        } catch (IOException e){
            printLog("updateReservation:\n" + e);
        }
    }
    @Override
    public double closeReservation(long id, double kilometersPerformed, boolean filledTank) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date currentDate = new Date();


        List<Reservation> reservations = getOpenReservations();
        Reservation reservation = null;

        for(Reservation r : reservations){
            if(r.getId() == id){
                reservation = r;
                break;
            }
        }

        if(reservation != null){//id was found
            try{
                Date startDate = dateFormat.parse(reservation.getStartDate());
                reservation.setEndDate(dateFormat.format(currentDate));
                reservation.setEndKilometers(kilometersPerformed);
                reservation.setDidFilledTank(filledTank);

                Long ms = currentDate.getTime() - startDate.getTime();
                double hours = ms / 3600000.0;

                double billToPay = hours * 15.0;

                if(!reservation.isDidFilledTank()){
                    double kilometers = reservation.getEndKilometers() - reservation.getStartKilometers();
                    billToPay += kilometers * 0.2;
                }

                reservation.setFinalAmountForBilling(billToPay);
                reservation.setOpen(false);
                updateReservation(reservation);

                return billToPay;

            } catch (ParseException e){
                e.printStackTrace();
            }
        }

        return -1;
    }

    @Override
    public boolean didReservationsChanged() {
        List<Car> carsAvailableNow = getAvailableCars();
        if(carsAvailableNow != null) {
            int carsCountNow = carsAvailableNow.size();
            if (carsCountNow != carsCount) {
                updateCarsCount();
                return true;
            }
        }

        return false;
    }
}
