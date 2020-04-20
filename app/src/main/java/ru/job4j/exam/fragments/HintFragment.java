package ru.job4j.exam.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.job4j.exam.R;
import ru.job4j.exam.store.QuestionStore;

import static ru.job4j.exam.BaseActivity.MY_LOG;
import static ru.job4j.exam.QuestionActivity.HINT_FOR;

public class HintFragment extends Fragment {

    private final QuestionStore store = QuestionStore.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(MY_LOG, "HintFragment - onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(MY_LOG, "HintFragment - onCreateView");

        View view = inflater.inflate(R.layout.hint_activity, container, false);

        Button btnBack = view.findViewById(R.id.hint_back);
        btnBack.setOnClickListener(this::btnBack);

        int question = getArguments().getInt(HINT_FOR, 0);

        TextView textQuestion = view.findViewById(R.id.question);
        textQuestion.setText(this.store.get(question).getText());

        TextView textHint = view.findViewById(R.id.hint_text);
        textHint.setText(this.store.get(question).getHint());

        return view;
    }

    public static HintFragment of(int index) {
        HintFragment hint = new HintFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(HINT_FOR, index);
        hint.setArguments(bundle);
        return hint;
    }

    private void btnBack(View view) {
        getActivity().onBackPressed(); // а тут будем как то отвязываться от родительского активити?
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(MY_LOG, "HintFragment - onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(MY_LOG, "HintFragment - onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(MY_LOG, "HintFragment - onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(MY_LOG, "HintFragment - onDetach");
    }
}
