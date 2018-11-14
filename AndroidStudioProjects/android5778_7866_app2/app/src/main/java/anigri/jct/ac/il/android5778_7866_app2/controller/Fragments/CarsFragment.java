package anigri.jct.ac.il.android5778_7866_app2.controller.Fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import anigri.jct.ac.il.android5778_7866_app2.R;
import anigri.jct.ac.il.android5778_7866_app2.adapters.CarArrayAdapter;
import anigri.jct.ac.il.android5778_7866_app2.model.backend.DB_Manager_Factory;
import anigri.jct.ac.il.android5778_7866_app2.model.backend.MyService;
import anigri.jct.ac.il.android5778_7866_app2.model.entities.Car;

/**
 * Created by Bruno on 04/02/2018.
 */

public class CarsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_cars, container, false);
    }

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

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Log.d("didReservationsChanged", "received!!!");
            Bundle extras = intent.getExtras();
            if(extras != null){
                String message = extras.getString("message");
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                getItems(getView());//TODO all this receiver
            }
        }
    };

    private void getItems(View view){
        getListItems(view);
    }


    private void getListItems(final View view){//when opened from main menu

        new AsyncTask<Void, Void, List<Car>>(){
            @Override
            protected List<Car> doInBackground(Void... voids) {
                return DB_Manager_Factory.getManager().getAvailableCars();
            }
            @Override
            protected void onPostExecute(List<Car> cars) {
                if(cars == null){
                    //show something else instead
                } else {
                    fillListView(cars, view);
                }
            }
        }.execute();
    }

    private void fillListView(final List<Car> cars, View view){//when opened from main menu
        Context context = view.getContext();

        //create our new array adapter
        ArrayAdapter<Car> adapter = new CarArrayAdapter(context, 0, cars);

        //add event listener so we can handle clicks
        AdapterView.OnItemClickListener adapterViewListener = new AdapterView.OnItemClickListener()
        {
            //on click
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //show car detail and branch detail
                Car car = cars.get(position);

                Bundle bundle = new Bundle();

                bundle.putLong("id", car.getId());
                bundle.putLong("modelId", car.getModelId());
                bundle.putLong("branchId", car.getBranchId());
                bundle.putDouble("kilometers", car.getKilometers());
                bundle.putString("modelPicture", car.getModelPicture());

                Fragment fragment = new CarDetailFragment();
                fragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentMainContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        };

        ListView listView = (ListView) view.findViewById(R.id.carsListView);
        listView.setAdapter(adapter);

        //set the listener to the list view
        listView.setOnItemClickListener(adapterViewListener);

        getActivity().setTitle("Cars");
    }

}
