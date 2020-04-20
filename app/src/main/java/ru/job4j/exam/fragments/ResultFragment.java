package ru.job4j.exam.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.job4j.exam.R;

import static ru.job4j.exam.BaseActivity.MY_LOG;
import static ru.job4j.exam.QuestionActivity.RESULT_FOR;

public class ResultFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(MY_LOG, "ResultFragment - onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(MY_LOG, "ResultFragment - onCreateView");

        View view = inflater.inflate(R.layout.result_layout, container, false);

        String result = getArguments().getString(RESULT_FOR);

        TextView text = view.findViewById(R.id.text_result);
        text.setText(result);

        return view;
    }

    public static ResultFragment of(String result) {
        ResultFragment hint = new ResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString(RESULT_FOR, result);
        hint.setArguments(bundle);
        return hint;
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(MY_LOG, "ResultFragment - onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(MY_LOG, "ResultFragment - onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(MY_LOG, "ResultFragment - onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(MY_LOG, "ResultFragment - onDetach");
    }
}
