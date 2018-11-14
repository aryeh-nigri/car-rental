package anigri.jct.ac.il.android5778_7866_2.controller;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Toast;

import anigri.jct.ac.il.android5778_7866_2.R;
import anigri.jct.ac.il.android5778_7866_2.model.backend.DB_Manager_Factory;
import anigri.jct.ac.il.android5778_7866_2.model.backend.EntitiesTools;

public class AddBranchActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_branch);

        findViews();

        numberPickerParkingSpace.setMinValue(0);
        numberPickerParkingSpace.setMaxValue(10000);
        numberPickerParkingSpace.setWrapSelectorWheel(false);
    }


    private EditText editTextBranchId;
    private EditText editTextBranchCity;
    private EditText editTextBranchStreet;
    private EditText editTextBranchNumber;
    private NumberPicker numberPickerParkingSpace;
    private ImageButton addBranchButton;


    private void findViews() {
        editTextBranchId = (EditText)findViewById( R.id.editTextBranchId );
        editTextBranchCity = (EditText)findViewById( R.id.editTextBranchCity );
        editTextBranchStreet = (EditText)findViewById( R.id.editTextBranchStreet );
        editTextBranchNumber = (EditText)findViewById( R.id.editTextBranchNumber );
        numberPickerParkingSpace = (NumberPicker)findViewById( R.id.numberPickerParkingSpace );
        addBranchButton = (ImageButton)findViewById( R.id.addBranchButton );

        addBranchButton.setOnClickListener( this );
    }


    @Override
    public void onClick(View v) {
        if ( v == addBranchButton ) {
            addBranch();
        }
    }

    private void addBranch(){
        final ContentValues values = new ContentValues();
        try{
            long id = Long.valueOf(editTextBranchId.getText().toString());
            int numberAddress = Integer.valueOf(editTextBranchNumber.getText().toString());
            int parkingSpaces = numberPickerParkingSpace.getValue();

            values.put(EntitiesTools.BranchConst.ID, id);
            values.put(EntitiesTools.BranchConst.NUMBER, numberAddress);
            values.put(EntitiesTools.BranchConst.PARKING_SPACE, parkingSpaces);
            values.put(EntitiesTools.BranchConst.CITY, editTextBranchCity.getText().toString());
            values.put(EntitiesTools.BranchConst.STREET, editTextBranchStreet.getText().toString());

            new AsyncTask<Void, Void, Long>(){


                @Override
                protected Long doInBackground(Void... voids) {
                    return DB_Manager_Factory.getManager().addBranch(values);
                }
            }.execute();

            this.finish();
        }
        catch (Exception e){
            Toast.makeText(getBaseContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


}
