package anigri.jct.ac.il.android5778_7866_app2.controller.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import anigri.jct.ac.il.android5778_7866_app2.R;
import anigri.jct.ac.il.android5778_7866_app2.model.backend.DB_Manager_Factory;
import anigri.jct.ac.il.android5778_7866_app2.model.entities.Branch;

/**
 * Created by Bruno on 18/01/2018.
 */

public class BranchDetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_branch_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
    }

    private void fillLayout() {

        //collect our bundle and populate our layout
        Bundle bundle = getArguments();

        Long id = bundle.getLong("id", 0);
        String city = bundle.getString("city");
        String street = bundle.getString("street");
        int number = bundle.getInt("number", 0);
        int parkingSpace = bundle.getInt("parkingSpace", 0);

        String address = street + " " + number;

        //set elements
        //image hardcoded for now
        branchDetailImageView.setBackgroundResource(R.drawable.ic_store_black_24dp);
        branchDetailCityTextView.setText(city);
        branchDetailIdTextView.setText(Long.toString(id));
        branchDetailParkingSpaceTextView.setText(Integer.toString(parkingSpace));
        branchDetailStreetAndNumberTextView.setText(address);

        //set the title of this activity
        getActivity().setTitle("Branch #" + id);
    }

    private ImageView branchDetailImageView;
    private TextView branchDetailIdTextView;
    private TextView branchDetailStreetAndNumberTextView;
    private TextView branchDetailCityTextView;
    private TextView branchDetailParkingSpaceTextView;


    private void findViews(View view) {
        branchDetailImageView = (ImageView)view.findViewById( R.id.branchDetailImageView );
        branchDetailIdTextView = (TextView)view.findViewById( R.id.branchDetailIdTextView );
        branchDetailStreetAndNumberTextView = (TextView)view.findViewById( R.id.branchDetailStreetAndNumberTextView );
        branchDetailCityTextView = (TextView)view.findViewById( R.id.branchDetailCityTextView );
        branchDetailParkingSpaceTextView = (TextView)view.findViewById( R.id.branchDetailParkingSpaceTextView );

        fillLayout();
    }

}
