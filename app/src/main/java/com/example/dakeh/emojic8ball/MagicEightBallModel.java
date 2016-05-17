package com.example.dakeh.emojic8ball;

import android.util.Log;

import java.util.Objects;

/**
 * Created by dakeh on 3/7/2016.
 */
public class MagicEightBallModel extends Object {


    String [] responseArray;
    static String [] initialResponseArray = {};

    public MagicEightBallModel(){
        responseArray = initialResponseArray;
    }

    public MagicEightBallModel(String [] extraresponse) {
//        Log.d("array", Integer.toString(extraresponse.length));

        responseArray = new String[initialResponseArray.length + extraresponse.length];

        System.arraycopy(initialResponseArray, 0, responseArray, 0, initialResponseArray.length);
        System.arraycopy(extraresponse, 0, responseArray, initialResponseArray.length, extraresponse.length);

//        for (int i = 0; i <= responseArray.length; i++)
//        {
//            Log.d("Response", responseArray[i]);
//        }
    }

    public String toString() {
        return responseArray.toString();
    }


}
