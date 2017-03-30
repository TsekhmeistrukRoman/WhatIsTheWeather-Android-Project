package tsekhmeistruk.whatistheweather.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

import tsekhmeistruk.whatistheweather.R;
import tsekhmeistruk.whatistheweather.ui.WeatherPreviewFragment;

public class MainActivity extends ActionBarActivity {

    private Drawer.Result drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        initializeToolbar();
        initializeNavigationDrawer();

        startFragment(WeatherPreviewFragment.newInstance());
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    public void startFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        fragmentTransaction.commit();
    }

    private void initializeToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initializeNavigationDrawer() {
        drawer = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.header_navigation_drawer)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(getString(R.string.weather_in_my_city))
                                .withIcon(ContextCompat.getDrawable(getApplicationContext(),
                                        R.drawable.city_icon))
                                .withIdentifier(1),
                        new PrimaryDrawerItem().withName(getString(R.string.find_city))
                                .withIcon(ContextCompat.getDrawable(getApplicationContext(),
                                        R.drawable.search)).withIdentifier(2),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(getString(R.string.rate_application))
                                .withIcon(ContextCompat.getDrawable(getApplicationContext(),
                                        R.drawable.google_play)).withIdentifier(3)
                )
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        InputMethodManager inputMethodManager
                                = (InputMethodManager) MainActivity.this.getSystemService(
                                Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager
                                .hideSoftInputFromWindow(MainActivity.this.getCurrentFocus()
                                        .getWindowToken(), 0);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }
                })
                .build();
    }

}
