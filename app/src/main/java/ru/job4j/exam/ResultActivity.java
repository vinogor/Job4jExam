package ru.job4j.exam;

import androidx.fragment.app.Fragment;

import ru.job4j.exam.fragments.ResultFragment;

public class ResultActivity extends BaseActivity {

    @Override
    public Fragment loadFrg() {
        return new ResultFragment();
    }
}
