package com.seyyedehtorkanhesari_hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    String msg;
    TextView txtRes;

    Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hiding title bar using code
        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_second);

        backButton = findViewById(R.id.button);
        txtRes = findViewById(R.id.txtRes);

        intent = getIntent();

        backButton.setOnClickListener(this);

        Bundle b = intent.getExtras();
        int num1 = b.getInt("num1");
        int num2 = b.getInt("num2");
        int num3 = b.getInt("num3");
        int num4 = b.getInt("num4");
        String str1 = b.getString("str1");
        String str2 = b.getString("str2");
        double res = ((num1*135)+(num2*130)+(num3*180)+(num4*89));
        String str = str1.concat(" ").concat(str2);

        txtRes.setText("Thanks for your purchase "+str+"\nEnjoy your meal!");
        txtRes.append("\nTotal payment is " + res  );
        msg = "Thanks for your purchase"+str+"\nEnjoy your meal!"+"\nTotal payment is "+ res;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("res",msg);
        setResult(RESULT_OK, intent);
        finish();
    }
}