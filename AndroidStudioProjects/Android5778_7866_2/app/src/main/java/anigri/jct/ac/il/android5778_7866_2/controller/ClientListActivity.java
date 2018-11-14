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
import anigri.jct.ac.il.android5778_7866_2.adapters.ClientArrayAdapter;
import anigri.jct.ac.il.android5778_7866_2.model.backend.DB_Manager_Factory;
import anigri.jct.ac.il.android5778_7866_2.model.entities.Client;

public class ClientListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        getListItems();
    }

    private void getListItems(){

        new AsyncTask<Void, Void, List<Client>>(){
            @Override
            protected List<Client> doInBackground(Void... voids) {
                return DB_Manager_Factory.getManager().getClients();
            }
            @Override
            protected void onPostExecute(List<Client> clients) {
                fillListView(clients);
            }
        }.execute();
    }

    private void fillListView(final List<Client> clients){

        //create our new array adapter
        ArrayAdapter<Client> adapter = new ClientArrayAdapter(this, 0, clients);

        //add event listener so we can handle clicks
        AdapterView.OnItemClickListener adapterViewListener = new AdapterView.OnItemClickListener()
        {
            //on click
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Client client = clients.get(position);
                Intent intent = new Intent(ClientListActivity.this, ClientDetailActivity.class);

                intent.putExtra("id", client.getId());
                intent.putExtra("firstName", client.getFirstName());
                intent.putExtra("lastName", client.getLastName());
                intent.putExtra("telephone", client.getTelephone());
                intent.putExtra("email", client.getEmail());
                intent.putExtra("creditCard", client.getCreditCard());

                startActivity(intent);
            }
        };

        ListView listView = (ListView) findViewById(R.id.clientsListView);
        listView.setAdapter(adapter);

        //set the listener to the list view
        listView.setOnItemClickListener(adapterViewListener);
    }

}
