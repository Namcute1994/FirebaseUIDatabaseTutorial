package com.software.fire.firebaseuidatabasetutorial.utils;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Brad on 12/26/2016.
 */

public class Utils {
    public static String getUID() {
        String url = FirebaseDatabase.getInstance().getReference(Constants.POSTS).push().toString();
        return url.substring(url.lastIndexOf('/'), url.length());
    }
}
