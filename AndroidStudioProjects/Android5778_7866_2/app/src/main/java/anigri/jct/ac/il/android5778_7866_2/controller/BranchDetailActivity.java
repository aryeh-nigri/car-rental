package anigri.jct.ac.il.android5778_7866_2.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import anigri.jct.ac.il.android5778_7866_2.R;

public class BranchDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_detail);

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
        String city = intent.getStringExtra("city");
        String street = intent.getStringExtra("street");
        int number = intent.getIntExtra("number", 0);
        int parkingSpace = intent.getIntExtra("parkingSpace", 0);

        String address = street + " " + number;

        //set elements
        //image hardcoded for now
        branchDetailImageView.setBackgroundResource(R.drawable.ic_store_black_24dp);
        branchDetailCityTextView.setText(city);
        branchDetailIdTextView.setText(Long.toString(id));
        branchDetailParkingSpaceTextView.setText(Integer.toString(parkingSpace));
        branchDetailStreetAndNumberTextView.setText(address);

        //set the title of this activity
        getSupportActionBar().setTitle("Details of Branch #" + id);//for example
    }

    private ImageView branchDetailImageView;
    private TextView branchDetailIdTextView;
    private TextView branchDetailStreetAndNumberTextView;
    private TextView branchDetailCityTextView;
    private TextView branchDetailParkingSpaceTextView;


    private void findViews() {
        branchDetailImageView = (ImageView)findViewById( R.id.branchDetailImageView );
        branchDetailIdTextView = (TextView)findViewById( R.id.branchDetailIdTextView );
        branchDetailStreetAndNumberTextView = (TextView)findViewById( R.id.branchDetailStreetAndNumberTextView );
        branchDetailCityTextView = (TextView)findViewById( R.id.branchDetailCityTextView );
        branchDetailParkingSpaceTextView = (TextView)findViewById( R.id.branchDetailParkingSpaceTextView );
    }


}
