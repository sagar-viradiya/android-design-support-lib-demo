package com.sagar.materialdesigndemo;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {


    private MainActivity mainActivity;
    private Toolbar toolbar;


    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_about, container, false);

        (view.findViewById(R.id.license_card)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myIntent = new Intent(Intent.ACTION_VIEW);
                        myIntent.setData(Uri.parse("http://source.android.com/"));
                        startActivity(myIntent);
                    }
        });

        toolbar = (Toolbar)view.findViewById(R.id.about_toolbar);

        setupToolbar();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity.setupNavigationDrawer(toolbar);
    }

    private void setupToolbar(){
        toolbar.setTitle(getString(R.string.about_fragment_title));
        mainActivity.setSupportActionBar(toolbar);
    }


}
