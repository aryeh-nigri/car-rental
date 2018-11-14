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
import anigri.jct.ac.il.android5778_7866_2.adapters.ModelArrayAdapter;
import anigri.jct.ac.il.android5778_7866_2.model.backend.DB_Manager_Factory;
import anigri.jct.ac.il.android5778_7866_2.model.entities.Model;

public class ModelListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_list);

        getListItems();
    }


    private void getListItems(){

        new AsyncTask<Void, Void, List<Model>>(){
            @Override
            protected List<Model> doInBackground(Void... voids) {
                return DB_Manager_Factory.getManager().getModels();
            }
            @Override
            protected void onPostExecute(List<Model> models) {
                fillListView(models);
            }
        }.execute();
    }

    private void fillListView(final List<Model> models){

        //create our new array adapter
        ArrayAdapter<Model> adapter = new ModelArrayAdapter(this, 0, models);

        //add event listener so we can handle clicks
        AdapterView.OnItemClickListener adapterViewListener = new AdapterView.OnItemClickListener()
        {
            //on click
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Model model = models.get(position);
                Intent intent = new Intent(ModelListActivity.this, ModelDetailActivity.class);

                intent.putExtra("id", model.getId());
                intent.putExtra("companyName", model.getCompanyName());
                intent.putExtra("modelName", model.getModelName());
                intent.putExtra("tankVolume", model.getTankVolume());
                intent.putExtra("gearbox", model.getGearbox().toString());//TODO verify if works
                intent.putExtra("seats", model.getSeats());

                startActivity(intent);
            }
        };

        ListView listView = (ListView) findViewById(R.id.modelsListView);
        listView.setAdapter(adapter);

        //set the listener to the list view
        listView.setOnItemClickListener(adapterViewListener);
    }

}
