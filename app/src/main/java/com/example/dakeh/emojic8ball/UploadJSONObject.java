package com.example.dakeh.emojic8ball;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by dakeh on 5/30/2016.
 */

public class UploadJSONObject {

    private static String uploadurl = "http://li859-75.members.linode.com/addEntry.php";
    private static String username = "sht458";
    private String question = null;
    private String answer = null;

    public void performUpload(QuestionResponseModel questionResponseModel) {
        question = questionResponseModel.getQuestion();
        answer = questionResponseModel.getAnswer();

        new UploadJSON().execute();

    }

    private class UploadJSON extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean status = false;
            String result = null;

            try {
                result = performRequest();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }



        public String performRequest() throws IOException {

            String result = null;
            HttpURLConnection connection = null;
            URL url = null;

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("question", question)
                    .appendQueryParameter("answer", answer)
                    .appendQueryParameter("username", username);
            String query = builder.build().getEncodedQuery();

            try {
                url = new URL(uploadurl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);

                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

                connection.connect();
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK)
                {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            connection.getInputStream()));
                    while ((line = br.readLine()) != null)
                    {
                        result += line;
                    }
                }
                else
                {
                    result = "BAD";
                }

            }
            catch (Exception e)
            {
                Log.d("HTTP", "HTTP ERROR " + e.getMessage());

                e.printStackTrace();
            }

            return result;
        }
    }
}
