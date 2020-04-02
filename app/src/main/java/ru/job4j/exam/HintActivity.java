package ru.job4j.exam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.job4j.exam.store.QuestionStore;

public class HintActivity extends AppCompatActivity {

    private final QuestionStore store = QuestionStore.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hint_activity);

        Button btnBack = findViewById(R.id.hint_back);
        btnBack.setOnClickListener(this::btnBack);

        int question = getIntent().getIntExtra(ExamActivity.HINT_FOR, 0);

        TextView textQuestion = findViewById(R.id.question);
        textQuestion.setText(this.store.get(question).getText());

        TextView textHint = findViewById(R.id.hint_text);
        textHint.setText(this.store.get(question).getHint());
    }

    private void btnBack(View view) {
        onBackPressed();
    }


}
