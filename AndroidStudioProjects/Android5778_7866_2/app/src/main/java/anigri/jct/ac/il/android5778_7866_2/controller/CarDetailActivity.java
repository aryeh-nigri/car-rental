package anigri.jct.ac.il.android5778_7866_2.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import anigri.jct.ac.il.android5778_7866_2.R;
import anigri.jct.ac.il.android5778_7866_2.model.backend.DB_Manager_Factory;
import anigri.jct.ac.il.android5778_7866_2.model.entities.Branch;
import anigri.jct.ac.il.android5778_7866_2.model.entities.Model;

public class CarDetailActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        fillLayout();
    }

    private void fillLayout() {
        //set the back (up) button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //find all our view components
        findViews();

        //collect our intent and populate our layout
        Intent intent = getIntent();

        Long id = intent.getLongExtra("id", 0);
        modelId = intent.getLongExtra("modelId", 0);
        branchId = intent.getLongExtra("branchId", 0);
        double kilometers = intent.getDoubleExtra("kilometers", 0);

        //set elements
        //image hardcoded for now
        carDetailImageView.setBackgroundResource(R.drawable.ic_directions_car_black_24dp);
        carDetailIdTextView.setText(Long.toString(id));
        carDetailModelIdButton.setText(Long.toString(modelId));
        carDetailBranchIdButton.setText(Long.toString(branchId));
        carDetailKilometersTextView.setText(Double.toString(kilometers));

        //set the title of this activity
        getSupportActionBar().setTitle("Details of Car #" + id);//for example
    }

    private ImageView carDetailImageView;
    private TextView carDetailIdTextView;
    private Button carDetailModelIdButton;
    private Button carDetailBranchIdButton;
    private TextView carDetailKilometersTextView;
    private long modelId;
    private long branchId;


    private void findViews() {
        carDetailImageView = (ImageView)findViewById( R.id.carDetailImageView );
        carDetailIdTextView = (TextView)findViewById( R.id.carDetailIdTextView );
        carDetailModelIdButton = (Button)findViewById( R.id.carDetailModelIdButton );
        carDetailBranchIdButton = (Button)findViewById( R.id.carDetailBranchIdButton );
        carDetailKilometersTextView = (TextView)findViewById( R.id.carDetailKilometersTextView );

        carDetailModelIdButton.setOnClickListener( this );
        carDetailBranchIdButton.setOnClickListener( this );
    }


    @Override
    public void onClick(View v) {
        if ( v == carDetailModelIdButton ) {
            openModelDetail();
        } else if ( v == carDetailBranchIdButton ) {
            openBranchDetail();
        }
    }

    private void openModelDetail(){
        new AsyncTask<Void, Void, Model>(){
            @Override
            protected Model doInBackground(Void... voids) {
                return DB_Manager_Factory.getManager().getModelById(modelId);
            }
            @Override
            protected void onPostExecute(Model model) {
                putModel(model);
            }
        }.execute();
    }

    private void putModel(Model model){
        Intent intent = new Intent(CarDetailActivity.this, ModelDetailActivity.class);

        intent.putExtra("id", model.getId());
        intent.putExtra("companyName", model.getCompanyName());
        intent.putExtra("modelName", model.getModelName());
        intent.putExtra("tankVolume", model.getTankVolume());
        intent.putExtra("gearbox", model.getGearbox().toString());//TODO verify if works
        intent.putExtra("seats", model.getSeats());

        startActivity(intent);
    }

    private void openBranchDetail(){
        new AsyncTask<Void, Void, Branch>(){
            @Override
            protected Branch doInBackground(Void... voids) {
                return DB_Manager_Factory.getManager().getBranchById(branchId);
            }
            @Override
            protected void onPostExecute(Branch branch) {
                putBranch(branch);
            }
        }.execute();
    }

    private void putBranch(Branch branch){
        Intent intent = new Intent(CarDetailActivity.this, BranchDetailActivity.class);

        intent.putExtra("id", branch.getId());
        intent.putExtra("city", branch.getCity());
        intent.putExtra("street", branch.getStreet());
        intent.putExtra("number", branch.getNumber());
        intent.putExtra("parkingSpace", branch.getParkingSpace());

        startActivity(intent);
    }

}
