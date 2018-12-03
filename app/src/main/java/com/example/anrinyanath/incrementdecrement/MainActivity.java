package com.example.anrinyanath.incrementdecrement;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {

    private TextView number;
    private int numberValue=0;
    SharedPreferences sharedpreferences;
    public static final String NumberVal = "numberval";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number=(TextView)findViewById(R.id.numberText);
        //numberValue=sharedpreferences.getInt(NumberVal,100);
        sharedpreferences=getSharedPreferences("MyPref", MODE_PRIVATE);
        numberValue=sharedpreferences.getInt(NumberVal, 0);
        number.setText(" " + numberValue);
    }

    public void clickButton(View view) {
        switch (view.getId()){
            case R.id.incrementButton:
                Log.d("error", "after");
                numberValue++;
                number.setText(" " + numberValue);

                break;
            case R.id.decrementButton:
                numberValue--;
                number.setText(" " + numberValue);
                if(numberValue<=0){
                    numberValue=0;
                    number.setText(" " + numberValue);
                }
                break;
            case R.id.resetButton:
                numberValue=0;
                number.setText(" "+numberValue);
                break;
        }
    }
    protected void onPause(){
        super.onPause();
        sharedpreferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(NumberVal, numberValue);
        editor.commit();
    }
    protected void onDetroy(){
        super.onDestroy();
        sharedpreferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(NumberVal, numberValue);
        editor.commit();
    }
}
