package ru.job4j.exam;

import android.util.Log;

import androidx.fragment.app.Fragment;

import ru.job4j.exam.fragments.QuestionFragment;

public class QuestionActivity extends BaseActivity {

    public static final String HINT_FOR = "hint_for";
    public static final String RESULT_FOR = "result_for";

    @Override
    public Fragment loadFrg() {
        Log.d(MY_LOG, "QuestionActivity - loadFrg");
        return new QuestionFragment();
    }
}
