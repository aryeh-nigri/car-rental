package anigri.jct.ac.il.android5778_7866_app2.controller.Fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import anigri.jct.ac.il.android5778_7866_app2.R;
import anigri.jct.ac.il.android5778_7866_app2.adapters.DownLoadImageTask;
import anigri.jct.ac.il.android5778_7866_app2.model.backend.DB_Manager_Factory;
import anigri.jct.ac.il.android5778_7866_app2.model.entities.Branch;
import anigri.jct.ac.il.android5778_7866_app2.model.entities.Model;

/**
 * Created by Bruno on 22/01/2018.
 */

public class CarDetailFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.car_detail_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fillLayout(view);
    }

    private void fillLayout(View view) {

        //find all our view components
        findViews(view);

        //collect our bundle and populate our layout
        Bundle bundle = getArguments();

        Long id = bundle.getLong("id", 0);
        modelId = bundle.getLong("modelId", 0);
        branchId = bundle.getLong("branchId", 0);
        double kilometers = bundle.getDouble("kilometers", 0);
        String modelPicture = bundle.getString("modelPicture");

        //set elements
        new DownLoadImageTask(carDetailImageView, modelId, view.getContext()).execute(modelPicture);
        carDetailIdTextView.setText(Long.toString(id));
        carDetailModelIdButton.setText(Long.toString(modelId));
        carDetailBranchIdButton.setText(Long.toString(branchId));
        carDetailKilometersTextView.setText(Double.toString(kilometers));

        getActivity().setTitle("Car #" + id);
    }

    private ImageView carDetailImageView;
    private TextView carDetailIdTextView;
    private Button carDetailModelIdButton;
    private Button carDetailBranchIdButton;
    private TextView carDetailKilometersTextView;
    private long modelId;
    private long branchId;


    private void findViews(View view) {
        carDetailImageView = (ImageView)view.findViewById( R.id.carDetailImageView );
        carDetailIdTextView = (TextView)view.findViewById( R.id.carDetailIdTextView );
        carDetailModelIdButton = (Button)view.findViewById( R.id.carDetailModelIdButton );
        carDetailBranchIdButton = (Button)view.findViewById( R.id.carDetailBranchIdButton );
        carDetailKilometersTextView = (TextView)view.findViewById( R.id.carDetailKilometersTextView );

        carDetailModelIdButton.setOnClickListener( this );
        carDetailBranchIdButton.setOnClickListener( this );
    }


    @Override
    public void onClick(View v) {
        if ( v == carDetailModelIdButton ) {
            openModelDetail(modelId);
        } else if ( v == carDetailBranchIdButton ) {
            openBranchDetail(branchId);
        }
    }

    private void openModelDetail(final long modelId) {
        new AsyncTask<Void, Void, Model>(){
            @Override
            protected Model doInBackground(Void... voids) {
                return DB_Manager_Factory.getManager().findModelById(modelId);
            }
            @Override
            protected void onPostExecute(Model model) {
                if(model != null){
                    setModelDetail(model);
                }
            }
        }.execute();
    }

    private void setModelDetail(Model model){
        Bundle bundle = new Bundle();

        bundle.putLong("id", model.getId());
        bundle.putString("companyName", model.getCompanyName());
        bundle.putString("modelName", model.getModelName());
        bundle.putDouble("tankVolume", model.getTankVolume());
        bundle.putString("gearbox", model.getGearbox().toString());
        bundle.putInt("seats", model.getSeats());
        bundle.putString("pictureURL", model.getPictureURL());

        Fragment fragment = new ModelDetailFragment();
        fragment.setArguments(bundle);


        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.detailFrameContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void openBranchDetail(final long branchId){
        new AsyncTask<Void, Void, Branch>(){
            @Override
            protected Branch doInBackground(Void... voids) {
                return DB_Manager_Factory.getManager().findBranchById(branchId);
            }
            @Override
            protected void onPostExecute(Branch branch) {
                if(branch != null){
                    setBranchDetail(branch);
                }
            }
        }.execute();
    }

    private void setBranchDetail(Branch branch){
        Bundle bundle = new Bundle();

        bundle.putLong("id", branch.getId());
        bundle.putString("city", branch.getCity());
        bundle.putString("street", branch.getStreet());
        bundle.putInt("number", branch.getNumber());
        bundle.putInt("parkingSpace", branch.getParkingSpace());

        Fragment fragment = new BranchDetailFragment();
        fragment.setArguments(bundle);


        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.detailFrameContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
