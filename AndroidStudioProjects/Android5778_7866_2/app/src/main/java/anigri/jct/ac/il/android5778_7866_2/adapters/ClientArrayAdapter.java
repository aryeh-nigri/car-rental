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
import anigri.jct.ac.il.android5778_7866_2.model.entities.Client;

/**
 * Created by Bruno on 09/01/2018.
 */

public class ClientArrayAdapter extends ArrayAdapter<Client> {

    private Context context;
    private List<Client> clients;

    public ClientArrayAdapter(@NonNull Context context, int resource, @NonNull List<Client> objects) {
        super(context, resource, objects);

        this.context = context;
        this.clients = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //get the client we are displaying
        Client client = clients.get(position);
        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.client_list_layout, null);

        ImageView clientListImageView = (ImageView)view.findViewById( R.id.clientListImageView );
        TextView clientListNameTextView = (TextView)view.findViewById( R.id.clientListNameTextView );
        TextView clientListEmailTextView = (TextView)view.findViewById( R.id.clientListEmailTextView );
        TextView clientListIdTextView = (TextView)view.findViewById( R.id.clientListIdTextView );
        TextView clientListTelephoneTextView = (TextView)view.findViewById( R.id.clientListTelephoneTextView );

        //set name and surname
        String fullName = client.getFirstName() + " " + client.getLastName();
        clientListNameTextView.setText(fullName);
        //set email
        String email = client.getEmail();
        clientListEmailTextView.setText(email);
        //set id
        String id = String.valueOf(client.getId());
        clientListIdTextView.setText(id);
        //set telephone
        String telephone = client.getTelephone();
        clientListTelephoneTextView.setText(telephone);
        //set image
        //for now, this is hardcoded image for everyone
        clientListImageView.setBackgroundResource(R.drawable.ic_account_circle_black_24dp);

        return view;

    }
}
