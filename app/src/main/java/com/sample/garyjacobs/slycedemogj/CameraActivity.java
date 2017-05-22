package com.sample.garyjacobs.slycedemogj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.slyce.SlyceBarcode;
import com.android.slyce.SlyceCameraFragment;
import com.android.slyce.listeners.OnSlyceCameraFragmentListener;

import org.json.JSONArray;
import org.json.JSONObject;

public class CameraActivity extends AppCompatActivity implements OnSlyceCameraFragmentListener {

    private SlyceCameraFragment slyceCameraFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        slyceCameraFragment = SlyceCameraFragment.newInstance(null, true, false);


        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, slyceCameraFragment)
                .addToBackStack(SlyceCameraFragment.class.getName())
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .commit();

    }


    // OnSlyceCameraFragmentListener
    // ...most of these methods are not required to be implemented.

    @Override
    public void onCameraFragmentBarcodeDetected(SlyceBarcode slyceBarcode) {

        Toast.makeText(this, "onCameraFragmentBarcodeDetected", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCameraFragmentImageDetected(String s) {

        Toast.makeText(this, "onCameraFragmentImageDetected", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onCameraFragmentImageInfoReceived(JSONArray jsonArray) {

        Toast.makeText(this, "onCameraFragmentImageInfoReceived", Toast.LENGTH_LONG).show();

    }

    // Received Product data, load Results fragments and pass data as a string
    @Override
    public void onCameraFragmentResultsReceived(JSONObject jsonObject) {

        // Load results fragment
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, ResultsFragment.getInstance(jsonObject.toString()))
                .addToBackStack(ResultsFragment.class.getName())
                .commit();

    }

    @Override
    public void onResultsReceivedExt(String s) {
        Toast.makeText(this, "onResultsReceivedExt", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onCameraFragmentError(String s) {

        Toast.makeText(this, "onCameraFragmentError", Toast.LENGTH_LONG).show();


    }

    @Override
    public void onCameraFragmentFinished() {

        Toast.makeText(this, "onCameraFragmentFinished", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onCameraFragmentBarcodeInfoReceived(JSONObject jsonObject) {
        Toast.makeText(this, "onCameraFragmentBarcodeInfoReceived", Toast.LENGTH_LONG).show();

    }

    // Force move to MainActivity when done backing out of fragments
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            // go back to MainActivity from here
            super.onBackPressed();
        }
    }
}
