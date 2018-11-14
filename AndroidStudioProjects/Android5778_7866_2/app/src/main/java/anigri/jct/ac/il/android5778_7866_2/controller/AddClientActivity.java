package anigri.jct.ac.il.android5778_7866_2.controller;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import anigri.jct.ac.il.android5778_7866_2.R;
import anigri.jct.ac.il.android5778_7866_2.model.backend.DB_Manager_Factory;
import anigri.jct.ac.il.android5778_7866_2.model.backend.EntitiesTools;

public class AddClientActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        findViews();
    }


    private EditText editTextId;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextPhone;
    private EditText editTextEmail;
    private EditText editTextCreditCard;
    private ImageButton addClientButton;


    private void findViews() {
        editTextId = (EditText)findViewById( R.id.editTextClientId );
        editTextFirstName = (EditText)findViewById( R.id.editTextClientFirstName );
        editTextLastName = (EditText)findViewById( R.id.editTextClientLastName );
        editTextPhone = (EditText)findViewById( R.id.editTextClientPhone );
        editTextEmail = (EditText)findViewById( R.id.editTextClientEmail );
        editTextCreditCard = (EditText)findViewById( R.id.editTextClientCreditCard );
        addClientButton = (ImageButton)findViewById( R.id.addClientButton );

        addClientButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if ( v == addClientButton ) {
            addClient();
        }
    }


    private void addClient(){

        final ContentValues values = new ContentValues();

        try{
            long id = Long.valueOf(editTextId.getText().toString());

            values.put(EntitiesTools.ClientConst.ID, id);
            values.put(EntitiesTools.ClientConst.FIRST_NAME, editTextFirstName.getText().toString());
            values.put(EntitiesTools.ClientConst.LAST_NAME, editTextLastName.getText().toString());
            values.put(EntitiesTools.ClientConst.TELEPHONE, editTextPhone.getText().toString());
            values.put(EntitiesTools.ClientConst.EMAIL, editTextEmail.getText().toString());
            values.put(EntitiesTools.ClientConst.CREDIT_CARD, editTextCreditCard.getText().toString());

            new AsyncTask<Void, Void, Long>(){
                @Override
                protected void onPostExecute(Long aLong) {
                    Toast.makeText(getBaseContext(), "ID: " + aLong, Toast.LENGTH_LONG).show();
                }

                @Override
                protected Long doInBackground(Void... voids) {
                    return DB_Manager_Factory.getManager().addClient(values);
                }
            }.execute();

            this.finish();
        }
        catch (Exception e){
            Toast.makeText(getBaseContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


}
