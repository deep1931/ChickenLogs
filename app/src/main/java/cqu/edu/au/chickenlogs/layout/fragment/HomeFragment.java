package cqu.edu.au.chickenlogs.layout.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cqu.edu.au.chickenlogs.R;
import cqu.edu.au.chickenlogs.layout.activity.MainActivity;


/**
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    private Button btnFoghorn;
    private Button btnTweety;
    private Button btnHawk;
    private Button btnLittle;
    private Button btnBerth;
    private View view;

    public static final String CHICKEN_TYPE_FOGHORN = "Foghorn";
    public static final String CHICKEN_TYPE_TWEETY = "Tweety";
    public static final String CHICKEN_TYPE_HAWK = "Hawk";
    public static final String CHICKEN_TYPE_LITTLE = "Little";
    public static final String CHICKEN_TYPE_BERTHA = "Bertha";


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        init();
        return view;
    }

    private void init() {
        btnFoghorn = (Button) view.findViewById(R.id.btnFoghorn);
        btnBerth = (Button) view.findViewById(R.id.btnBertha);
        btnHawk = (Button) view.findViewById(R.id.btnHawk);
        btnLittle = (Button) view.findViewById(R.id.btnLittle);
        btnTweety = (Button) view.findViewById(R.id.btnTweety);


        btnFoghorn.setOnClickListener(this);
        btnBerth.setOnClickListener(this);
        btnHawk.setOnClickListener(this);
        btnLittle.setOnClickListener(this);
        btnTweety.setOnClickListener(this);

        getActivity().setTitle("CHICKENLOGS");


    }


    @Override
    public void onClick(View view) {

        if (btnFoghorn.getId() == view.getId()) {
            FoghornFragment homeFragment = FoghornFragment.newInstance();

            startFragment(homeFragment, null, CHICKEN_TYPE_FOGHORN);
        }

        if (btnTweety.getId() == view.getId()) {
            TweetyFragment fragment = TweetyFragment.newInstance();

            startFragment(fragment, null, CHICKEN_TYPE_TWEETY);
        }

        if (btnLittle.getId() == view.getId()) {
            LittleFragment fragment = LittleFragment.newInstance();

            startFragment(fragment, null, CHICKEN_TYPE_LITTLE);
        }

        if (btnBerth.getId() == view.getId()) {
            BerthFragment fragment = BerthFragment.newInstance();

            startFragment(fragment, null, CHICKEN_TYPE_BERTHA);
        }

        if (btnHawk.getId() == view.getId()) {
            HawkFragment fragment = HawkFragment.newInstance();

            startFragment(fragment, null, CHICKEN_TYPE_HAWK);
        }

    }

    private void startFragment(Fragment fragment, Bundle bundle, String tag) {

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        fragment.setArguments(bundle);

        final FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment_container, fragment, tag);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.hideBottonControls(true);


    }

    @Override
    public void onPause() {
        super.onPause();

        MainActivity.hideBottonControls(false);

    }


}
