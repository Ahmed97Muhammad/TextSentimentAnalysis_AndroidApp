package cs.dsu.edu.com.getatextsentimetn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.Console;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import butterknife.BindView;
import butterknife.ButterKnife;
import cs.dsu.edu.com.getatextsentimetn.R;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.city_name)
    EditText data;
    @BindView(R.id.result)
    TextView resultTextView;
    public String link;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);






    }
    public void makeToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    public void getSearch(View view){
        //Toast.makeText(this,"test",Toast.LENGTH_SHORT).show();

        JsonObject json = new JsonObject();
        json.addProperty("data", data.getText().toString());
        //url here
        link = "";
        System.out.println(link);
        //makeToast(link);
        Ion.with(this)
                .load(link).setHeader("Content-Type","application/json").setHeader("Accept","application/json")
                .setJsonObjectBody(json)

                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        if (result == null) {
                            makeToast("error");

                        } else {
                            //JsonObject test = result.getAsJsonArray("weather").get(0).getAsJsonObject();

                            Log.v("Result", result.get("response").getAsString());
                            //progressBar.setVisibility(View.GONE);
                            resultTextView.setText("Sentiment: "+result.get("response").getAsString());

                        }




                    }
                });

    }
}
