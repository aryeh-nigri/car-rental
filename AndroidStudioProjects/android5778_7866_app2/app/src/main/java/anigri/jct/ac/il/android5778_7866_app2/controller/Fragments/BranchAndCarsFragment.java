package anigri.jct.ac.il.android5778_7866_app2.controller.Fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import anigri.jct.ac.il.android5778_7866_app2.R;

/**
 * Created by Bruno on 18/01/2018.
 */

public class BranchAndCarsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_branch_and_cars, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        startFragments();
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().setTitle("Branches");
    }

    private void startFragments(){

        Bundle bundleBranch = getArguments();

        Bundle bundleCars = new Bundle();
        bundleCars.putLong("id", bundleBranch.getLong("id"));

        BranchDetailFragment branchDetailFragment = new BranchDetailFragment();
        branchDetailFragment.setArguments(bundleBranch);

        BranchCarsFragment branchCarsFragment = new BranchCarsFragment();
        branchCarsFragment.setArguments(bundleCars);


        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.branchDetailFragment, branchDetailFragment);
        //fragmentTransaction.addToBackStack(null);
        //fragmentTransaction.commit();

        fragmentTransaction.add(R.id.carsInBranchFragment, branchCarsFragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
