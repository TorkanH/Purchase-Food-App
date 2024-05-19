package com.seyyedehtorkanhesari_hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    Intent intent;

    String msg = "Food purchased.";

    TextView txtImageInfo;
    ImageView imageView;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /// Hiding title bar using code
        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_third);

        txtImageInfo = findViewById(R.id.txtImageInfo);
        imageView = findViewById(R.id.imageView);
        btnBack = findViewById(R.id.btnBack);

        intent = getIntent();
        Bundle b = intent.getExtras();
        String imageName = b.getString("imgName");
        txtImageInfo.setText(imageName+" more detailed menu!");
        int resImageID = getResources().getIdentifier( imageName, "drawable", getPackageName());
        imageView.setImageResource(resImageID);
        msg = imageName;




        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("res", msg);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}