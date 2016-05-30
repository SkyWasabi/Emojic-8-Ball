package com.example.dakeh.emojic8ball;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    ArrayList<QuestionResponseModel> retrievedobj = new ArrayList<QuestionResponseModel>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        //ArrayList<QuestionResponseModel> questionResponseModels = (ArrayList<QuestionResponseModel>)bundle.getSerializable("value");

        performDownload();

        //
    }

    public void performDownload() {
        String url = "http://li859-75.members.linode.com/retrieveAllEntries.php";

        new DownloadJSONObj().execute(url);
    }

    private class DownloadJSONObj extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return perfromRequest(urls[0]);
            } catch (IOException e) {

                return "Unable to retrieve. URL may be invalid.";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            HistoryArrayAdapter itemsAdapter = new HistoryArrayAdapter(getApplicationContext(), retrievedobj);

            ListView listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(itemsAdapter);
        }

        private String perfromRequest(String myURL) throws IOException {
            InputStream in = null;

            try {
                URL url = new URL(myURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(10000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();

                int response = connection.getResponseCode();

                Log.v("Response code", String.valueOf(response));

                in = connection.getInputStream();
                String stringResult = parseStream(in);

                fetchDataFromJSONObj(stringResult);

                return stringResult;
            } finally {
                if (in != null) {
                    in.close();
                }
            }
        }

        public  String parseStream(InputStream stream) throws  IOException, UnsupportedEncodingException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            return result.toString();
        }

        private void fetchDataFromJSONObj (String result) {
            try {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    QuestionResponseModel questionResponseModel = new QuestionResponseModel();
                    questionResponseModel.setAnswer(jsonObject.getString("answer"));
                    questionResponseModel.setImageURL(jsonObject.getString("imageURL"));
                    questionResponseModel.setQuestion(jsonObject.getString("question"));
                    questionResponseModel.setUsername(jsonObject.getString("username"));

                    retrievedobj.add(questionResponseModel);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
