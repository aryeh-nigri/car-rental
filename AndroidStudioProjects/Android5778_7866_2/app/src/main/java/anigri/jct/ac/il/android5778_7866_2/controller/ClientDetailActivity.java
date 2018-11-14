package anigri.jct.ac.il.android5778_7866_2.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import anigri.jct.ac.il.android5778_7866_2.R;

public class ClientDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_detail);

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
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String telephone = intent.getStringExtra("telephone");
        String email = intent.getStringExtra("email");
        String creditCard = intent.getStringExtra("creditCard");

        String fullName = firstName + " " + lastName;

        //set elements
        //image hardcoded for now
        clientDetailImageView.setBackgroundResource(R.drawable.ic_account_circle_black_24dp);
        clientDetailNameTextView.setText(fullName);
        clientDetailIdTextView.setText(Long.toString(id));
        clientDetailTelephoneTextView.setText(telephone);
        clientDetailEmailTextView.setText(email);
        clientDetailCreditCardTextView.setText(creditCard);

        //set the title of this activity
        getSupportActionBar().setTitle("Details of Client #" + id);//for example
    }

    private ImageView clientDetailImageView;
    private TextView clientDetailNameTextView;
    private TextView clientDetailIdTextView;
    private TextView clientDetailTelephoneTextView;
    private TextView clientDetailEmailTextView;
    private TextView clientDetailCreditCardTextView;


    private void findViews() {
        clientDetailImageView = (ImageView)findViewById( R.id.clientDetailImageView );
        clientDetailNameTextView = (TextView)findViewById( R.id.clientDetailNameTextView );
        clientDetailIdTextView = (TextView)findViewById( R.id.clientDetailIdTextView );
        clientDetailTelephoneTextView = (TextView)findViewById( R.id.clientDetailTelephoneTextView );
        clientDetailEmailTextView = (TextView)findViewById( R.id.clientDetailEmailTextView );
        clientDetailCreditCardTextView = (TextView)findViewById( R.id.clientDetailCreditCardTextView );
    }


}
