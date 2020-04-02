package ru.job4j.exam;

import androidx.fragment.app.Fragment;

import ru.job4j.exam.fragments.QuestionFragment;

public class QuestionActivity extends BaseActivity {

    @Override
    public Fragment loadFrg() {
        return new QuestionFragment();
    }
}
