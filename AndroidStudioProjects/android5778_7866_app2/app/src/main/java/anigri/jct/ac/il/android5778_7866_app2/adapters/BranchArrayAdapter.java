package anigri.jct.ac.il.android5778_7866_app2.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import anigri.jct.ac.il.android5778_7866_app2.R;
import anigri.jct.ac.il.android5778_7866_app2.model.entities.Branch;

/**
 * Created by Bruno on 18/01/2018.
 */

public class BranchArrayAdapter extends ArrayAdapter<Branch> {

    private Context context;
    private List<Branch> branches;

    public BranchArrayAdapter(@NonNull Context context, int resource, @NonNull List<Branch> objects) {
        super(context, resource, objects);

        this.context = context;
        this.branches = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //get the client we are displaying
        Branch branch = branches.get(position);
        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.branch_list_layout, null);

        TextView branchListStreetTextView = (TextView)view.findViewById( R.id.branchListStreetTextView );
        TextView branchListNumberTextView = (TextView)view.findViewById( R.id.branchListNumberTextView );
        TextView branchListCityTextView = (TextView)view.findViewById( R.id.branchListCityTextView );
        TextView branchListParkingSpaceTextView = (TextView)view.findViewById( R.id.branchListParkingSpaceTextView );

        //set street and number
        String street = branch.getStreet();
        branchListStreetTextView.setText(street);
        String number = String.valueOf(branch.getNumber());
        branchListNumberTextView.setText(number);
        //set city
        String city = branch.getCity();
        branchListCityTextView.setText(city);
        //set parking space
        String parkingSpace = String.valueOf(branch.getParkingSpace());
        branchListParkingSpaceTextView.setText(parkingSpace);

        return view;
    }

}
