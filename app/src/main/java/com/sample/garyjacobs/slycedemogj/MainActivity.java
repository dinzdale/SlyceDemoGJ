package com.sample.garyjacobs.slycedemogj;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.android.slyce.Slyce;
import com.android.slyce.listeners.OnSlyceOpenListener;

public class MainActivity extends AppCompatActivity {

    private Button openButton;
    private Slyce slyce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openButton = (Button) findViewById(R.id.open_button);
        openButton.setOnClickListener(openButtonListener);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.too);
//        setSupportActionBar(toolbar);

    }

    View.OnClickListener openButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            slyce = Slyce.getInstance(MainActivity.this);
            slyce.open(getResources().getString(R.string.client_id), new OnSlyceOpenListener() {
                @Override
                public void onOpenSuccess() {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Slyce Open Sucess")
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
    };

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (slyce != null && slyce.isOpen()) {
            slyce.close();
        }
    }
}
