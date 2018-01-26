package be.vives.pieter.githubrestclassdemo.thumper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ThumperService thumperService;
    private String id = "0";
    private String IP_ADDRESS = "192.168.0.237";
    private String PORT = "3000";
    private int red;
    private int green;
    private int blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button post_pixels_color = (Button) findViewById(R.id.pixelButtonColor);
        post_pixels_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                red = ((SeekBar) findViewById(R.id.redBar)).getProgress();
                green = ((SeekBar) findViewById(R.id.greenBar)).getProgress();
                blue = ((SeekBar) findViewById(R.id.blueBar)).getProgress();

                // Create a call instance for the request
                Call<Pixel> call = thumperService.setPixels(id, red, green, blue);

                // Make the asynchronous call
                call.enqueue(new Callback<Pixel>() {
                    @Override
                    public void onResponse(Call<Pixel> call, Response<Pixel> response) {
                        // This executes on the GUI Thread, so we can access the UI components
                        if (response.body() != null) {
                            Log.e("REST", "The colours are: " + red + ", " + green + ", " + blue);
                        } else {
                            // Snackbar.make(getApplicationContext(), "Replace with your own action", Snackbar.LENGTH_LONG)
                            //         .setAction("Action", null).show();

                            Log.e("REST", "Could not send colours");
                        }
                    }

                    @Override
                    public void onFailure(Call <Pixel> call, Throwable t) {
                        Log.e("REST", "Could not make request: " + t);
                    }
                });
            }

        });

        Button post_pixels_off = (Button) findViewById(R.id.pixelButtonOff);
        post_pixels_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                red = 0;
                green = 0;
                blue = 0;

                // Create a call instance for the request
                Call<Pixel> call = thumperService.setPixels(id, red, green, blue);

                // Make the asynchronous call
                call.enqueue(new Callback<Pixel>() {
                    @Override
                    public void onResponse(Call<Pixel> call, Response<Pixel> response) {
                        // This executes on the GUI Thread, so we can access the UI components
                        if (response.body() != null) {
                            Log.e("REST", "The colours are: " + red + ", " + green + ", " + blue);
                        } else {
                            // Snackbar.make(getApplicationContext(), "Replace with your own action", Snackbar.LENGTH_LONG)
                            //         .setAction("Action", null).show();

                            Log.e("REST", "Could not send colours");
                        }
                    }

                    @Override
                    public void onFailure(Call <Pixel> call, Throwable t) {
                        Log.e("REST", "Could not make request: " + t);
                    }
                });
            }

        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + IP_ADDRESS + ":" + PORT + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        thumperService = retrofit.create(ThumperService.class);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
