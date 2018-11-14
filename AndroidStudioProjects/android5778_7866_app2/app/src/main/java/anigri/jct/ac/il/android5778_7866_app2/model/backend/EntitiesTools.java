package anigri.jct.ac.il.android5778_7866_app2.model.backend;

import android.content.ContentValues;

import anigri.jct.ac.il.android5778_7866_app2.model.entities.*;

/**
 * Created by Bruno on 11/01/2018.
 */

public class EntitiesTools {

    public static class ClientConst{
        public static final String ID = "_id";
        public static final String FIRST_NAME = "firstName";
        public static final String LAST_NAME = "lastName";
        public static final String TELEPHONE = "telephone";
        public static final String EMAIL = "email";
        public static final String CREDIT_CARD = "creditCard";
        public static final String PASSWORD = "password";
    }

    public static class ModelConst{
        public static final String ID = "_id";
        public static final String COMPANY_NAME = "companyName";
        public static final String MODEL_NAME = "modelName";
        public static final String TANK_VOLUME = "tankVolume";
        public static final String GEARBOX = "gearbox";
        public static final String SEATS = "seats";
        public static final String PICTURE_URL = "pictureURL";
    }

    public static class CarConst{
        public static final String ID = "_id";
        public static final String MODEL_ID = "modelId";
        public static final String BRANCH_ID = "branchId";
        public static final String KILOMETERS = "kilometers";
        public static final String MODEL_PICTURE = "modelPicture";
    }

    public static class BranchConst{
        public static final String ID = "_id";
        public static final String CITY = "city";
        public static final String STREET = "street";
        public static final String NUMBER = "number";
        public static final String PARKING_SPACE = "parkingSpace";
    }

    public static class ReservationConst{
        public static final String ID = "_id";
        public static final String CLIENT_ID = "clientId";
        public static final String CAR_ID = "carId";
        public static final String IS_OPEN = "isOpen";
        public static final String START_DATE = "startDate";
        public static final String END_DATE = "endDate";
        public static final String START_KILOMETERS = "startKilometers";
        public static final String END_KILOMETERS = "endKilometers";
        public static final String DID_FILLED_TANK = "didFilledTank";
        public static final String LITERS_FILLED = "litersFilled";
        public static final String FINAL_AMOUNT_FOR_BILLING = "finalAmountForBilling";
    }


    public static ContentValues ClientToContentValues(Client client){
        ContentValues values = new ContentValues();

        values.put(ClientConst.ID, client.getId());
        values.put(ClientConst.FIRST_NAME, client.getFirstName());
        values.put(ClientConst.LAST_NAME, client.getLastName());
        values.put(ClientConst.TELEPHONE, client.getTelephone());
        values.put(ClientConst.EMAIL, client.getEmail());
        values.put(ClientConst.CREDIT_CARD, client.getCreditCard());
        values.put(ClientConst.PASSWORD, client.getPassword());

        return values;
    }

    public static ContentValues ReservationToContentValues(Reservation reservation){
        ContentValues values = new ContentValues();

        values.put(ReservationConst.ID, reservation.getId());
        values.put(ReservationConst.CLIENT_ID, reservation.getClientId());
        values.put(ReservationConst.CAR_ID, reservation.getCarId());
        values.put(ReservationConst.IS_OPEN, reservation.isOpen());//boolean
        values.put(ReservationConst.START_DATE, reservation.getStartDate());
        values.put(ReservationConst.END_DATE, reservation.getEndDate());
        values.put(ReservationConst.START_KILOMETERS, reservation.getStartKilometers());
        values.put(ReservationConst.END_KILOMETERS, reservation.getEndKilometers());
        values.put(ReservationConst.DID_FILLED_TANK, reservation.isDidFilledTank());//boolean
        values.put(ReservationConst.LITERS_FILLED, reservation.getLitersFilled());
        values.put(ReservationConst.FINAL_AMOUNT_FOR_BILLING, reservation.getFinalAmountForBilling());

        return values;
    }

    public static ContentValues BranchToContentValues(Branch branch){
        ContentValues values = new ContentValues();

        values.put(BranchConst.ID, branch.getId());
        values.put(BranchConst.CITY, branch.getCity());
        values.put(BranchConst.STREET, branch.getStreet());
        values.put(BranchConst.NUMBER, branch.getNumber());
        values.put(BranchConst.PARKING_SPACE, branch.getParkingSpace());

        return values;
    }

