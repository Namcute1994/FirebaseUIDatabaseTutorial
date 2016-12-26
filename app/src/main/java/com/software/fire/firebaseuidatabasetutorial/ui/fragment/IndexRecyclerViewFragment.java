package com.software.fire.firebaseuidatabasetutorial.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseIndexRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.software.fire.firebaseuidatabasetutorial.R;
import com.software.fire.firebaseuidatabasetutorial.models.Post;
import com.software.fire.firebaseuidatabasetutorial.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndexRecyclerViewFragment extends Fragment {

    private View mRootView;
    private RecyclerView mIndexedRecyclerview;
    private FirebaseIndexRecyclerAdapter<Post, RecyclerViewFragment.PostViewHolder> mPostIndexedAdapter;
    private DatabaseReference mIndexedPostRef;
    private DatabaseReference mKeyRef;

    private String[] emails = new String[]{"123@gmail,com", "456@gmail,com", "789@gmail,com"};

    public IndexRecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_index_recycler_view, container, false);
        initialiseView();
        return mRootView;
    }

    private void initialiseView() {
        mKeyRef = FirebaseDatabase.getInstance().getReference(Constants.FAVOURITES)
                .child(emails[(int) (Math.random() * emails.length)]);
        mIndexedPostRef = FirebaseDatabase.getInstance().getReference(Constants.POSTS);

        mIndexedRecyclerview = (RecyclerView) mRootView.findViewById(R.id.indexedRecyclerView);
        mIndexedRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        setupAdapter();
        mIndexedRecyclerview.setAdapter(mPostIndexedAdapter);
    }

    private void setupAdapter() {
        mPostIndexedAdapter = new FirebaseIndexRecyclerAdapter<Post, RecyclerViewFragment.PostViewHolder>(
                Post.class,
                R.layout.item_post,
                RecyclerViewFragment.PostViewHolder.class,
                mKeyRef,
                mIndexedPostRef
        ) {
            @Override
            protected void populateViewHolder(RecyclerViewFragment.PostViewHolder viewHolder, Post model, int position) {
                viewHolder.favouriteButton.setVisibility(View.GONE);
                viewHolder.setText(model.getText());
            }
        };
    }


}
