package com.example.dakeh.emojic8ball;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        double age = 21;
        String name = "Tan Shou Heng";
        String responseLog;

        String agge = String.format("%.2f", age);

        Log.d("Name", "Tan Shou Heng");
        Log.d("Age", agge);
        Log.d("Name in variable", name);

        String [] extraResponseArray = {"Hello World", "Happy go lucky"};

        MagicEightBallModel mb = new MagicEightBallModel(extraResponseArray);

        Log.d("Question 1","Will I get full marks for this lab");
        responseLog = generateResponse(mb);
        Log.d("Response 1", responseLog);

        Log.d("Question 2","Will the Cronulla sharks receive a premiership this year");
        responseLog = generateResponse(mb);
        Log.d("Response 2", responseLog);

        Log.d("Question 3","Will I end up becoming a cat person when I get old");
        responseLog = generateResponse(mb);
        Log.d("Response 3", responseLog);

    }

    public static String generateResponse(MagicEightBallModel mb) {
//
//        String test = Integer.toString(mb.responseArray.length);
//        Log.d("ArraySize", test);
        Random randomGenerator = new Random();
        int randomint = randomGenerator.nextInt(mb.responseArray.length);
        String num = Integer.toString(randomint);
        Log.d("Number", num);

        return mb.responseArray[randomint];
    }
}
