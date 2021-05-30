package com.e.englishspinwheel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {




    boolean blnButtonRotation = true;
    int IntNumber = 7;;
    long IngDegrees = 0;
    SharedPreferences sharedPreferences;

    Button b_start,b_up,b_down,b_kelime,b_soru ;
    ImageView ImageSelect,ImageRoulette ;




@Override
    protected void onCreate(Bundle savedInstanceState) {

    getWindow() .addFlags(1024) ;
    requestWindowFeature(1) ;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b_start = (Button) findViewById(R.id.ButtonSpin);
        b_up = (Button) findViewById(R.id.buttonUp);
        b_soru = (Button) findViewById(R.id.sorular);
        b_kelime = (Button) findViewById(R.id.kelimeler);
        b_down = (Button) findViewById(R.id.buttonDown);
        ImageSelect = (ImageView) findViewById(R.id.imageSelect);
        ImageRoulette = (ImageView) findViewById(R.id.spinwheel);
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this) ;
        this.IntNumber = this.sharedPreferences.getInt("INT_NUMBER",6) ;
        setImageRoulette(this.IntNumber);



        b_soru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(MainActivity.this, sorular.class);
                startActivity(intent2);

            }
        });

        b_kelime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, kelimeler.class);
                startActivity(intent1);
            }
        });









    }


    @Override
    public void onAnimationStart(Animation animation) {

    this.blnButtonRotation = false ;
    b_start.setVisibility(View.VISIBLE);





    }


    @Override
    public void onAnimationEnd(Animation animation) {

    this.blnButtonRotation = true ;
    b_start.setVisibility(View.VISIBLE);

    }


    @Override
    public void onAnimationRepeat(Animation animation) {


    }



    public void onClickButtonRotation(View v)
    {

        if (this.blnButtonRotation)
        {
            int ran = new Random().nextInt(360) + 3600;
            RotateAnimation rotateAnimation = new RotateAnimation((float)this.IngDegrees, (float)
                    (this.IngDegrees + ((long)ran)) , 1, 0.5f , 1 ,0.5f) ;

            this.IngDegrees = (this.IngDegrees + ((long)ran)) %360;
            rotateAnimation.setDuration((long)ran);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setInterpolator(new DecelerateInterpolator());
            rotateAnimation.setAnimationListener(this);
            ImageRoulette.setAnimation(rotateAnimation);
            ImageRoulette.startAnimation(rotateAnimation);










        }








    }


    @SuppressLint("WrongConstant")
    public void buttonUp (View v)

    {

        if (this.IntNumber < 7)

        {
            this.IntNumber++;
            setImageRoulette(this.IntNumber);
            b_down.setVisibility(View.VISIBLE);
            SharedPreferences.Editor editor = this.sharedPreferences.edit();
            editor.putInt("INT_NUMBER",this.IntNumber);
            editor.commit();




        }

        if (this.IntNumber ==7)
        {
            b_up.setVisibility(View.VISIBLE);
        }











    }

       public void buttonDown(View v)
       {
           if (this.IntNumber > 2)
           {
               IntNumber--;
               setImageRoulette(this.IntNumber);
               b_up.setVisibility(View.VISIBLE);

               SharedPreferences.Editor editor = this.sharedPreferences.edit();
               editor.putInt("INT_NUMBER", this.IntNumber);
               editor.commit();

           }

       if (this.IntNumber > 2)
       {
           b_down.setVisibility(View.VISIBLE);
       }




       }

    private void setImageRoulette(int MyNumber) {
        switch (MyNumber) {
            case 1:
                ImageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.ic_carkiki));
                return;
            case 2:
                ImageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.ic_carkuc));
                return;
            case 3:
                ImageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.ic_carkdort));
                return;
            case 4:
                ImageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.ic_carkbes));
                return;

            case 5:
                ImageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.ic_carkalti));
                return;
            case 6:
                ImageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.ic_carkyedi));
                return;

            case 7:
                ImageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.ic_carksekiz));
                return;

            }
        }



}
