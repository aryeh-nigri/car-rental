package anigri.jct.ac.il.android5778_7866_app2.controller.Fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import anigri.jct.ac.il.android5778_7866_app2.R;
import anigri.jct.ac.il.android5778_7866_app2.adapters.CarArrayAdapter;
import anigri.jct.ac.il.android5778_7866_app2.controller.MainActivity;
import anigri.jct.ac.il.android5778_7866_app2.model.backend.DB_Manager_Factory;
import anigri.jct.ac.il.android5778_7866_app2.model.backend.EntitiesTools;
import anigri.jct.ac.il.android5778_7866_app2.model.backend.MyService;
import anigri.jct.ac.il.android5778_7866_app2.model.entities.Car;
import anigri.jct.ac.il.android5778_7866_app2.model.entities.Reservation;

/**
 * Created by Bruno on 18/01/2018.
 */

public class BranchCarsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_cars, container, false);
    }

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();
            if(extras != null){
                String message = extras.getString("message");
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                getItems(getView());//TODO all this receiver
            }
        }
    };

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = view.getContext();
        context.startService(new Intent(context, MyService.class));

        getItems(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Context context = getView().getContext();
        context.stopService(new Intent(context, MyService.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(myReceiver, new IntentFilter("il.ac.jct.UPDATE"));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(myReceiver);
    }

    private void getItems(View view){
        Context context = view.getContext();

        Bundle bundle = this.getArguments();
        long id = bundle.getLong("id");
        getListItemsBranch(id, view);
        //Toast.makeText(context, "when opened from branchesList", Toast.LENGTH_LONG).show();
    }

    private void getListItemsBranch(final long id, final View view) {//when opened from branchesList
        new AsyncTask<Void, Void, List<Car>>(){
            @Override
            protected List<Car> doInBackground(Void... voids) {
                return DB_Manager_Factory.getManager().getAvailableCarsFromSpecificBranch(id);
            }
            @Override
            protected void onPostExecute(List<Car> cars) {
                if(cars == null){
                    //show something else instead
                } else {
                    fillListViewBranch(cars, view);
                }
            }
        }.execute();
    }

    private void fillListViewBranch(final List<Car> cars, final View view) {//when opened from branchesList
        final Context context = view.getContext();

        //create our new array adapter
        ArrayAdapter<Car> adapter = new CarArrayAdapter(context, 0, cars);

        //add event listener so we can handle clicks
        AdapterView.OnItemClickListener adapterViewListener = new AdapterView.OnItemClickListener()
        {
            //on click
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //automatic add reservation
                Car car = cars.get(position);
                long carId = car.getId();
                long clientId = ((MainActivity)getActivity()).getClientId();
                long reservationId = carId + clientId;//this way, there wont be duplicates
                double startKilometers = car.getKilometers();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date currentDate = new Date();
                String startDate = dateFormat.format(currentDate);


                Reservation reservation = new Reservation(reservationId, clientId, carId, true, startDate, "empty", startKilometers, 0.0, false, 0.0, 0.0);

                confirmDialog(reservation, context);
                //addReservation(reservation, context);
            }
        };

        ListView listView = (ListView) view.findViewById(R.id.carsListView);
        listView.setAdapter(adapter);

        //set the listener to the list view
        listView.setOnItemClickListener(adapterViewListener);

        //Toast.makeText(context, "cars of branch", Toast.LENGTH_SHORT);
    }

    private void confirmDialog(final Reservation reservation, final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirm reservation");
        builder.setMessage("You are about to reserve the selected car. Do you really want to proceed ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(context, "Reservation in process...", Toast.LENGTH_SHORT).show();
                addReservation(reservation, context);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(context, "You've changed your mind", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }

    private void addReservation(Reservation reservation, final Context context){

        try{
            final ContentValues values = EntitiesTools.ReservationToContentValues(reservation);

            new AsyncTask<Void, Void, Long>(){
                @Override
                protected void onPostExecute(Long aLong) {
                    Toast.makeText(context, "Reservation ID: " + aLong, Toast.LENGTH_LONG).show();
                }

                @Override
                protected Long doInBackground(Void... voids) {
                    return DB_Manager_Factory.getManager().addReservation(values);
                }
            }.execute();

        }
        catch (Exception e){
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}