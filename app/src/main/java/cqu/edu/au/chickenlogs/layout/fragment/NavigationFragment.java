package cqu.edu.au.chickenlogs.layout.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cqu.edu.au.chickenlogs.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnNavigationCommandListener} interface
 * to handle interaction events.
 * Use the {@link NavigationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavigationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CMD_NAV_PREV = "CMD_NAV_PREV";
    private static final String CMD_NAV_HOME = "CMD_NAV_HOME";
    private static final String CMD_NAV_NEXT = "CMD_NAV_NEXT";

    // TODO: Rename and change types of parameters
    private String cmdNavPrev;
    private String cmdNavHome;
    private String cmdNavNext;

    private OnNavigationCommandListener mListener;

    public NavigationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 command for button1.
     * @param param2 command for button2.
     * @param param3 command for button3.
     * @return A new instance of fragment NavigationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NavigationFragment newInstance(String param1, String param2, String param3) {
        NavigationFragment fragment = new NavigationFragment();
        Bundle args = new Bundle();
        args.putString(CMD_NAV_PREV, param1);
        args.putString(CMD_NAV_HOME, param2);
        args.putString(CMD_NAV_NEXT, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cmdNavPrev = getArguments().getString(CMD_NAV_PREV);
            cmdNavHome = getArguments().getString(CMD_NAV_HOME);
            cmdNavNext = getArguments().getString(CMD_NAV_NEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        Button btnNav1 = (Button) view.findViewById(R.id.btn_nav_prev);
        btnNav1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                navigationButtonPressed(v);
            }
        });

        Button btnNav2 = (Button) view.findViewById(R.id.btn_nav_home);
        btnNav2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                navigationButtonPressed(v);
            }
        });

        Button btnNav3 = (Button) view.findViewById(R.id.btn_nav_next);
        btnNav3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                navigationButtonPressed(v);
            }
        });

        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    private void navigationButtonPressed(View v) {
        String cmd;
        if (mListener != null) {
            switch(v.getId())
            {
                case R.id.btn_nav_home:
                    cmd = cmdNavHome;
                    break;
                case R.id.btn_nav_prev:
                    cmd = cmdNavPrev;
                    break;
                case R.id.btn_nav_next:
                    cmd = cmdNavNext;
                    break;
                default:
                    cmd = "CMD_UNKNOWN";
                    break;
            }
            mListener.NavigationCommandSelected(cmd);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNavigationCommandListener) {
            mListener = (OnNavigationCommandListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnNavigationCommandListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnNavigationCommandListener {
        // TODO: Update argument type and name
        public void NavigationCommandSelected(String command);
    }
}
