package ru.job4j.exam;

import android.util.Log;

import androidx.fragment.app.Fragment;

import ru.job4j.exam.fragments.HintFragment;

import static ru.job4j.exam.QuestionActivity.HINT_FOR;

public class HintActivity extends BaseActivity {

    @Override
    public Fragment loadFrg() {
        Log.d(MY_LOG, "HintActivity - loadFrg");
        return HintFragment.of(
                getIntent().getIntExtra(HINT_FOR, 0)
        );
    }
}
