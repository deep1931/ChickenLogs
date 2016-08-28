package cqu.edu.au.chickenlogs.layout.fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import cqu.edu.au.chickenlogs.R;
import cqu.edu.au.chickenlogs.adapter.ItemListAdapter;
import cqu.edu.au.chickenlogs.adapter.ObjectItem;
import cqu.edu.au.chickenlogs.database.DBAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowLogFragment extends Fragment {


    private View view;
    private ListView listView;

    private DBAdapter db;
    private String type;

    private Button btnBack;

    public static ShowLogFragment newInstance() {
        ShowLogFragment fragment = new ShowLogFragment();
        return fragment;
    }

    public ShowLogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_show_log, container, false);

        init();
        return view;
    }

    private void init() {

        db = new DBAdapter(getActivity());
        db.open();


        listView = (ListView) view.findViewById(R.id.list);

        if (getArguments() != null)
            type = getArguments().getString("type");

        setList();

        btnBack=(Button)view.findViewById(R.id.btnBack);
        btnBack.setText("BACK TO "+type.toUpperCase());

        getActivity().setTitle(type.toUpperCase());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
    }

    private void setList() {
        Cursor cursor = db.getAlllogs(type);

        int count = cursor.getCount();
        cursor.moveToFirst();

        if (count > 0) {
            ArrayList<ObjectItem> objectItem = new ArrayList<>();

            for (int i = 0; i < count; i++) {

                String data = cursor.getString(cursor.getColumnIndex("_id"))
                        + "  " + cursor.getString(cursor.getColumnIndex("weight"))
                        + "  " + cursor.getString(cursor.getColumnIndex("eggs"))
                        + "  " + cursor.getString(cursor.getColumnIndex("grain"))
                        + "  " + cursor.getString(cursor.getColumnIndex("water"));


                objectItem.add(new ObjectItem(cursor.getString(cursor.getColumnIndex("timestamp"))));
                objectItem.add(new ObjectItem(data));

                cursor.moveToNext();
            }

            ItemListAdapter adapter = new ItemListAdapter(getActivity(), R.layout.list_item, objectItem);
            // create a new ListView, set the adapter and item click listener
            listView.setAdapter(adapter);
        }
    }

}
