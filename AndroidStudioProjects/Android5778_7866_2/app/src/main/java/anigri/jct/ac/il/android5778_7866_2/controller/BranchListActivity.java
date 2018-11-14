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
import anigri.jct.ac.il.android5778_7866_2.adapters.BranchArrayAdapter;
import anigri.jct.ac.il.android5778_7866_2.model.backend.DB_Manager_Factory;
import anigri.jct.ac.il.android5778_7866_2.model.entities.Branch;

public class BranchListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_list);

        getListItems();
    }


    private void getListItems(){

        new AsyncTask<Void, Void, List<Branch>>(){
            @Override
            protected List<Branch> doInBackground(Void... voids) {
                return DB_Manager_Factory.getManager().getBranches();
            }
            @Override
            protected void onPostExecute(List<Branch> branches) {
                fillListView(branches);
            }
        }.execute();
    }

    private void fillListView(final List<Branch> branches){

        //create our new array adapter
        ArrayAdapter<Branch> adapter = new BranchArrayAdapter(this, 0, branches);

        //add event listener so we can handle clicks
        AdapterView.OnItemClickListener adapterViewListener = new AdapterView.OnItemClickListener()
        {
            //on click
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Branch branch = branches.get(position);
                Intent intent = new Intent(BranchListActivity.this, BranchDetailActivity.class);

                intent.putExtra("id", branch.getId());
                intent.putExtra("city", branch.getCity());
                intent.putExtra("street", branch.getStreet());
                intent.putExtra("number", branch.getNumber());
                intent.putExtra("parkingSpace", branch.getParkingSpace());

                startActivity(intent);
            }
        };

        ListView listView = (ListView) findViewById(R.id.branchesListView);
        listView.setAdapter(adapter);

        //set the listener to the list view
        listView.setOnItemClickListener(adapterViewListener);
    }

}
