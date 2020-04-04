package ru.job4j.exam;

import androidx.fragment.app.Fragment;

import ru.job4j.exam.fragments.QuestionFragment;

public class QuestionActivity extends BaseActivity {

    public static final String HINT_FOR = "hint_for";
    public static final String RESULT_FOR = "result_for";

    @Override
    public Fragment loadFrg() {
        return new QuestionFragment();
    }
}
