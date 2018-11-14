package anigri.jct.ac.il.android5778_7866_app2.controller.Fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import anigri.jct.ac.il.android5778_7866_app2.R;
import anigri.jct.ac.il.android5778_7866_app2.controller.LoginActivity;
import anigri.jct.ac.il.android5778_7866_app2.controller.MainActivity;
import anigri.jct.ac.il.android5778_7866_app2.model.backend.DB_Manager_Factory;
import anigri.jct.ac.il.android5778_7866_app2.model.entities.Client;

/**
 * Created by Bruno on 22/01/2018.
 */


public class LoginFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
    }

    private SharedPreferences sharedPreferences;
    private CheckBox checkBoxRememberMe;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;
    private TextInputLayout usernameWrapper;
    private TextInputLayout passwordWrapper;

    private void findViews(View view) {
        usernameEditText = (EditText) view.findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) view.findViewById(R.id.passwordEditText);
        loginButton = (Button) view.findViewById(R.id.loginButton);
        registerButton = (Button) view.findViewById(R.id.registerButton);
        usernameWrapper = (TextInputLayout) view.findViewById(R.id.usernameWrapper);
        passwordWrapper = (TextInputLayout) view.findViewById(R.id.passwordWrapper);
        checkBoxRememberMe = (CheckBox)view.findViewById( R.id.checkBoxRememberMe );

        usernameWrapper.setHint("User ID");
        passwordWrapper.setHint("Password");

        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());

        loadSharedPreferences(getActivity().getBaseContext());
    }

    @Override
    public void onClick(View v) {
        if (v == loginButton) {
            login(v);
        } else if (v == registerButton) {
            register();
        }
    }

    private void login(final View v) {
        //user is id and password is user chosen
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
                        validateLogin(client, v);
                    }
                }
            }.execute();
        } catch (Exception e){
            Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT);
        }
    }

    private void validateLogin(Client client, View v) {

        String password = client.getPassword();
        String passwordEntered = passwordEditText.getText().toString();

        if(password != null){
            if (password.compareTo(passwordEntered) == 0) {// ok

                //((MainActivity) getActivity()).setClient(client);// save client
                Snackbar.make(v, "Logged In", Snackbar.LENGTH_SHORT).show();
                saveSharedPreferencesAsync(getActivity().getBaseContext());//save preferences
                getFragmentManager().beginTransaction().remove(this).commit();//close itself

                ((LoginActivity) getActivity()).loginDone(client.getId());
                Log.i("LOGIN-DONE", "Should be on main activity now");

            } else {
                passwordWrapper.setError("Wrong password");
            }
        } else {
            passwordWrapper.setError("Wrong password");
        }

    }

    private void register() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.loginContainer, new RegisterFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void saveSharedPreferencesAsync(Context context) {
        if(checkBoxRememberMe.isChecked()){
            try {
                SharedPreferences.Editor editor = sharedPreferences.edit();

                String userId = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                editor.putString("userId", userId);
                editor.putString("password", password);
                editor.commit();

                //Toast.makeText(context, "User preferences saved", Toast.LENGTH_SHORT).show();

            } catch (Exception ex) {
                Toast.makeText(context, "Failed to save preferences", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loadSharedPreferences(Context context) {

        if (sharedPreferences.contains("userId") && sharedPreferences.contains("password")) {
            usernameEditText.setText(sharedPreferences.getString("userId", null));
            //Toast.makeText(context, "Load user ID", Toast.LENGTH_SHORT).show();

            passwordEditText.setText(sharedPreferences.getString("password", null));
            //Toast.makeText(context, "Load password", Toast.LENGTH_SHORT).show();

            checkBoxRememberMe.setChecked(true);
        }

    }

}

