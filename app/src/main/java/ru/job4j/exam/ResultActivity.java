package ru.job4j.exam;

import android.util.Log;

import androidx.fragment.app.Fragment;

import ru.job4j.exam.fragments.ResultFragment;

import static ru.job4j.exam.QuestionActivity.RESULT_FOR;

public class ResultActivity extends BaseActivity {

    @Override
    public Fragment loadFrg() {
        Log.d(MY_LOG, "ResultActivity - loadFrg");
        return ResultFragment.of(
                getIntent().getStringExtra(RESULT_FOR)
        );
    }
}
