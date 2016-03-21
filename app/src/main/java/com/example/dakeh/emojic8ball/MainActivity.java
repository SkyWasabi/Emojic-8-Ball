package com.example.dakeh.emojic8ball;

import android.content.res.TypedArray;
import android.media.Image;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String [] extraResponseArray = {"Hello World", "Happy go lucky", ":)"};

        final MagicEightBallModel mb = new MagicEightBallModel(extraResponseArray);

        //noDPI //drawable

        final EditText question = (EditText) findViewById(R.id.questioninput);
        final ImageView background = (ImageView) findViewById(R.id.bg);
        final ImageView circle = (ImageView) findViewById(R.id.circle);
        final TextView response = (TextView) findViewById(R.id.response);

        final String checktext = question.getText().toString().trim();
        final TypedArray circlearray = getResources().obtainTypedArray(R.array.circlearray);

        question.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) && (question.getText().toString().trim().length() != 0)) {
                    functions(mb,circle, circlearray, response);
                    Log.d("Keyboard pressed enter", "");
                }

                else {
                    Toast.makeText(getApplicationContext(), "No question asked", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });

        Button button = (Button)findViewById(R.id.shakebutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (question.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "No question asked", Toast.LENGTH_SHORT).show();

                }

                else {
                    functions(mb,circle, circlearray, response);
                    Log.d("Button Triggered", "");
                }
            }
        });

        double age = 21;
        String name = "Tan Shou Heng";
        String responseLog;

        String agge = String.format("%.2f", age);

        Log.d("Name", "Tan Shou Heng");
        Log.d("Age", agge);
        Log.d("Name in variable", name);



        Log.d("Question 1","Will I get full marks for this lab");
        responseLog = generateResponse(mb, circle, circlearray);
        Log.d("Response 1", responseLog);

        Log.d("Question 2","Will the Cronulla sharks receive a premiership this year");
        responseLog = generateResponse(mb, circle, circlearray);
        Log.d("Response 2", responseLog);

        Log.d("Question 3","Will I end up becoming a cat person when I get old");
        responseLog = generateResponse(mb, circle ,circlearray);
        Log.d("Response 3", responseLog);

        Log.d("All the response",mb.toString());


    }

    public static String generateResponse(MagicEightBallModel mb, ImageView circle, TypedArray circlearray) {
//
//        String test = Integer.toString(mb.responseArray.length);
//        Log.d("ArraySize", test);
        Random randomGenerator = new Random();
        int randomint = randomGenerator.nextInt(mb.responseArray.length);
        String num = Integer.toString(randomint);
        Log.d("Number", num);



//        AlphaAnimation animation1 = new AlphaAnimation(1f, 0f);
//        circle.startAnimation(animation1);
//

        return mb.responseArray[randomint];
    }

    public void functions (MagicEightBallModel mb, ImageView circle, TypedArray circlearray, TextView response)
    {
        Random randomGenerator = new Random();

        int randomcircleindex = randomGenerator.nextInt(6);
        int resID = circlearray.getResourceId(randomcircleindex, 0);

        AlphaAnimation animation1 = new AlphaAnimation(1f, 0f);
        response.startAnimation(animation1);
        response.setText(generateResponse(mb, circle, circlearray));
        circle.setImageResource(resID);
    }

}
