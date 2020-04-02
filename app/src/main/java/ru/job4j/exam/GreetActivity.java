package ru.job4j.exam;

import android.util.Log;

import androidx.fragment.app.Fragment;

import ru.job4j.exam.fragments.GreetFragment;

public class GreetActivity extends BaseActivity {
    @Override
    public Fragment loadFrg() {
        Log.d("fragments", "class GreetActivity - loadFrg");
        return new GreetFragment();
    }
}