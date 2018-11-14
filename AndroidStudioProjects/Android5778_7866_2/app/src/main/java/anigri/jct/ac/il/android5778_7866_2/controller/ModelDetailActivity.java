package anigri.jct.ac.il.android5778_7866_2.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import anigri.jct.ac.il.android5778_7866_2.R;

public class ModelDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_detail);

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
        String companyName = intent.getStringExtra("companyName");
        String modelName = intent.getStringExtra("modelName");
        double tankVolume = intent.getDoubleExtra("tankVolume", 0);
        String gearbox = intent.getStringExtra("gearbox");
        int seats = intent.getIntExtra("seats", 0);

        //set elements
        //image hardcoded for now
        modelDetailImageView.setBackgroundResource(R.drawable.ic_directions_car_black_24dp);
        modelDetailIdTextView.setText(Long.toString(id));
        modelDetailCompanyNameTextView.setText(companyName);
        modelDetailModelNameTextView.setText(modelName);
        modelDetailTankVolumeTextView.setText(Double.toString(tankVolume));
        modelDetailGearboxTextView.setText(gearbox);
        modelDetailSeatsTextView.setText(Integer.toString(seats));

        //set the title of this activity
        getSupportActionBar().setTitle("Details of Model #" + id);//for example
    }

    private ImageView modelDetailImageView;
    private TextView modelDetailIdTextView;
    private TextView modelDetailCompanyNameTextView;
    private TextView modelDetailModelNameTextView;
    private TextView modelDetailTankVolumeTextView;
    private TextView modelDetailGearboxTextView;
    private TextView modelDetailSeatsTextView;


    private void findViews() {
        modelDetailImageView = (ImageView)findViewById( R.id.modelDetailImageView );
        modelDetailIdTextView = (TextView)findViewById( R.id.modelDetailIdTextView );
        modelDetailCompanyNameTextView = (TextView)findViewById( R.id.modelDetailCompanyNameTextView );
        modelDetailModelNameTextView = (TextView)findViewById( R.id.modelDetailModelNameTextView );
        modelDetailTankVolumeTextView = (TextView)findViewById( R.id.modelDetailTankVolumeTextView );
        modelDetailGearboxTextView = (TextView)findViewById( R.id.modelDetailGearboxTextView );
        modelDetailSeatsTextView = (TextView)findViewById( R.id.modelDetailSeatsTextView );
    }


}
