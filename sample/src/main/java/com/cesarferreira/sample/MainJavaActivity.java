package com.cesarferreira.sample;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.gluehome.common.data.log.Timber;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_java);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Timber.d("Heyyy");
                Timber.d(
                        "Bit %d = %s (%s)",
                        1,
                        "yooo",
                        "double yoooo"
                )
            }
        });
    }

}
