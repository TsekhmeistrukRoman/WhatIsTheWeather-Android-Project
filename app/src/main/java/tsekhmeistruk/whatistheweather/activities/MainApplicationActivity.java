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

import butterknife.BindView;
import butterknife.ButterKnife;
import tsekhmeistruk.whatistheweather.R;
import tsekhmeistruk.whatistheweather.ui.WeatherPreviewFragment;

public class MainApplicationActivity extends ActionBarActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        ButterKnife.bind(MainApplicationActivity.this);

        initializeToolbar();
        initializeNavigationDrawer();

        startFragment(WeatherPreviewFragment.newInstance());
    }

    public void startFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        fragmentTransaction.commit();
    }

    private void initializeToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initializeNavigationDrawer() {
        new Drawer()
                .withActivity(MainApplicationActivity.this)
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
                                = (InputMethodManager) getSystemService(
                                Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager
                                .hideSoftInputFromWindow(getCurrentFocus()
                                        .getWindowToken(), 0);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }
                })
                .build();
    }

}
