package ru.job4j.exam;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import ru.job4j.exam.store.Option;
import ru.job4j.exam.store.Question;
import ru.job4j.exam.store.QuestionStore;

public class ExamActivity extends AppCompatActivity {

    private static final String TAG = "ExamActivity";
    private final QuestionStore store = QuestionStore.getInstance();
    private int rotate = 0;
    private int position = 0;     // текущий номер вопроса
    private int oldPosition = 0;  // предыдущий номер вопроса

    private Button buttonPrevious;
    private Button buttonNext;
    private Button buttonCheck;
    private RadioGroup variants;
    private TextView tvQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_exam);


        buttonPrevious = findViewById(R.id.button_previous);
        buttonCheck = findViewById(R.id.button_check);
        buttonNext = findViewById(R.id.button_next);
        variants = findViewById((R.id.variants));
        tvQuestion = findViewById(R.id.question);

        buttonPrevious.setEnabled(false);
        buttonCheck.setEnabled(false);
        buttonNext.setEnabled(false);

        buttonNext.setOnClickListener(this::btnNext);
        buttonCheck.setOnClickListener(this::btnCheck);
        buttonPrevious.setOnClickListener(this::btnPrevious);
        variants.setOnCheckedChangeListener(this::rBtnChange);

        this.fillForm();

        Log.d(TAG, "    init rotate counter = " + rotate);
        Log.d(TAG, "    looking for counter old value");
        if (savedInstanceState != null) {
            rotate = savedInstanceState.getInt("rotate");
        } else {
            Log.d(TAG, "    savedInstanceState == null");
        }
        Log.d(TAG, "    actual rotate counter = " + rotate);
    }

    private void btnNext(View view) {
        oldPosition = position;
        position++;
        fillForm();
    }

    private void btnCheck(View view) {
        showAnswer();
    }

    private void btnPrevious(View view) {
        oldPosition = position;
        position--;
        fillForm();
    }

    private void rBtnChange(RadioGroup group, int checkedId) {
        // сохранение ответа пользователя
        store.get(position).setUserAnswer(checkedId);

        // обновление доступности кнопок
        buttonPrevious.setEnabled(position != 0 && store.get(position - 1).getUserAnswer() != -1);
        buttonCheck.setEnabled(store.get(position).getUserAnswer() != -1);
        buttonNext.setEnabled((position != store.size() - 1) && store.get(position).getUserAnswer() != -1);
    }

    private void fillForm() {

        // получаем объект вопроса по текущему номеру вопроса
        Question question = this.store.get(this.position);
        // прописываем текст вопроса на экране
        tvQuestion.setText(question.getText());

        // перебираем и прописываем варианты ответа
        for (int index = 0; index != variants.getChildCount(); index++) {
            RadioButton button = (RadioButton) variants.getChildAt(index);
            Option option = question.getOptions().get(index);
            button.setId(option.getId());
            button.setText(option.getText());
        }

        // получаем сохранённый ранее ответ
        int answer = store.get(position).getUserAnswer();
        // устанавливаем его
        variants.check(answer);

        // если ответ такой же, что и был в предыдущем вопросе, то событие не сработает!
        // но надо обновить доступность кнопок НАЗАД и ДАЛЕЕ при крайних вариантах
        if (answer != -1 && answer == store.get(oldPosition).getUserAnswer() ) {
            buttonPrevious.setEnabled(position != 0);
            buttonNext.setEnabled(position != store.size() - 1);
        }
    }

    private void showAnswer() {
        int id = variants.getCheckedRadioButtonId();
        Question question = this.store.get(this.position);
        Toast.makeText(
                this,
                "Your answer is " + id + ", correct is " + question.getRightAnswer(),
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        rotate++;
        Log.d(TAG, "    rotate counter +1 = " + rotate);
        outState.putInt("rotate", rotate);
        Log.d(TAG, "    saved in bundle");
    }
}
