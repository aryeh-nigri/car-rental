package anigri.jct.ac.il.android5778_7866_2.adapters;

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

import anigri.jct.ac.il.android5778_7866_2.R;
import anigri.jct.ac.il.android5778_7866_2.model.entities.Model;

/**
 * Created by Bruno on 16/01/2018.
 */

public class ModelArrayAdapter extends ArrayAdapter<Model> {

    private Context context;
    private List<Model> models;

    public ModelArrayAdapter(@NonNull Context context, int resource, @NonNull List<Model> objects) {
        super(context, resource, objects);

        this.context = context;
        this.models = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //get the client we are displaying
        Model model = models.get(position);
        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.model_list_layout, null);

        ImageView modelListImageView = (ImageView)view.findViewById( R.id.modelListImageView );
        TextView modelListModelNameTextView = (TextView)view.findViewById( R.id.modelListModelNameTextView );
        TextView modelListGearboxTextView = (TextView)view.findViewById( R.id.modelListGearboxTextView );
        TextView modelListCompanyNameTextView = (TextView)view.findViewById( R.id.modelListCompanyNameTextView );

        //set model name
        String modelName = model.getModelName();
        modelListModelNameTextView.setText(modelName);
        //set gearbox
        String gearbox = model.getGearbox().toString();
        modelListGearboxTextView.setText(gearbox);
        //set company name
        String companyName = model.getCompanyName();
        modelListCompanyNameTextView.setText(companyName);
        //set image
        //for now, this is hardcoded image for everyone
        modelListImageView.setBackgroundResource(R.drawable.ic_directions_car_black_24dp);

        return view;
    }
}
