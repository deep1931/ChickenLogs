package cqu.edu.au.chickenlogs.layout.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import cqu.edu.au.chickenlogs.R;
import cqu.edu.au.chickenlogs.layout.fragment.HomeFragment;
import cqu.edu.au.chickenlogs.layout.fragment.LinearLayoutFragment;
import cqu.edu.au.chickenlogs.layout.fragment.NavigationFragment;
import cqu.edu.au.chickenlogs.layout.fragment.TableLayoutFragment;

public class MainActivity extends AppCompatActivity implements
        NavigationFragment.OnNavigationCommandListener,
        LinearLayoutFragment.OnFragmentInteractionListener,
        TableLayoutFragment.OnFragmentInteractionListener {
    //
    private enum NAV_COMMAND {
        NAV_PREV, NAV_HOME, NAV_NEXT;
    }
    //
    private static final String CMD_PREV = "NAV_PREV";
    private static final String CMD_HOME = "NAV_HOME";
    private static final String CMD_NEXT = "NAV_NEXT";
    //
    private static final int MAX_INDEX = 2; //maximum number of fragments
    private int fragmentIndex;

    private static LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout=(LinearLayout)findViewById(R.id.bottom_layout);

        if (savedInstanceState != null) {
            // if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            return;
        }
        //initialize our home fragments
        if (findViewById(R.id.main_fragment_container) != null) {

            // Create a new NavigationFragment to be placed in the activity layout
            HomeFragment homeFragment = HomeFragment.newInstance();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            homeFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_fragment_container, homeFragment).commit();
        }

        //initialize our navigation fragments
        if (findViewById(R.id.bottom_layout) != null) {

            // Create a new NavigationFragment to be placed in the activity layout
            NavigationFragment navFragment = NavigationFragment.newInstance(CMD_PREV, CMD_HOME, CMD_NEXT);

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            // navFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.bottom_layout, navFragment).commit();
        }
        fragmentIndex = 0;

    }

    /**
     * This is the implementation of
     * NavigationFragment.OnNavigationCommandListener interface
     * Commands should be defined in advanced
     */
    public void NavigationCommandSelected (String command) {
        NAV_COMMAND nav_command = NAV_COMMAND.valueOf(command);
        switch (nav_command) {
            case NAV_HOME:
                navigateHome();
                break;
            case NAV_NEXT:
                navigateNext();
                break;
            case NAV_PREV:
                navigatePrev();
                break;
        }
    }

    /*Navigate home and bring up the homefragment*/
    private void navigateHome() {
        if (fragmentIndex > 0) {
            fragmentIndex = 0;
            loadFragment(fragmentIndex);
        }
    }

    /*Navigate to the previous fragment*/
    private void navigatePrev() {
        fragmentIndex--;
        if (fragmentIndex <= 0)
            fragmentIndex = MAX_INDEX;
        loadFragment(fragmentIndex);

    }

    private void navigateNext() {
        fragmentIndex++;
        if (fragmentIndex > MAX_INDEX)
            fragmentIndex = 1;
        loadFragment(fragmentIndex);
    }

/*
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .show(somefrag)
                .commit();
*/
    private void loadFragment(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (index) {
            case 0:
                HomeFragment homeFragment = HomeFragment.newInstance();
                transaction.replace(R.id.main_fragment_container, homeFragment);
                break;
            case 1:
                LinearLayoutFragment linearLayoutFragment = LinearLayoutFragment.newInstance("","");
                transaction.replace(R.id.main_fragment_container, linearLayoutFragment);
                break;
            case 2:
                TableLayoutFragment tableLayoutFragment = TableLayoutFragment.newInstance("","");
                transaction.replace(R.id.main_fragment_container, tableLayoutFragment);

                break;
            case 3:

                break;
            default:
                break;
        }
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }

    public void onFragmentInteraction(Uri uri) {

    }


    public static void hideBottonControls(boolean visible) {
        if (visible)
            layout.setVisibility(View.VISIBLE);
        else
            layout.setVisibility(View.GONE);

    }
}

