package com.example.dakeh.emojic8ball;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by dakeh on 4/11/2016.
 */
public class QuestionResponseModel implements Serializable{
    String question;
    String answer;
    String imageurl;
    String username;

    private static final long serialVersionUID = 0L;

    public void setQuestion(String q) {
        question = q;
    }

    public void setAnswer(String a) {
        answer = a;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getImageURL() {return  this.imageurl;}

    public void setImageURL(String imgurl) {this.imageurl = imgurl;}

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

