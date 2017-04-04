package tsekhmeistruk.whatistheweather.ui;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

import tsekhmeistruk.whatistheweather.R;

/**
 * Created by Roman Tsekhmeistruk on 31.03.2017.
 */

public class BaseFragment extends Fragment {

    private Drawer.Result drawer;
    private Toolbar toolbar;

    @Override
    public void onResume() {
        super.onResume();

        initializeToolbar();
        initializeNavigationDrawer();
    }

    private void initializeToolbar() {
        ActionBarActivity activity = (ActionBarActivity) getActivity();
        toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initializeNavigationDrawer() {
        drawer = new Drawer()
                .withActivity(getActivity())
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.header_navigation_drawer)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(getString(R.string.weather_in_my_city))
                                .withIcon(ContextCompat.getDrawable(getActivity().getApplicationContext(),
                                        R.drawable.city_icon))
                                .withIdentifier(1),
                        new PrimaryDrawerItem().withName(getString(R.string.find_city))
                                .withIcon(ContextCompat.getDrawable(getActivity().getApplicationContext(),
                                        R.drawable.search)).withIdentifier(2),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(getString(R.string.rate_application))
                                .withIcon(ContextCompat.getDrawable(getActivity().getApplicationContext(),
                                        R.drawable.google_play)).withIdentifier(3)
                )
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        InputMethodManager inputMethodManager
                                = (InputMethodManager) getActivity().getSystemService(
                                Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager
                                .hideSoftInputFromWindow(getActivity().getCurrentFocus()
                                        .getWindowToken(), 0);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }
                })
                .build();
    }

}
