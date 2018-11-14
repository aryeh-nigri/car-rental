package anigri.jct.ac.il.android5778_7866_app2.controller.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import anigri.jct.ac.il.android5778_7866_app2.R;
import anigri.jct.ac.il.android5778_7866_app2.controller.MainActivity;
import anigri.jct.ac.il.android5778_7866_app2.model.backend.DB_Manager_Factory;
import anigri.jct.ac.il.android5778_7866_app2.model.entities.Client;

/**
 * Created by Bruno on 24/01/2018.
 */

public class RegisterFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Register");
        findViews(view);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().setTitle("Login");
    }

    private TextInputLayout usernameWrapper;
    private EditText usernameEditText;
    private TextInputLayout passwordWrapper;
    private EditText passwordEditText;
    private Button registerButton;

    private void findViews(View view) {
        usernameWrapper = (TextInputLayout)view.findViewById( R.id.usernameWrapper );
        usernameEditText = (EditText)view.findViewById( R.id.usernameEditText );
        passwordWrapper = (TextInputLayout)view.findViewById( R.id.passwordWrapper );
        passwordEditText = (EditText)view.findViewById( R.id.passwordEditText );
        registerButton = (Button)view.findViewById( R.id.registerButton );

        registerButton.setOnClickListener( this );
    }

    
    @Override
    public void onClick(View v) {
        if ( v == registerButton ) {
            register(v);
        }
    }

    private void register(final View v) {

        try{
            final long id = Long.parseLong(usernameEditText.getText().toString());

            new AsyncTask<Void, Void, Client>() {
                @Override
                protected Client doInBackground(Void... voids) {
                    return DB_Manager_Factory.getManager().findClientById(id);
                }

                @Override
                protected void onPostExecute(Client client) {
                    if (client == null) {
                        usernameWrapper.setError("User does not exist");
                    } else {
                        validateRegister(client, v);
                    }
                }
            }.execute();
        } catch (Exception e){
            Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT);
        }
    }

    private void validateRegister(Client client, View v) {

        String passwordEntered = passwordEditText.getText().toString();

        if (passwordEntered.length() > 5) {// ok
            updateClient(client.getId(), passwordEntered, v.getContext());
            Snackbar.make(v, "Signed Up", Snackbar.LENGTH_SHORT).show();
            getFragmentManager().popBackStack();
            //getFragmentManager().beginTransaction().remove(this).commit();//close itself
        } else {
            passwordWrapper.setError("At least 6 characters long!");
        }

    }

    private void updateClient(final long id, final String passwordEntered, final Context context) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                return DB_Manager_Factory.getManager().updateClient(id, passwordEntered);
            }

            @Override
            protected void onPostExecute(Long id) {
                if (id > 0) {
                    Toast.makeText(context, "ID: " + id, Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }


}
