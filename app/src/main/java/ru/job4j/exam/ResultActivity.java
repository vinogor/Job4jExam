package ru.job4j.exam;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);

        String result = getIntent().getStringExtra(ExamActivity.RESULT_FOR);

        TextView text = findViewById(R.id.text_result);
        text.setText(result);
    }
}
