package anigri.jct.ac.il.android5778_7866_app2.controller.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import anigri.jct.ac.il.android5778_7866_app2.R;

/**
 * Created by Bruno on 18/01/2018.
 */

public class AboutUsFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("About Us");
        findViews(view);
    }

    private TextView telephoneTextView;
    private TextView emailTextView;
    private TextView siteTextView;


    private void findViews(View view) {
        telephoneTextView = (TextView)view.findViewById( R.id.telephoneTextView );
        emailTextView = (TextView)view.findViewById( R.id.emailTextView );
        siteTextView = (TextView)view.findViewById( R.id.siteTextView );

        telephoneTextView.setOnClickListener(this);
        emailTextView.setOnClickListener(this);
        siteTextView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == telephoneTextView) {
            makeCall();
        } else if (v == emailTextView) {
            sendEmail();
        } else if(v == siteTextView){
            openSite();
        }
    }

    private void makeCall() {
        // Handle clicks for call
        String number = telephoneTextView.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }

    private void sendEmail(){
        String mail = emailTextView.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("mailto:" + mail));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Contact Us");
        startActivity(intent);
    }

    private void openSite(){
        String url = siteTextView.getText().toString();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("http://" + url));
        startActivity(intent);
    }

}
