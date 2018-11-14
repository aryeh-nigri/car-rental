package anigri.jct.ac.il.android5778_7866_app2.controller.Fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import anigri.jct.ac.il.android5778_7866_app2.R;
import anigri.jct.ac.il.android5778_7866_app2.adapters.BranchArrayAdapter;
import anigri.jct.ac.il.android5778_7866_app2.model.backend.DB_Manager_Factory;
import anigri.jct.ac.il.android5778_7866_app2.model.entities.Branch;

/**
 * Created by Bruno on 18/01/2018.
 */

public class BranchesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_branches, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getListItems(view);
        getActivity().setTitle("Branches");
    }


    private void getListItems(final View view){

        new AsyncTask<Void, Void, List<Branch>>(){
            @Override
            protected List<Branch> doInBackground(Void... voids) {
                return DB_Manager_Factory.getManager().getBranches();
            }
            @Override
            protected void onPostExecute(List<Branch> branches) {
                if(branches == null){
                    //show something else instead
                } else{
                    fillListView(branches, view);
                }
            }
        }.execute();
    }

    private void fillListView(final List<Branch> branches, View view){

        //create our new array adapter
        ArrayAdapter<Branch> adapter = new BranchArrayAdapter(view.getContext(), 0, branches);

        //add event listener so we can handle clicks
        AdapterView.OnItemClickListener adapterViewListener = new AdapterView.OnItemClickListener()
        {
            //on click
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //show branch detail and its cars
                Branch branch = branches.get(position);

                Bundle bundle = new Bundle();
                bundle.putLong("id", branch.getId());
                bundle.putString("city", branch.getCity());
                bundle.putString("street", branch.getStreet());
                bundle.putInt("number", branch.getNumber());
                bundle.putInt("parkingSpace", branch.getParkingSpace());

                BranchAndCarsFragment branchAndCarsFragment = new BranchAndCarsFragment();
                branchAndCarsFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentMainContainer, branchAndCarsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        };

        ListView listView = (ListView) view.findViewById(R.id.branchesListView);
        listView.setAdapter(adapter);

        //set the listener to the list view
        listView.setOnItemClickListener(adapterViewListener);
    }


}
