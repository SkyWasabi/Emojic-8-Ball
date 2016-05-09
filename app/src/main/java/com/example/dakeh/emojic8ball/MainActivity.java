package com.example.dakeh.emojic8ball;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        LinearLayout linear = new LinearLayout(this);
        linear.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linear.setOrientation(LinearLayout.VERTICAL);

        RelativeLayout relative = new RelativeLayout(this);
        relative.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        //relative.setBackgroundResource(R.drawable.background);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.addView(relative);

        final EditText question = new EditText(this);
        question.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        RelativeLayout.LayoutParams editpa = (RelativeLayout.LayoutParams)question.getLayoutParams();
        editpa.addRule(RelativeLayout.ALIGN_PARENT_END);
        question.setId(View.generateViewId());
        question.setHint("Ask a question");
        question.setSingleLine();


        ImageView background = new ImageView(this);
        background.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        Resources res = getResources();
        background.setImageDrawable(res.getDrawable(R.drawable.background));
        RelativeLayout.LayoutParams bgpa = (RelativeLayout.LayoutParams)background.getLayoutParams();
        background.setId(View.generateViewId());
        background.setScaleType(ImageView.ScaleType.FIT_XY);
        bgpa.addRule(RelativeLayout.BELOW, question.getId());

        final ImageView circle = new ImageView(this);
        circle.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        RelativeLayout.LayoutParams circlepa = (RelativeLayout.LayoutParams)circle.getLayoutParams();
        circlepa.addRule(RelativeLayout.CENTER_VERTICAL);
        circlepa.addRule(RelativeLayout.ALIGN_PARENT_START);
        circle.setImageDrawable(res.getDrawable(R.drawable.circle1));

        final TextView response = new TextView(this);
        response.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        response.setText("Hello World");
        response.setTextColor(Color.parseColor("#FFFFFF"));
        response.setTextSize(22);
        RelativeLayout.LayoutParams respa = (RelativeLayout.LayoutParams)response.getLayoutParams();
        respa.addRule(RelativeLayout.CENTER_VERTICAL);
        respa.addRule(RelativeLayout.CENTER_HORIZONTAL);

        Button button = new Button(this);
        button.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 180));
        button.setText("History");
        button.setTextColor(Color.parseColor("#000000"));
        RelativeLayout.LayoutParams buttonpa = (RelativeLayout.LayoutParams)button.getLayoutParams();
        buttonpa.addRule(RelativeLayout.ALIGN_BOTTOM, background.getId());
        button.setBackgroundResource(R.drawable.shakebutton);


        relative.addView(background);
        relative.addView(question);
        relative.addView(circle);
        relative.addView(response);
        relative.addView(button);

        addContentView(linear, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        String [] extraResponseArray = {"Hello World", "Happy go lucky", ":)"};

        final MagicEightBallModel mb = new MagicEightBallModel(extraResponseArray);

        String filepath = this.getFilesDir().getPath().toString() + "/myobject.txt";
        final File f = new File(filepath);

        final ArrayList<QuestionResponseModel> questionResponseModelArrayList = new ArrayList<QuestionResponseModel>();
        writeArrayToFile(questionResponseModelArrayList, f);

        //noDPI //drawable

        //final EditText question = (EditText) findViewById(R.id.questioninput);
        //final ImageView background = (ImageView) findViewById(R.id.bg);
        //final ImageView circle = (ImageView) findViewById(R.id.circle);
        //final TextView response = (TextView) findViewById(R.id.response);

        final TypedArray circlearray = getResources().obtainTypedArray(R.array.circlearray);

        question.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(question.getWindowToken(), 0);
                final QuestionResponseModel questionresponse = new QuestionResponseModel();
                if (((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) && (question.getText().toString().trim().length() != 0)) {
                    questionresponse.setQuestion(question.getText().toString().trim());
                    functions(mb,circle, circlearray, response, questionresponse);
                    questionResponseModelArrayList.add(questionresponse);
                    writeArrayToFile(questionResponseModelArrayList, f);

                    Log.d("Keyboard pressed enter", "");
                }

                else {
                    Toast.makeText(getApplicationContext(), "No question asked", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });

        //Button button = (Button)findViewById(R.id.shakebutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, HistoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("value", questionResponseModelArrayList);
                i.putExtras(bundle);
                startActivity(i);


//                final QuestionResponseModel questionresponse = new QuestionResponseModel();
//                if (question.getText().toString().matches("")) {
//                    Toast.makeText(getApplicationContext(), "No question asked", Toast.LENGTH_SHORT).show();
//
//                }
//
//                else {
//                    questionresponse.setQuestion(question.getText().toString().trim());
//                    functions(mb,circle, circlearray, response, questionresponse);
//                    questionResponseModelArrayList.add(questionresponse);
//                    writeArrayToFile(questionResponseModelArrayList, f);
//
//                    Log.d("Button Triggered", "");
//                }
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

    public void functions (MagicEightBallModel mb, ImageView circle, TypedArray circlearray, TextView response, QuestionResponseModel questionResponseModel)
    {
        Random randomGenerator = new Random();

        int randomcircleindex = randomGenerator.nextInt(6);
        int resID = circlearray.getResourceId(randomcircleindex, 0);

        String answer = generateResponse(mb, circle, circlearray);

        AlphaAnimation animation1 = new AlphaAnimation(1f, 0f);
        response.startAnimation(animation1);
        response.setText(answer);
        questionResponseModel.setAnswer(answer);
        circle.setImageResource(resID);
    }

    public void writeArrayToFile(ArrayList<QuestionResponseModel> questionResponseModels, File f) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(questionResponseModels);
            oos.flush();
            oos.close();
        }catch (Exception e) {
            Log.e(getLocalClassName(), "Can't save records " + e.getMessage());
        }


    }

    public void readArrayFromFile(File f) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            QuestionResponseModel record = new QuestionResponseModel();
            ArrayList<QuestionResponseModel> arrayofrecord = (ArrayList<QuestionResponseModel>) ois.readObject();

        }catch (Exception e) {
            Log.e(getLocalClassName(), "Can't read records " + e.getMessage());
        }
    }
}