    public static ContentValues ModelToContentValues(Model model){
        ContentValues values = new ContentValues();

        values.put(ModelConst.ID, model.getId());
        values.put(ModelConst.COMPANY_NAME, model.getCompanyName());
        values.put(ModelConst.MODEL_NAME, model.getModelName());
        values.put(ModelConst.TANK_VOLUME, model.getTankVolume());
        values.put(ModelConst.GEARBOX, model.getGearbox().toString());
        values.put(ModelConst.SEATS, model.getSeats());
        values.put(ModelConst.PICTURE_URL, model.getPictureURL());

        return values;
    }

    public static ContentValues CarToContentValues(Car car){

        ContentValues values = new ContentValues();

        values.put(CarConst.ID, car.getId());
        values.put(CarConst.MODEL_ID, car.getModelId());
        values.put(CarConst.BRANCH_ID, car.getBranchId());
        values.put(CarConst.KILOMETERS, car.getKilometers());
        values.put(CarConst.MODEL_PICTURE, car.getModelPicture());

        return values;
    }

    public static Client ContentValuesToClient(ContentValues values){

        Client client = new Client();

        client.setId(values.getAsLong(ClientConst.ID));
        client.setFirstName(values.getAsString(ClientConst.FIRST_NAME));
        client.setLastName(values.getAsString(ClientConst.LAST_NAME));
        client.setTelephone(values.getAsString(ClientConst.TELEPHONE));
        client.setEmail(values.getAsString(ClientConst.EMAIL));
        client.setCreditCard(values.getAsString(ClientConst.CREDIT_CARD));
        client.setPassword(values.getAsString(ClientConst.PASSWORD));

        return client;
    }

    public static Reservation ContentValuesToReservation(ContentValues values){

        Reservation reservation = new Reservation();

        reservation.setId(values.getAsLong(ReservationConst.ID));
        reservation.setClientId(values.getAsLong(ReservationConst.CLIENT_ID));
        reservation.setCarId(values.getAsLong(ReservationConst.CAR_ID));
        reservation.setOpen(values.getAsBoolean(ReservationConst.IS_OPEN));//boolean
        reservation.setStartDate(values.getAsString(ReservationConst.START_DATE));
        reservation.setEndDate(values.getAsString(ReservationConst.END_DATE));
        reservation.setStartKilometers(values.getAsDouble(ReservationConst.START_KILOMETERS));
        reservation.setEndKilometers(values.getAsDouble(ReservationConst.END_KILOMETERS));
        reservation.setDidFilledTank(values.getAsBoolean(ReservationConst.DID_FILLED_TANK));//boolean
        reservation.setLitersFilled(values.getAsDouble(ReservationConst.LITERS_FILLED));
        reservation.setFinalAmountForBilling(values.getAsDouble(ReservationConst.FINAL_AMOUNT_FOR_BILLING));

        return reservation;
    }

    public static Branch ContentValuesToBranch(ContentValues values){

        Branch branch = new Branch();

        branch.setId(values.getAsInteger(BranchConst.ID));
        branch.setCity(values.getAsString(BranchConst.CITY));
        branch.setStreet(values.getAsString(BranchConst.STREET));
        branch.setNumber(values.getAsInteger(BranchConst.NUMBER));
        branch.setParkingSpace(values.getAsInteger(BranchConst.PARKING_SPACE));

        return branch;
    }

    public static Model ContentValuesToModel(ContentValues values){

        Model model = new Model();

        model.setId(values.getAsInteger(ModelConst.ID));
        model.setCompanyName(values.getAsString(ModelConst.COMPANY_NAME));
        model.setModelName(values.getAsString(ModelConst.MODEL_NAME));
        model.setTankVolume(values.getAsDouble(ModelConst.TANK_VOLUME));
        model.setGearbox(Gearbox.valueOf(values.getAsString(ModelConst.GEARBOX)));
        model.setSeats(values.getAsInteger(ModelConst.SEATS));
        model.setPictureURL(values.getAsString(ModelConst.PICTURE_URL));

        return model;
    }

    public static Car ContentValuesToCar(ContentValues values){

        Car car = new Car();

        car.setId(values.getAsLong(CarConst.ID));
        car.setModelId(values.getAsInteger(CarConst.MODEL_ID));
        car.setBranchId(values.getAsInteger(CarConst.BRANCH_ID));
        car.setKilometers(values.getAsDouble(CarConst.KILOMETERS));
        car.setModelPicture(values.getAsString(CarConst.MODEL_PICTURE));

        return car;
    }


}
