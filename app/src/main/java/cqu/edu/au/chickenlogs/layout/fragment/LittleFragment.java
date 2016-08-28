package cqu.edu.au.chickenlogs.layout.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cqu.edu.au.chickenlogs.R;
import cqu.edu.au.chickenlogs.database.DBAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class LittleFragment extends Fragment {


    private View view;
    private EditText etId;
    private EditText etWeight;
    private EditText etEggs;
    private EditText etGrain;
    private EditText etWater;

    private Button btnSaveLog;
    private Button btnShowLog;


    private String id;
    private String weight;
    private String eggs;
    private String grain;
    private String water;


    private DBAdapter dbAdapter;


    public static LittleFragment newInstance() {
        return new LittleFragment();
    }

    public LittleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_little, container, false);

        init();
        return view;
    }

    private void init() {

        getActivity().setTitle("LITTLE");


        dbAdapter = new DBAdapter(getActivity());
        dbAdapter.open();

        etId = (EditText) view.findViewById(R.id.etId);
        etEggs = (EditText) view.findViewById(R.id.etEggs);
        etGrain = (EditText) view.findViewById(R.id.etGrain);
        etWater = (EditText) view.findViewById(R.id.etWater);
        etWeight = (EditText) view.findViewById(R.id.etWeight);

        btnSaveLog = (Button) view.findViewById(R.id.btnLog);
        btnShowLog = (Button) view.findViewById(R.id.btnShow);


        btnSaveLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    if (dbAdapter.insertLogs(id, HomeFragment.CHICKEN_TYPE_LITTLE, weight, eggs, grain, water) < 0) {
                        showToast("Record Not Inserted !");

                    } else {
                        showToast("Record Inserted !");
                        clearFields();

                    }
                }
            }
        });

        btnShowLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("type", HomeFragment.CHICKEN_TYPE_LITTLE);
                startFragment(ShowLogFragment.newInstance(), bundle);
            }
        });


    }

    private boolean validate() {
        id = etId.getText().toString();
        eggs = etEggs.getText().toString();
        grain = etGrain.getText().toString();
        water = etWater.getText().toString();
        weight = etWeight.getText().toString();

        if (id.isEmpty()) {
            showToast("Please enter Id");
            etId.requestFocus();
            return false;
        } else if (Integer.parseInt(id) < 1111 || Integer.parseInt(id) > 9999) {
            etId.requestFocus();

            showToast("Id should be 1111 to 9999");
            return false;
        }

        if (weight.isEmpty()) {
            etWeight.requestFocus();

            showToast("Please enter weight");
            return false;
        } else if (Integer.parseInt(weight) < 0 || Integer.parseInt(weight) > 1000) {
            etWeight.requestFocus();

            showToast("Weight should be 0 to 1000");

            return false;

        }

        if (eggs.isEmpty()) {
            etEggs.requestFocus();

            showToast("Please enter eggs laid");
            return false;
        } else if (Integer.parseInt(eggs) < 0 || Integer.parseInt(eggs) > 4) {
            etEggs.requestFocus();

            showToast("Eggs laid should be 0 to 4");

            return false;

        }

        if (grain.isEmpty()) {
            showToast("Please enter grain eaten(g)");
            etGrain.requestFocus();

            return false;
        } else if (Integer.parseInt(grain) < 0 || Integer.parseInt(grain) > 100) {
            showToast("Grain eaten(g) should be 0 to 100");
            etGrain.requestFocus();


            return false;

        }

        if (water.isEmpty()) {
            showToast("Please enter water(ml");
            etWater.requestFocus();

            return false;
        } else if (Integer.parseInt(water) < 0 || Integer.parseInt(water) > 200) {
            showToast("Water(ml) should be 0 to 100");
            etWater.requestFocus();


            return false;

        }


        return true;
    }


    private void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }


    private void clearFields() {
        etId.setText("");
        etEggs.setText("");
        etWeight.setText("");
        etWater.setText("");
        etGrain.setText("");
    }

    private void startFragment(Fragment fragment, Bundle bundle) {

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        fragment.setArguments(bundle);

        final FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
