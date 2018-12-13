package com.jw0ng.segmentedcontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jw0ng.widget.Segmented;
import com.jw0ng.widget.SegmentedControl;

public class MainActivity extends AppCompatActivity {

    private SegmentedControl segmentedControl;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        segmentedControl = findViewById(R.id.sg_control);
        segmentedControl.setOnItemSelectedListener(new SegmentedControl.OnItemSelectedListener() {
            @Override
            public void onItemSelected(Segmented segmented, int index) {
                switch (index) {
                    case 0:
                        Log.v(TAG,index + "");
                        Log.v(TAG,segmented.getText());
                        break;
                    case 1:
                        segmented.setDescription("1");
                        break;
                }

            }
        });

    }
}
