package com.software.fire.firebaseuidatabasetutorial.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.software.fire.firebaseuidatabasetutorial.R;
import com.software.fire.firebaseuidatabasetutorial.utils.Constants;
import com.software.fire.firebaseuidatabasetutorial.utils.Utils;

public class ListViewFragment extends Fragment {

    private View mRootView;
    private ListView mListView;
    private FirebaseListAdapter<String> mListAdapter;
    private DatabaseReference mListRef;

    public ListViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_list_view, container, false);
        initialiseView();

        FloatingActionButton fab = (FloatingActionButton) mRootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference(Constants.LIST)
                        .push()
                        .setValue("Random Text: " + Utils.getUID());
            }
        });

        return mRootView;
    }

    private void initialiseView() {
        mListRef = FirebaseDatabase.getInstance().getReference(Constants.LIST);
        mListView = (ListView) mRootView.findViewById(R.id.listview);
        setupAdapter();
        mListView.setAdapter(mListAdapter);
    }

    private void setupAdapter() {
        mListAdapter = new FirebaseListAdapter<String>(
                getActivity(),
                String.class,
                R.layout.item_text,
                mListRef
        ) {
            @Override
            protected void populateView(View v, String model, int position) {
                TextView listText = (TextView) v.findViewById(R.id.list_text);
                listText.setText(model);
            }
        };
    }


}
