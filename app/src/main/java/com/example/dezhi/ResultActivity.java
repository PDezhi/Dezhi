package com.example.dezhi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.dezhi.model.RatingResults;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class ResultActivity extends AppCompatActivity  implements View.OnClickListener {
    TextView textViewResult;
    EditText editTextName;
    RadioGroup radioGroupRating, radioGroupSorting;
    Button buttonGoBack;


    ArrayList<RatingResults> listOfRatingResults;
    ArrayList<RatingResults> currentListOfRatingResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initialize();
    }

    private void initialize() {
        textViewResult = findViewById(R.id.editTextTextMultiLine);
        editTextName = findViewById(R.id.editTextTextPersonName);
        radioGroupRating = findViewById(R.id.radioGroupRating);
        radioGroupSorting = findViewById(R.id.radioGroupSorting);
        buttonGoBack=findViewById(R.id.buttonGoBack);
        buttonGoBack.setOnClickListener(this);

        Bundle bundle = getIntent().getBundleExtra("intentExtra");
        Serializable bundledListOfRatingResults = bundle.getSerializable("bundleExtra");

        listOfRatingResults = (ArrayList<RatingResults>) bundledListOfRatingResults;
        currentListOfRatingResults=listOfRatingResults;

        showListOfRatingResults(listOfRatingResults);

    }

    private void showListOfRatingResults(ArrayList<RatingResults> listOfStudents) {
        textViewResult.setText(convertToString(listOfStudents));
    }



    public void showMe(View view) {
        int selected_radio_btn = radioGroupRating.getCheckedRadioButtonId();
        int ratingLevel = 0;
        ArrayList<RatingResults> results = new ArrayList<RatingResults>();

        switch (selected_radio_btn) {
            case R.id.radioButton1:
                ratingLevel = 1;
                results = getListOfRatingResultsByRating(ratingLevel);
                break;
            case R.id.radioButton2:
                ratingLevel = 2;
                results = getListOfRatingResultsByRating(ratingLevel);
                break;
            case R.id.radioButton3:
                ratingLevel = 3;
                results = getListOfRatingResultsByRating(ratingLevel);
                break;
            default:
                results = listOfRatingResults;
                break;
        }
        currentListOfRatingResults = results;
        textViewResult.setText(convertToString(results));
    }
    public void sorting(View view) {
        int selected_radio_btn = radioGroupSorting.getCheckedRadioButtonId();
        ArrayList<RatingResults> results = new ArrayList<RatingResults>();

        switch (selected_radio_btn) {
            case R.id.radioButtonAscending:
                Collections.sort(currentListOfRatingResults);
                break;
            case R.id.radioButtonDescending:
                Collections.sort(currentListOfRatingResults);
                Collections.reverse(currentListOfRatingResults);
                break;

        }
        textViewResult.setText(convertToString(currentListOfRatingResults));
    }

    private ArrayList<RatingResults> getListOfRatingResultsByRating(int ratingLevel) {
        ArrayList<RatingResults> results = new ArrayList<RatingResults>();
        if (ratingLevel == 1) {
            for (RatingResults oneRating : listOfRatingResults) {
                if (oneRating.getRating() < 2)
                    results.add(oneRating);
            }
        } else if (ratingLevel == 2) {
            for (RatingResults oneRating : listOfRatingResults) {
                if (oneRating.getRating() == 2 || oneRating.getRating() == 2.5)
                    results.add(oneRating);
            }
        } else if (ratingLevel == 3) {
            for (RatingResults oneRating : listOfRatingResults) {
                if (oneRating.getRating() == 3)
                    results.add(oneRating);
            }
        } else
            results = listOfRatingResults;

        return results;
    }

    private String convertToString(ArrayList<RatingResults> results) {
        String str = "";
        for (RatingResults oneRating : results) {
            str = str + oneRating;
        }
        return str;
    }

    public void goBack() {
        Intent intent = new Intent();
        intent.putExtra("return_result_tag", editTextName.getText().toString());

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.buttonGoBack)
        goBack();
    }
}