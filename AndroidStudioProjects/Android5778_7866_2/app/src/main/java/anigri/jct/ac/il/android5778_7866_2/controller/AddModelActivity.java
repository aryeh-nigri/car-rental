package anigri.jct.ac.il.android5778_7866_2.controller;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import anigri.jct.ac.il.android5778_7866_2.R;
import anigri.jct.ac.il.android5778_7866_2.model.backend.DB_Manager_Factory;
import anigri.jct.ac.il.android5778_7866_2.model.backend.EntitiesTools;

public class AddModelActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_model);

        findViews();
        numberPickerModelSeats.setMinValue(0);
        numberPickerModelSeats.setMaxValue(20);
        numberPickerModelSeats.setWrapSelectorWheel(false);
    }

    private EditText editTextModelId;
    private EditText editTextModelCompanyName;
    private EditText editTextModelModelName;
    private EditText editTextModelTankVolume;
    private RadioGroup radioGroupModelGearbox;
    private RadioButton radioButtonModelAutomatic;
    private RadioButton radioButtonModelManual;
    private NumberPicker numberPickerModelSeats;
    private ImageButton addModelButton;


    private void findViews() {
        editTextModelId = (EditText)findViewById( R.id.editTextModelId );
        editTextModelCompanyName = (EditText)findViewById( R.id.editTextModelCompanyName );
        editTextModelModelName = (EditText)findViewById( R.id.editTextModelModelName );
        editTextModelTankVolume = (EditText)findViewById( R.id.editTextModelTankVolume );
        radioGroupModelGearbox = (RadioGroup)findViewById( R.id.radioGroupModelGearbox );
        radioButtonModelAutomatic = (RadioButton)findViewById( R.id.radioButtonModelAutomatic );
        radioButtonModelManual = (RadioButton)findViewById( R.id.radioButtonModelManual );
        numberPickerModelSeats = (NumberPicker)findViewById( R.id.numberPickerModelSeats );
        addModelButton = (ImageButton)findViewById( R.id.addModelButton );

        addModelButton.setOnClickListener( this );
    }


    @Override
    public void onClick(View v) {
        if ( v == addModelButton ) {
            addModel();
        }
    }

    private void addModel(){
        final ContentValues values = new ContentValues();
        try{
            long id = Long.valueOf(editTextModelId.getText().toString());
            int seats = numberPickerModelSeats.getValue();
            double tankVolume = Double.valueOf(editTextModelTankVolume.getText().toString());
            String gearbox = "";

            int checkedRadioButtonId = radioGroupModelGearbox.getCheckedRadioButtonId();
            if (checkedRadioButtonId == -1) {
                throw new Exception("Gearbox type not selected!");
            }
            else{
                if (checkedRadioButtonId == R.id.radioButtonModelAutomatic) {
                    gearbox = radioButtonModelAutomatic.getText().toString().toUpperCase();
                }
                else if(checkedRadioButtonId == R.id.radioButtonModelManual){
                    gearbox = radioButtonModelManual.getText().toString().toUpperCase();
                }
            }

            values.put(EntitiesTools.ModelConst.ID, id);
            values.put(EntitiesTools.ModelConst.TANK_VOLUME, tankVolume);
            values.put(EntitiesTools.ModelConst.SEATS, seats);
            values.put(EntitiesTools.ModelConst.COMPANY_NAME, editTextModelCompanyName.getText().toString());
            values.put(EntitiesTools.ModelConst.MODEL_NAME, editTextModelModelName.getText().toString());
            values.put(EntitiesTools.ModelConst.GEARBOX, gearbox);

            new AsyncTask<Void, Void, Long>(){

                @Override
                protected Long doInBackground(Void... voids) {
                    return DB_Manager_Factory.getManager().addModel(values);
                }
            }.execute();

            this.finish();
        }
        catch (Exception e){
            Toast.makeText(getBaseContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


}
