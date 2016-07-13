package com.leandroasouza.restapidemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private static final String TAG = "MainActivity";

    //Root URL of our web service
    //AVD
    //public static final String ROOT_URL = "http://10.0.2.2:8080/";
    //Genymotion
    public static final String ROOT_URL = "http://10.0.3.2:8080/";

    //Strings to bind with intent will be used to send data to other activity
    public static final String KEY_BOOK_ID = "key_book_id";
    public static final String KEY_BOOK_NAME = "key_book_name";
    public static final String KEY_BOOK_PRICE = "key_book_price";
    public static final String KEY_BOOK_STOCK = "key_book_stock";

    //List view to show data
    private ListView listView;

    private Retrofit adapter;

    //List of type books this list will store type Book which is our data model
    private List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listViewBooks);

        ProgressDialog loading = ProgressDialog.show(MainActivity.this, "Fetching Data", "Please wait ...", false, false);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        adapter = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        BookAPI api = adapter.create(BookAPI.class);

        api.getBooks().clone().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, " RestApi onResponse  " + response.body().toString());
                    books = response.body();
                    showList();
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {

            }
        });

        loading.dismiss();

        listView.setOnItemClickListener(this);

    }

    private void showList() {

        if (books != null && books.size() > 0){
            String[] items = new String[books.size()];

            for(int i = 0; i < books.size(); i++) {
                items[i] = books.get(i).getName();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.simple_list, items);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //Creating an intent
        Intent intent = new Intent(this, ShowBookDetails.class);

        //Getting the requested book from the list
        Book book = books.get(i);

        //Adding book details to intent
        intent.putExtra(KEY_BOOK_ID,book.getBookId());
        intent.putExtra(KEY_BOOK_NAME,book.getName());
        intent.putExtra(KEY_BOOK_PRICE,book.getPrice());
        intent.putExtra(KEY_BOOK_STOCK,book.getInStock());

        //Starting another activity to show book details
        startActivity(intent);
    }


}
