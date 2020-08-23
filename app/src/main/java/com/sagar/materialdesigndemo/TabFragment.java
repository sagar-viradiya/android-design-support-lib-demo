package com.sagar.materialdesigndemo;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.sagar.adapters.ViewPagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment extends Fragment {

    private MainActivity mainActivity;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public TabFragment() {
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

        View view = inflater.inflate(R.layout.fragment_tab, container, false);

        toolbar = (Toolbar)view.findViewById(R.id.tab_toolbar);

        setupToolbar();

        viewPager = (ViewPager)view.findViewById(R.id.tab_view_pager);

        setupViewPager();

        tabLayout = (TabLayout)view.findViewById(R.id.tab_layout);

        setupTab();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void setupToolbar(){
        toolbar.setTitle(getString(R.string.tab_fragment_title));
        mainActivity.setSupportActionBar(toolbar);
    }

    private void setupTab(){
        tabLayout.setupWithViewPager(viewPager);
    }


    private void setupViewPager(){
        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));
    }
}
