package com.oldhigh.learnmeterialedittext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.oldhigh.learnmeterialedittext.view08.MaterialActivity;
import com.oldhigh.learnmeterialedittext.view08.MaterialEditText;
import com.oldhigh.learnmeterialedittext.view09.LayoutMeasureActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        getApplicationContext() ,
                        LayoutMeasureActivity.class
                ));
            }
        });

    }
}
