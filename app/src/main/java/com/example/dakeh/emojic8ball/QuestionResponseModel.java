package com.example.dakeh.emojic8ball;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by dakeh on 4/11/2016.
 */
public class QuestionResponseModel implements Serializable{
    String question;
    String answer;

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
}

