package com.ys.yoosir.circleprogressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ys.yoosir.view.CircleImageView;

public class MainActivity extends AppCompatActivity {

    CircleImageView circleProgressBar;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleProgressBar = (CircleImageView) findViewById(R.id.circleProgressBar);
        circleProgressBar.setColorSchemeResources(R.color.holo_blue_light,R.color.holo_green_light,R.color.holo_orange_light,R.color.holo_red_light);
        circleProgressBar.setRefreshing(true);
        tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circleProgressBar.setRefreshing(!circleProgressBar.isShow());
            }
        });


    }
}
