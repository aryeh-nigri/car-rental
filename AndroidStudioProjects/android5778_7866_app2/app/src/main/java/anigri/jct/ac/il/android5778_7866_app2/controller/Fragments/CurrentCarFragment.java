package anigri.jct.ac.il.android5778_7866_app2.controller.Fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.List;

import anigri.jct.ac.il.android5778_7866_app2.R;
import anigri.jct.ac.il.android5778_7866_app2.model.backend.DB_Manager_Factory;
import anigri.jct.ac.il.android5778_7866_app2.model.entities.Car;
import anigri.jct.ac.il.android5778_7866_app2.model.entities.Reservation;

/**
 * Created by Bruno on 18/01/2018.
 */

public class CurrentCarFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_current_car, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        getActivity().setTitle("Current Car");
    }

    private ImageButton freeCarButton;
    private Reservation myReservation;

    private void findViews(View view) {
        freeCarButton = (ImageButton)view.findViewById( R.id.freeCarButton );

        freeCarButton.setOnClickListener( this );

        startCarDetail();
    }

    private void startCarDetail() {
        final long clientId = getArguments().getLong("clientId");

        new AsyncTask<Void, Void, List<Reservation>>(){
            @Override
            protected List<Reservation> doInBackground(Void... voids) {
                return DB_Manager_Factory.getManager().findReservationsByClient(clientId);
            }
            @Override
            protected void onPostExecute(List<Reservation> reservations) {
                if(reservations == null){
                    //show something else instead
                    myReservation = null;
                    openNoReservationFragment();
                } else{
                    if(!reservations.isEmpty()) {
                        myReservation = reservations.get(0);
                        Car car = DB_Manager_Factory.getManager().findCarById(myReservation.getCarId());
                        setCarDetail(car);
                    }
                }
            }
        }.execute();

    }

    private void openNoReservationFragment(){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.carContainerNoButton, new NoReservationFragment());
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void setCarDetail(Car car) {
        Bundle bundle = new Bundle();

        bundle.putLong("id", car.getId());
        bundle.putLong("modelId", car.getModelId());
        bundle.putLong("branchId", car.getBranchId());
        bundle.putDouble("kilometers", car.getKilometers());
        bundle.putString("modelPicture", car.getModelPicture());

        Fragment fragment = new CarDetailFragment();
        fragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.currentCarContainer, fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        if ( v == freeCarButton ) {
            if(myReservation != null){
                freeCar(v, myReservation.getId());
            }
        }
    }

    private void freeCar(final View v, final long id) {//todo free car(close reservation) and end this fragment

        Object[] objects = promptDialog(v.getContext());
        final double kilometers = (double) objects[0];
        final boolean filledTank = (boolean) objects[1];

        if(kilometers == 0){
            return;
        }

        new AsyncTask<Void, Void, Double>(){
            @Override
            protected Double doInBackground(Void... voids) {
                return DB_Manager_Factory.getManager().closeReservation(id,kilometers, filledTank);

            }
            @Override
            protected void onPostExecute(Double bill) {
                if(bill <= 0){
                    //show something else instead
                } else{
                    Snackbar.make(v, "Automatic payment of $" + bill, Snackbar.LENGTH_SHORT);
                    openNoReservationFragment();
                }
            }
        }.execute();
    }

    /**
     * Prompt dialog
     * it is used when you want to capture user input
     */
    private Object[] promptDialog(final Context context) {

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context);
        View mView = layoutInflaterAndroid.inflate(R.layout.close_reservation_dialog, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(context);
        alertDialogBuilderUserInput.setView(mView);

        final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.closeReservationKilometersEditText);
        final CheckBox closeReservationCheckBox = (CheckBox) mView.findViewById(R.id.closeReservationCheckBox);
        final double[] kilometers = new double[1];
        kilometers[0] = 0;
        final boolean[] filledTank = new boolean[1];
        filledTank[0] = false;

        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        // ToDo get user input here
                        kilometers[0] = Double.valueOf(userInputDialogEditText.getText().toString());
                        filledTank[0] = closeReservationCheckBox.isChecked();
                        dialogBox.cancel();
                    }
                })

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                kilometers[0] = 0;
                                filledTank[0] = false;
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.setTitle("Current kilometers");
        alertDialogAndroid.show();

        Object[] objects = new Object[]{kilometers[0], filledTank[0]};//should be EntrySet, but this works too

        return objects;
    }

}
