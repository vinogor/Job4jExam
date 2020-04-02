package ru.job4j.exam;

import androidx.fragment.app.Fragment;

import ru.job4j.exam.fragments.HintFragment;

public class HintActivity extends BaseActivity {

    @Override
    public Fragment loadFrg() {
        return new HintFragment();
    }
}
