package com.seyyedehtorkanhesari_hw1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityResultLauncher<Intent> secondActivityIntentLauncher;
    ActivityResultLauncher<Intent> createActivityIntentLauncher;

    private ValueAnimator colorAnim;
    String imgName = "";
    int[] imgIds = {R.drawable.pizza, R.drawable.burger, R.drawable.doner, R.drawable.lahmacun};



    Spinner spImage;
    ImageView imageSP;
    TextView tvTitle;
    Button btnSecond;
    Button btnCreate;
    EditText editNum1;
    EditText editNum2;
    EditText editNum3;
    EditText editNum4;
    EditText editSTR1;
    EditText editSTR2;
    SeekBar sb;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Hiding title bar using code
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.tvTitle);
        spImage = findViewById(R.id.spImage);
        imageSP = findViewById(R.id.imageSP);
        btnSecond = findViewById(R.id.btnSecond);
        btnCreate = findViewById(R.id.btnCreate);
        editNum1 = findViewById(R.id.editNum1);
        editNum2 = findViewById(R.id.editNum2);
        editNum3 = findViewById(R.id.editNum3);
        editNum4 = findViewById(R.id.editNum4);
        editSTR1 = findViewById(R.id.editSTR1);
        editSTR2 = findViewById(R.id.editSTR2);


        spImage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                imageSP.setImageResource(imgIds[i]);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                float scale =  ((progress / 10.0f)+1);
                imageSP.setScaleX(scale);
                imageSP.setScaleY(scale);
            }
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar){

            }
        });

        btnSecond.setOnClickListener(this);
        btnCreate.setOnClickListener(this);

        colorAnim = ObjectAnimator.ofInt(tvTitle, "textColor",Color.MAGENTA, Color.RED, Color.BLUE,Color.GREEN,Color.YELLOW);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();

        secondActivityIntentLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();

                            String msg = data.getStringExtra("res");

                            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                        }

                    }
                });

        createActivityIntentLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent receivedIntent = result.getData();
                            String msg = receivedIntent.getStringExtra("res");

                            makeAndShowDialog(msg + " was viewd.");
                        }

                    }
                });



    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        if (view.getId() == R.id.btnSecond) {
            intent = new Intent(this, SecondActivity.class);

            int num1 = Integer.parseInt(editNum1.getText().toString());
            int num2 = Integer.parseInt(editNum2.getText().toString());
            int num3 = Integer.parseInt(editNum3.getText().toString());
            int num4 = Integer.parseInt(editNum4.getText().toString());
            String str1 = editSTR1.getText().toString();
            String str2 = editSTR2.getText().toString();

            Bundle b = new Bundle();
            b.putInt("num1", num1);
            b.putInt("num2", num2);
            b.putInt("num3", num3);
            b.putInt("num4",num4);
            b.putString("str1", str1);
            b.putString("str2", str2);

            intent.putExtras(b);

            secondActivityIntentLauncher.launch(intent);
        } else if (view.getId() == R.id.btnCreate) {
            intent = new Intent(this, ThirdActivity.class);

            Bundle b = new Bundle();
            b.putString("imgName", spImage.getSelectedItem().toString());
            intent.putExtras(b);

            createActivityIntentLauncher.launch(intent);
        }
    }

    private void makeAndShowDialog(String message) {
        AlertDialog.Builder box = new AlertDialog.Builder(this);
        box.setTitle("FoodOrder");
        box.setMessage(message);

        box.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        box.create();
        box.show();
    }


}