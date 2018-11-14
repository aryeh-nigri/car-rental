package anigri.jct.ac.il.android5778_7866_app2.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import anigri.jct.ac.il.android5778_7866_app2.R;
import anigri.jct.ac.il.android5778_7866_app2.model.entities.Car;

/**
 * Created by Bruno on 18/01/2018.
 */

public class CarArrayAdapter extends ArrayAdapter<Car> {

    private Context context;
    private List<Car> cars;

    public CarArrayAdapter(@NonNull Context context, int resource, @NonNull List<Car> objects) {
        super(context, resource, objects);

        this.context = context;
        this.cars = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //get the client we are displaying
        Car car = cars.get(position);
        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.car_list_layout, null);

        //TODO write the correct Views

        ImageView carListImageView = (ImageView)view.findViewById( R.id.carListImageView );
        TextView carListBranchIdTextView = (TextView)view.findViewById( R.id.carListBranchIdTextView );
        TextView carListModelIdTextView = (TextView)view.findViewById( R.id.carListModelIdTextView );

        //set image
        new DownLoadImageTask(carListImageView, car.getModelId(), context).execute(car.getModelPicture());
        //set branch ID
        String branchId = String.valueOf(car.getBranchId());
        carListBranchIdTextView.setText(branchId);
        //set model ID
        String modelId = String.valueOf(car.getModelId());
        carListModelIdTextView.setText(modelId);

        return view;
    }
}