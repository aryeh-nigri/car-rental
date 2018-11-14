package anigri.jct.ac.il.android5778_7866_2.controller;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import anigri.jct.ac.il.android5778_7866_2.R;
import anigri.jct.ac.il.android5778_7866_2.model.backend.DB_Manager_Factory;
import anigri.jct.ac.il.android5778_7866_2.model.backend.EntitiesTools;

public class AddCarActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        findViews();
    }

    private EditText editTextCarId;
    private Spinner spinnerCarModel;
    private Spinner spinnerCarBranch;
    private EditText editTextCarKilometers;
    private ImageButton addCarButton;


    private void findViews() {
        editTextCarId = (EditText)findViewById( R.id.editTextCarId );
        spinnerCarModel = (Spinner)findViewById( R.id.spinnerCarModel );
        spinnerCarBranch = (Spinner)findViewById( R.id.spinnerCarBranch );
        editTextCarKilometers = (EditText)findViewById( R.id.editTextCarKilometers );
        addCarButton = (ImageButton)findViewById( R.id.addCarButton );

        addCarButton.setOnClickListener( this );
    }


    @Override
    public void onClick(View v) {
        if ( v == addCarButton ) {
            addCar();
        }
    }

    private void addCar(){
        final ContentValues values = new ContentValues();
        try{//TODO check if getSelectedItemId works; if not, getSelectedItem as Object, cast it and than get id
            long id = Long.valueOf(editTextCarId.getText().toString());
            long modelId = spinnerCarModel.getSelectedItemId();
            long branchId = spinnerCarBranch.getSelectedItemId();
            double kilometers = Double.valueOf(editTextCarKilometers.getText().toString());

            values.put(EntitiesTools.CarConst.ID, id);
            values.put(EntitiesTools.CarConst.MODEL_ID, modelId);
            values.put(EntitiesTools.CarConst.BRANCH_ID, branchId);
            values.put(EntitiesTools.CarConst.KILOMETERS, kilometers);

            new AsyncTask<Void, Void, Long>(){

                @Override
                protected Long doInBackground(Void... voids) {
                    return DB_Manager_Factory.getManager().addCar(values);
                }
            }.execute();

            this.finish();
        }
        catch (Exception e){
            Toast.makeText(getBaseContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


}
