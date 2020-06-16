package com.example.dezhi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dezhi.model.RatingResults;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener, AdapterView.OnItemSelectedListener {

    final static int REQUEST_CODE = 1;
    TextView textView;
    Spinner spinnerMeal;
    ImageView imageViewMeal;
    RatingBar ratingBarMeal;
    Button btnAdd, btnShowAll, btnMeal, btnSalad;


    String listMeal[] = {"Salmon", "Poutine", "Sushi", "Tacos"};
    String listSalad[] = {"Chicken Salad", "Montreal", "Green Salad"};
    int mealPicture[] = {R.drawable.salmon, R.drawable.poutine, R.drawable.sushi, R.drawable.tacos};
    int saladPicture[] = {R.drawable.salmon, R.drawable.poutine, R.drawable.sushi};

    ArrayList<RatingResults> listOfRatingResults;
    ArrayAdapter<String> mealAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {

        listOfRatingResults = new ArrayList<RatingResults>();

        // Reference to ratingBar.................................
        ratingBarMeal = findViewById(R.id.ratingBar);
        //........................................................

        textView = findViewById(R.id.textView);
        imageViewMeal = findViewById(R.id.imageView);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnShowAll = findViewById(R.id.btnShowAll);
        btnShowAll.setOnClickListener(this);

        btnMeal = findViewById(R.id.btnMeal);
        btnMeal.setOnClickListener(this);

        btnSalad = findViewById(R.id.btnSalad);
        btnSalad.setOnClickListener(this);

        // Initialize spinner -----------------------------------
        spinnerMeal = findViewById(R.id.spinnerMeal);
        spinnerMeal.setOnItemSelectedListener(this);

        mealAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,
                listMeal);
        spinnerMeal.setAdapter(mealAdapter);
        //-------------------------------------------------------
    }

    // AdapterView.OnItemSelectedListener ----------------------------------------------------------
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
        // 'parent' The AdapterView where the click happened
        // 'i' is index of selected item in spinner,
        // so we can assign the corresponding image reference
        // from our image array to our imageView
        int image = mealPicture[i];
        imageViewMeal.setImageResource(image);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAdd:
                addRatingResults();
                break;

            case R.id.btnShowAll:
                showAllRatingResults();
                break;
            case R.id.btnMeal:
                showMealItem();
                break;
            case R.id.btnSalad:
                showSaladItem();
                break;
        }
    }

    private void addRatingResults() {

        String name = spinnerMeal.getSelectedItem().toString();

        // Read ratingBar ....................................
        float rating = (float) ratingBarMeal.getRating();
        //....................................................


        // Create new object and add it to our model array....
        RatingResults ratingResult = new RatingResults(name, rating);
        listOfRatingResults.add(ratingResult);
        //....................................................

        // Reset rating bar for next time
        ratingBarMeal.setRating(0);
    }

    private void showAllRatingResults() {

        Bundle bundle = new Bundle();
        bundle.putSerializable("bundleExtra", listOfRatingResults);

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("intentExtra", bundle);
        startActivityForResult(intent, REQUEST_CODE);


//        StringBuilder sb = new StringBuilder("");
//
//        for(RatingResults oneRatingResults : listOfRatingResults) {
//            sb.append(oneRatingResults + "\n");
//        }
//        Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
    }

    private void showMealItem() {
        // Initialize spinner -----------------------------------
        mealAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,
                listMeal);
        spinnerMeal.setAdapter(mealAdapter);
        //-------------------------------------------------------
    }

    private void showSaladItem() {
        // Initialize spinner -----------------------------------
        mealAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,
                listSalad);
        spinnerMeal.setAdapter(mealAdapter);
        //-------------------------------------------------------
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String receivedData = (String) data.getStringExtra("return_result_tag");
        String showData = "Thank you : " + receivedData;

        textView.setText(showData);
    }
}