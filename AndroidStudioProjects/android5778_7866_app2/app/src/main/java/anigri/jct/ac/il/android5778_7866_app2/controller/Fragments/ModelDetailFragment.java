package anigri.jct.ac.il.android5778_7866_app2.controller.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import anigri.jct.ac.il.android5778_7866_app2.R;
import anigri.jct.ac.il.android5778_7866_app2.adapters.DownLoadImageTask;

/**
 * Created by Bruno on 22/01/2018.
 */


public class ModelDetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.model_detail_layout, container, false);
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
        String companyName = bundle.getString("companyName");
        String modelName = bundle.getString("modelName");
        double tankVolume = bundle.getDouble("tankVolume", 0);
        String gearbox = bundle.getString("gearbox");
        int seats = bundle.getInt("seats", 0);
        String pictureURL = bundle.getString("pictureURL");

        //set elements
        new DownLoadImageTask(modelDetailImageView, id, view.getContext()).execute(pictureURL);
        modelDetailIdTextView.setText(Long.toString(id));
        modelDetailCompanyNameTextView.setText(companyName);
        modelDetailModelNameTextView.setText(modelName);
        modelDetailTankVolumeTextView.setText(Double.toString(tankVolume));
        modelDetailGearboxTextView.setText(gearbox);
        modelDetailSeatsTextView.setText(Integer.toString(seats));

        getActivity().setTitle("Model #" + id);
    }

    private ImageView modelDetailImageView;
    private TextView modelDetailIdTextView;
    private TextView modelDetailCompanyNameTextView;
    private TextView modelDetailModelNameTextView;
    private TextView modelDetailTankVolumeTextView;
    private TextView modelDetailGearboxTextView;
    private TextView modelDetailSeatsTextView;


    private void findViews(View view) {
        modelDetailImageView = (ImageView)view.findViewById( R.id.modelDetailImageView );
        modelDetailIdTextView = (TextView)view.findViewById( R.id.modelDetailIdTextView );
        modelDetailCompanyNameTextView = (TextView)view.findViewById( R.id.modelDetailCompanyNameTextView );
        modelDetailModelNameTextView = (TextView)view.findViewById( R.id.modelDetailModelNameTextView );
        modelDetailTankVolumeTextView = (TextView)view.findViewById( R.id.modelDetailTankVolumeTextView );
        modelDetailGearboxTextView = (TextView)view.findViewById( R.id.modelDetailGearboxTextView );
        modelDetailSeatsTextView = (TextView)view.findViewById( R.id.modelDetailSeatsTextView );
    }

}