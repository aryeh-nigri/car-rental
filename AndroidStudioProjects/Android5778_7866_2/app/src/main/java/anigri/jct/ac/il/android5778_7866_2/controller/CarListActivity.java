package anigri.jct.ac.il.android5778_7866_2.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import anigri.jct.ac.il.android5778_7866_2.R;
import anigri.jct.ac.il.android5778_7866_2.adapters.CarArrayAdapter;
import anigri.jct.ac.il.android5778_7866_2.model.backend.DB_Manager_Factory;
import anigri.jct.ac.il.android5778_7866_2.model.entities.Car;

public class CarListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        getListItems();
    }


    private void getListItems(){

        new AsyncTask<Void, Void, List<Car>>(){
            @Override
            protected List<Car> doInBackground(Void... voids) {
                return DB_Manager_Factory.getManager().getCars();
            }
            @Override
            protected void onPostExecute(List<Car> cars) {
                fillListView(cars);
            }
        }.execute();
    }

    private void fillListView(final List<Car> cars){

        //create our new array adapter
        ArrayAdapter<Car> adapter = new CarArrayAdapter(this, 0, cars);

        //add event listener so we can handle clicks
        AdapterView.OnItemClickListener adapterViewListener = new AdapterView.OnItemClickListener()
        {
            //on click
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Car car = cars.get(position);
                Intent intent = new Intent(CarListActivity.this, CarDetailActivity.class);

                intent.putExtra("id", car.getId());
                intent.putExtra("modelId", car.getModelId());
                intent.putExtra("branchId", car.getBranchId());
                intent.putExtra("kilometers", car.getKilometers());

                startActivity(intent);
            }
        };

        ListView listView = (ListView) findViewById(R.id.carsListView);
        listView.setAdapter(adapter);

        //set the listener to the list view
        listView.setOnItemClickListener(adapterViewListener);
    }

}
