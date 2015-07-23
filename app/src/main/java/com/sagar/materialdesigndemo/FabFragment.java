package com.sagar.materialdesigndemo;


import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sagar.adapters.RecyclerAdapter;
import com.sagar.models.CardItemModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FabFragment extends Fragment {

    private List<CardItemModel> cardItems = new ArrayList<>(30);
    private MainActivity mainActivity;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private RecyclerAdapter recyclerAdapter;

    public FabFragment() {
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
        View view = inflater.inflate(R.layout.fragment_fab, container, false);

        toolbar = (Toolbar)view.findViewById(R.id.fab_toolbar);

        setupToolbar();

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);

        fixFloatingActionButtonMargin();

        recyclerView = (RecyclerView)view.findViewById(R.id.fab_recycler_view);

        setupRecyclerView();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity.setupNavigationDrawer(toolbar);
    }

    private void setupToolbar(){
        toolbar.setTitle(getString(R.string.fab_fragment_title));
        mainActivity.setSupportActionBar(toolbar);
    }

    private void setupRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));
        recyclerView.setHasFixedSize(true);
        initializeCardItemList();
        recyclerAdapter = new RecyclerAdapter(cardItems);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void initializeCardItemList(){
        CardItemModel cardItemModel;
        String[] cardTitles = getResources().getStringArray(R.array.card_titles);
        String[] cardContents = getResources().getStringArray(R.array.card_contents);
        final int length = cardTitles.length;
        for(int i=0;i<length;i++){
            cardItemModel = new CardItemModel(cardTitles[i],cardContents[i]);
            cardItems.add(cardItemModel);
        }
    }

    public void addItem(String title,String content){
        recyclerAdapter.cardItems.add(new CardItemModel(title,content));
        recyclerAdapter.notifyDataSetChanged();
    }

    public void removeItem(){
        recyclerAdapter.cardItems.remove(recyclerAdapter.cardItems.size() - 1);
        recyclerAdapter.notifyDataSetChanged();
    }

    public void fixFloatingActionButtonMargin(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams)
                    floatingActionButton.getLayoutParams();
            // get rid of margins since shadow area is now the margin
            p.setMargins(0, 0, dpToPx(getActivity(), 8), 0);
            floatingActionButton.setLayoutParams(p);
        }
    }

    public static int dpToPx(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((dp * scale) + 0.5f);
    }
}
