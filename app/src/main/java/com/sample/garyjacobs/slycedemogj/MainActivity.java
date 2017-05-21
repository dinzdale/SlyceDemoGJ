package com.sample.garyjacobs.slycedemogj;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.animation.ValueAnimatorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.slyce.Slyce;
import com.android.slyce.listeners.OnSlyceOpenListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView slyceDemo;
    private TextView garyJacobs;

    private Button openButton;
    private Slyce slyce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openButton = (Button) findViewById(R.id.open_button);
        openButton.setOnClickListener(openButtonListener);

        slyceDemo = (TextView) findViewById(R.id.slyce_demo);
        garyJacobs = (TextView) findViewById(R.id.by_gary_jacobs);

    }

    View.OnClickListener openButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (slyce == null) {
                slyce = Slyce.getInstance(MainActivity.this);
            }

            if (!slyce.isOpen()) {
                slyce.open(getResources().getString(R.string.client_id), new OnSlyceOpenListener() {
                    @Override
                    public void onOpenSuccess() {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Slyce Open Success")
                                .setMessage("Premium: " + slyce.isPremiumUser() + "\n" +
                                        "2D Search: " + slyce.is2DSearchEnabled())
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                                        MainActivity.this.startActivity(intent);
                                    }
                                })
                                .show();

                    }

                    @Override
                    public void onOpenFail(String s) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setMessage("Slyce Open Failed")
                                .setPositiveButton("OK", null)
                                .setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        MainActivity.this.finish();
                                    }
                                })
                                .show();
                    }
                });
            }
            else {
                // Go to Camera activity
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                MainActivity.this.startActivity(intent);
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        doIntroAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (slyce != null && slyce.isOpen()) {
            slyce.close();
        }
        slyce = null;
    }

    private void doIntroAnimation() {

        slyceDemo.setScaleX(0.0f);
        slyceDemo.setScaleY(0.0f);

        garyJacobs.setAlpha(0.0f);

        slyceDemo.animate()
                .scaleX(1.0f)
                .scaleY(1.0f)
                .setStartDelay(2000)
                .setDuration(3000)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        garyJacobs.animate()
                                .alpha(1.0f)
                                .setDuration(3000)
                                .start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .start();


    }

}
