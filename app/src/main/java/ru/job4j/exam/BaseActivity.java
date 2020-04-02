package ru.job4j.exam;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle saved) {
        super.onCreate(saved);
        Log.d("fragments", "BaseActivity - onCreate");

        setContentView(R.layout.host_frg);

        FragmentManager fm = getSupportFragmentManager();

        if (fm.findFragmentById(R.id.content) == null) {
            fm
                    .beginTransaction()
                    //  куда вставляем / что за фрагмент вставляем
                    .add(R.id.content, loadFrg())
                    .commit();
        }
    }

    public abstract Fragment loadFrg();
}