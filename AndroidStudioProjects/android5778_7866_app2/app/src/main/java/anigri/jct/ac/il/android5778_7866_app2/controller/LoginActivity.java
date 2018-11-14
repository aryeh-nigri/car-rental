package anigri.jct.ac.il.android5778_7866_app2.controller;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import anigri.jct.ac.il.android5778_7866_app2.R;
import anigri.jct.ac.il.android5778_7866_app2.controller.Fragments.CarsFragment;
import anigri.jct.ac.il.android5778_7866_app2.controller.Fragments.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    //private long clientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        startCarsAsync();

        login();
    }

    private void startCarsAsync() {
        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                new CarsFragment();//start the fragment to download the images first, in the background
                long nothing = 0;
                return nothing;
            }
        }.execute();
    }

    private void login() {//TODO ver como nao aparecer o menu atras

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        //ft.add(R.id.loginContainer, new LoginFragment());
        ft.add(R.id.loginContainer, new LoginFragment());
        //ft.addToBackStack(null);
        ft.commit();
    }

    public void loginDone(long clientId){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("clientId", clientId);

        startActivity(intent);
        this.finish();
    }

}
