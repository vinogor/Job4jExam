package ru.job4j.exam;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ExamActivity extends AppCompatActivity {

    private static final String TAG = "ExamActivity";
    private int rotate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_exam);

        Log.d(TAG, "    init rotate = " + rotate);
        Log.d(TAG, "    looking for rotate old value");
        if (savedInstanceState != null) {
            rotate = savedInstanceState.getInt("rotate");
        } else {
            Log.d(TAG, "    savedInstanceState == null");
        }
        Log.d(TAG, "    actual rotate = " + rotate);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        rotate++;
        Log.d(TAG, "    rotate +1 = " + rotate);
        outState.putInt("rotate", rotate);
        Log.d(TAG, "    saved in bundle");
    }
}
