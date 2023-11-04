package com.alikocak.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Duration;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView kenny1;
    ImageView kenny2;
    ImageView kenny3;
    ImageView kenny4;
    ImageView kenny5;
    ImageView kenny6;
    ImageView kenny7;
    ImageView kenny8;
    ImageView kenny9;

    TextView txtScore;
    TextView txtTime;

    Random rnd;
    Handler handler;
    Runnable runnable;
    ImageView[] kennies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kenny1 = findViewById(R.id.kenny1);
        kenny2 = findViewById(R.id.kenny2);
        kenny3 = findViewById(R.id.kenny3);
        kenny4 = findViewById(R.id.kenny4);
        kenny5 = findViewById(R.id.kenny5);
        kenny6 = findViewById(R.id.kenny6);
        kenny7 = findViewById(R.id.kenny7);
        kenny8 = findViewById(R.id.kenny8);
        kenny9 = findViewById(R.id.kenny9);

        kennies= new ImageView[] {kenny1,kenny2,kenny3,kenny4,kenny5,kenny6,kenny7,kenny8,kenny9};

        txtScore = findViewById(R.id.ttScore);
        txtTime = findViewById(R.id.txtTime);





    }
    int score;
    public void catchKenny(View view){
        score++;
        txtScore.setText("Score : "+score);
    }
    public void startGame(View view){
        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long l) {
                txtTime.setText("Time : "+l/1000);

            }

            @Override
            public void onFinish() {
                handler.removeCallbacks(runnable);

                for (ImageView kenny: kennies) {
                    kenny.setVisibility(View.INVISIBLE);

                txtTime.setText("Time off");

                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Restart  ?");
                    alert.setMessage("Are you sure to restart game ?");
                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                    });

                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this,"Good bye", Toast.LENGTH_LONG).show();
                            try {
                                Thread.sleep(1000);
                            }
                            catch (Exception e){
                                System.out.println("Error in Thread");
                            }

                            finish();
                        }
                    });

                    alert.show();
                }
            }
        }.start();

        hideImages();
    }

    private void hideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView kenny: kennies) {
                    kenny.setVisibility(View.INVISIBLE);
                }
                rnd = new Random();
                int index = rnd.nextInt(9);
                kennies[index].setVisibility(View.VISIBLE);


                handler.postDelayed(this,500);
            }
        };

        handler.post(runnable);
    }
}