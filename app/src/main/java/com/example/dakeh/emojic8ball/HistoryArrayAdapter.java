package com.example.dakeh.emojic8ball;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryArrayAdapter extends ArrayAdapter<QuestionResponseModel> {


    ArrayList<QuestionResponseModel> questionResponseModels;
    Context context;


    public HistoryArrayAdapter(Context context, ArrayList<QuestionResponseModel> questionResponseModels) {
        super(context, android.R.layout.simple_list_item_2, questionResponseModels);

    }


    static class ViewHolderItem {
        TextView textView1;
        TextView textView2;
    }

    @Override
    public View getView(int position, View convertView , ViewGroup parent) {

        QuestionResponseModel questionResponseModel = getItem(position);

        ViewHolderItem viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolderItem();
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.historyview, parent, false);
            viewHolder.textView1 = (TextView) convertView.findViewById(R.id.text1);
            viewHolder.textView2 = (TextView) convertView.findViewById(R.id.text2);

            convertView.setTag(viewHolder);
        }

        else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }

        viewHolder.textView1.setText(questionResponseModel.getQuestion());
        viewHolder.textView2.setText(questionResponseModel.getAnswer());

        return convertView;

    }

}
