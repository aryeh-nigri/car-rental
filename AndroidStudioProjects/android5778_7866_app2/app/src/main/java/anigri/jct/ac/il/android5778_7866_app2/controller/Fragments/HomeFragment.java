package anigri.jct.ac.il.android5778_7866_app2.controller.Fragments;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import anigri.jct.ac.il.android5778_7866_app2.R;
import anigri.jct.ac.il.android5778_7866_app2.model.backend.DB_Manager_Factory;
import anigri.jct.ac.il.android5778_7866_app2.model.entities.Client;

/**
 * Created by Bruno on 06/02/2018.
 */

public class HomeFragment  extends Fragment{

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            //return super.onCreateView(inflater, container, savedInstanceState);

            return inflater.inflate(R.layout.fragment_home, container, false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            findViews(view);
        }

        private TextView textViewHomeClientName;


        private void findViews(View view) {
            textViewHomeClientName = (TextView)view.findViewById(R.id.textViewHomeClientName);
            setClientName();
        }

    private void setClientName() {
        final long clientId = getArguments().getLong("clientId", -1);

        new AsyncTask<Void, Void, Client>(){
            @Override
            protected Client doInBackground(Void... voids) {
                return DB_Manager_Factory.getManager().findClientById(clientId);
            }
            @Override
            protected void onPostExecute(Client client) {
                if(client == null){
                    //show something else instead
                } else{
                    setClientDetail(client);
                }
            }
        }.execute();
    }

    private void setClientDetail(Client client) {
        String firstName = client.getFirstName();
        String lastName = client.getLastName();
        String name = firstName + " " + lastName;
        textViewHomeClientName.setText(name);
    }

}
