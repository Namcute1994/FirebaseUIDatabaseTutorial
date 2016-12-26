package com.software.fire.firebaseuidatabasetutorial.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.software.fire.firebaseuidatabasetutorial.R;
import com.software.fire.firebaseuidatabasetutorial.models.Post;
import com.software.fire.firebaseuidatabasetutorial.utils.Constants;
import com.software.fire.firebaseuidatabasetutorial.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {

    private RecyclerView mPostRecyclerView;
    private FirebaseRecyclerAdapter<Post, PostViewHolder> mPostAdapter;
    private View mRootView;
    private DatabaseReference mPostRef;

    private String[] emails = new String[]{"123@gmail,com", "456@gmail,com", "789@gmail,com"};

    public RecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        initialiseView();

        FloatingActionButton fab = (FloatingActionButton) mRootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Post post = new Post();
                post.setUID(Utils.getUID());
                post.setText("Random text: " + post.getUID());

                mPostRef.child(post.getUID()).setValue(post);
            }
        });

        return mRootView;
    }

    private void initialiseView() {
        mPostRef = FirebaseDatabase.getInstance().getReference(Constants.POSTS);
        mPostRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerview);
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setupAdapter();
        mPostRecyclerView.setAdapter(mPostAdapter);
    }

    private void setupAdapter() {
        mPostAdapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(
                Post.class,
                R.layout.item_post,
                PostViewHolder.class,
                mPostRef
        ) {
            @Override
            protected void populateViewHolder(PostViewHolder viewHolder, final Post model, int position) {
                viewHolder.setText(model.getText());
                viewHolder.favouriteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseDatabase.getInstance().getReference(Constants.FAVOURITES)
                                .child(emails[(int) (Math.random() * emails.length)])
                                .child(model.getUID())
                                .setValue(true);
                    }
                });
            }
        };
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        Button favouriteButton;
        TextView postText;

        public PostViewHolder(View itemView) {
            super(itemView);
            favouriteButton = (Button) itemView.findViewById(R.id.favourite_button);
            postText = (TextView) itemView.findViewById(R.id.post_text);
        }

        public void setText(String text) {
            postText.setText(text);
        }
    }
}
