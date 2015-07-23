package com.sagar.materialdesigndemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{

    private final static String COLLAPSING_TOOLBAR_FRAGMENT_TAG = "collapsing_toolbar";
    private final static String FAB_FRAGMENT_TAG = "fab";
    private final static String TAB_FRAGMENT_TAG = "tab";
    private final static String ABOUT_FRAGMENT_TAG = "about";
    private final static String SELECTED_TAG = "selected_index";
    private final static int COLLAPSING_TOOLBAR = 0;
    private final static int FAB = 1;
    private final static int TAB = 2;
    private final static int ABOUT = 3;

    private static int selectedIndex;

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private View dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        if(savedInstanceState!=null){
            navigationView.getMenu().getItem(savedInstanceState.getInt(SELECTED_TAG)).setChecked(true);
            return;
        }

        selectedIndex = COLLAPSING_TOOLBAR;

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                new CollapsingToolbarFragment(),COLLAPSING_TOOLBAR_FRAGMENT_TAG).commit();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_TAG, selectedIndex);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        switch(menuItem.getItemId()){
            case R.id.item_collapsing_toolbar:
                if(!menuItem.isChecked()){
                    selectedIndex = COLLAPSING_TOOLBAR;
                    menuItem.setChecked(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new CollapsingToolbarFragment(), COLLAPSING_TOOLBAR_FRAGMENT_TAG).commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.item_fab:
                if(!menuItem.isChecked()){
                    selectedIndex = FAB;
                    menuItem.setChecked(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new FabFragment(),FAB_FRAGMENT_TAG).commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.item_tab:
                if(!menuItem.isChecked()){
                    selectedIndex = TAB;
                    menuItem.setChecked(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new TabFragment(),TAB_FRAGMENT_TAG).commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.item_about:
                if(!menuItem.isChecked()){
                    selectedIndex = ABOUT;
                    menuItem.setChecked(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new AboutFragment(),ABOUT_FRAGMENT_TAG).commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
        }
        return false;
    }


    //Click listener for Snackbar UNDO action
    @Override
    public void onClick(View view) {
        ((FabFragment)getSupportFragmentManager().findFragmentByTag(FAB_FRAGMENT_TAG)).removeItem();
    }

    public void setupNavigationDrawer(Toolbar toolbar){
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.open_drawer,R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    public void fabClick(View view){

        setupDialog();

        AlertDialog.Builder builder =
                new AlertDialog.Builder(MainActivity.this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle(getString(R.string.dialog_title));
        builder.setView(dialogView);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!isEmpty()) {

                    String titleText = ((EditText) dialogView.findViewById(R.id.title_text_input))
                            .getText().toString().trim();
                    String contentText = ((EditText) dialogView.findViewById(R.id.content_text_input))
                            .getText().toString().trim();
                    ((FabFragment) getSupportFragmentManager().findFragmentByTag(FAB_FRAGMENT_TAG))
                            .addItem(titleText, contentText);
                    Snackbar.make(findViewById(R.id.fab_coordinator_layout),
                            getString(R.string.text_success), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.text_undo), MainActivity.this)
                            .show();
                }
            }
        });//second parameter used for onclicklistener
        builder.setNegativeButton("Cancel", null);
        //Show dialog and launch keyboard
        builder.show().getWindow().setSoftInputMode(WindowManager.LayoutParams
                .SOFT_INPUT_STATE_ALWAYS_VISIBLE);

    }

    private void setupDialog(){
        dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_layout,null,false);

        final TextInputLayout titleInputLayout = (TextInputLayout)dialogView.findViewById(R.id.text_input_title);
        final TextInputLayout contentInputLayout = (TextInputLayout)dialogView.findViewById(R.id.text_input_content);

        titleInputLayout.setErrorEnabled(true);
        contentInputLayout.setErrorEnabled(true);

        EditText titleInput = (EditText)dialogView.findViewById(R.id.title_text_input);
        EditText contentInput = (EditText)dialogView.findViewById(R.id.content_text_input);

        titleInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence==null||charSequence.toString().equals("")){
                    titleInputLayout.setError(getString(R.string.edittext_error));
                }else{
                    titleInputLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        contentInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence==null||charSequence.toString().equals("")){
                    contentInputLayout.setError(getString(R.string.edittext_error));
                }else{
                    contentInputLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private boolean isEmpty(){
        String titleText = ((EditText)dialogView.findViewById(R.id.title_text_input))
                .getText().toString().trim();
        String contentText = ((EditText)dialogView.findViewById(R.id.content_text_input))
                .getText().toString().trim();

        if((titleText==null||titleText.equals(""))&&(contentText==null||contentText.equals(""))){
            Snackbar.make(findViewById(R.id.fab_coordinator_layout),
                    getString(R.string.title_content_error),Snackbar.LENGTH_LONG).show();
            return true;
        }else if(titleText==null||titleText.equals("")){
            Snackbar.make(findViewById(R.id.fab_coordinator_layout),
                    getString(R.string.titletext_error),Snackbar.LENGTH_LONG).show();
            return true;
        }else if(contentText==null||contentText.equals("")){
            Snackbar.make(findViewById(R.id.fab_coordinator_layout),
                    getString(R.string.contenttext_error),Snackbar.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}
