package ru.job4j.exam.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.job4j.exam.R;

import static ru.job4j.exam.QuestionActivity.RESULT_FOR;

public class ResultFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

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
}
