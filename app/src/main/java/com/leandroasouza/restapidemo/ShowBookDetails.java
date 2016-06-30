package com.leandroasouza.restapidemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by leandrosouza on 24/06/2016.
 */
public class ShowBookDetails extends AppCompatActivity {

    private TextView textViewBookId;
    private TextView textViewBookName;
    private TextView textViewBookPrice;
    private TextView textViewBookInStock;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_book_details);

        textViewBookId = (TextView) findViewById(R.id.textViewBookId);
        textViewBookName = (TextView) findViewById(R.id.textViewBookName);
        textViewBookPrice = (TextView) findViewById(R.id.textViewBookPrice);
        textViewBookInStock = (TextView) findViewById(R.id.textViewBookInStock);

        Intent intent = getIntent();

        //Displaying values by fetching from intent
        textViewBookId.setText(String.valueOf(intent.getIntExtra(MainActivity.KEY_BOOK_ID, 0)));
        textViewBookName.setText(intent.getStringExtra(MainActivity.KEY_BOOK_NAME));
        textViewBookPrice.setText(intent.getStringExtra(MainActivity.KEY_BOOK_PRICE));
        textViewBookInStock.setText(String.valueOf(intent.getIntExtra(MainActivity.KEY_BOOK_STOCK,0)));
    }
}
