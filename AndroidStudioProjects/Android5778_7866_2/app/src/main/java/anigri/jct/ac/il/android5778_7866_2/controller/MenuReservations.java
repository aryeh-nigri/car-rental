package anigri.jct.ac.il.android5778_7866_2.controller;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import anigri.jct.ac.il.android5778_7866_2.R;

/**
 * Created by Bruno on 31/12/2017.
 */

public class MenuReservations extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.menu_clients, container, false);
        return inflater.inflate(R.layout.generic_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Reservations");
        findViews();
    }

    private LinearLayout addButton;
    private LinearLayout removeButton;
    private LinearLayout updateButton;
    private LinearLayout showAllButton;


    private void findViews() {
        addButton = (LinearLayout) getActivity().findViewById( R.id.addButton );
        removeButton = (LinearLayout) getActivity().findViewById( R.id.removeButton );
        updateButton = (LinearLayout) getActivity().findViewById( R.id.updateButton );
        showAllButton = (LinearLayout) getActivity().findViewById( R.id.showAllButton );

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        showAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}
