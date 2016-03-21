package com.example.dakeh.emojic8ball;

import android.util.Log;

import java.util.Objects;

/**
 * Created by dakeh on 3/7/2016.
 */
public class MagicEightBallModel extends Object {


    String [] responseArray;
    static String [] initialResponseArray = {"Ask again later",
                                    "It is certain",
                                    "It is decidedly so",
                                    "Without a doubt",
                                    "Yes, definitely",
                                    "You may rely on it",
                                    "As I see it, yes",
                                    "Most likely",
                                    "Outlook good",
                                    "Yes",
                                    "Signs point to yes",
                                    "Reply hazy try again",
                                    "Better not tell you now",
                                    "Cannot predict now",
                                    "Concentrate and ask again",
                                    "Don't count on it",
                                    "My reply is no",
                                    "My sources say no",
                                    "Outlook not so good",
                                    "Very doubtful"};

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
