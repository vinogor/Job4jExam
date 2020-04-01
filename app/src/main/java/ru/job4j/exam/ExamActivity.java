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

import java.util.Arrays;
import java.util.List;

public class ExamActivity extends AppCompatActivity {

    private static final String TAG = "ExamActivity";
    private int rotate = 0;
    private int position = 0;     // текущий номер вопроса
    private int oldPosition = 0;  // предыдущий номер вопроса

    // поле содержащее список вопросов
    private final List<Question> questions = Arrays.asList(
            new Question(
                    1, "How many primitive variables does Java have?",
                    Arrays.asList(
                            new Option(1, "1.1"), new Option(2, "1.2"),
                            new Option(3, "1.3"), new Option(4, "1.4")
                    ), 4
            ),
            new Question(
                    2, "What is Java Virtual Machine?",
                    Arrays.asList(
                            new Option(1, "2.1"), new Option(2, "2.2"),
                            new Option(3, "2.3"), new Option(4, "2.4")
                    ), 4
            ),
            new Question(
                    3, "What is happen if we try unboxing null?",
                    Arrays.asList(
                            new Option(1, "3.1"), new Option(2, "3.2"),
                            new Option(3, "3.3"), new Option(4, "3.4")
                    ), 4
            )
    );

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
        buttonNext.setEnabled(false);
        buttonCheck.setEnabled(false);

        this.fillForm();

        // настраиваем поведение кнопки ДАЛЕЕ
        buttonNext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        oldPosition = position;
                        position++;
                        fillForm();
                    }
                }
        );

        // настраиваем поведение кнопки ПРОВЕРИТЬ
        buttonCheck.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showAnswer();
                    }
                }
        );

        // настраиваем поведение кнопки НАЗАД
        buttonPrevious.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        oldPosition = position;
                        position--;
                        fillForm();
                    }
                }
        );

        // настраиваем поведение RadioGroup
        variants.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {

                        // сохранение ответа пользователя
                        questions.get(position).setUserAnswer(checkedId);

                        // обновление доступности кнопок
                        buttonPrevious.setEnabled(position != 0 && questions.get(position - 1).getUserAnswer() != -1);
                        buttonCheck.setEnabled(questions.get(position).getUserAnswer() != -1);
                        buttonNext.setEnabled((position != questions.size() - 1) && questions.get(position).getUserAnswer() != -1);

                    }
                }
        );

        Log.d(TAG, "    init rotate counter = " + rotate);
        Log.d(TAG, "    looking for counter old value");
        if (savedInstanceState != null) {
            rotate = savedInstanceState.getInt("rotate");
        } else {
            Log.d(TAG, "    savedInstanceState == null");
        }
        Log.d(TAG, "    actual rotate counter = " + rotate);
    }

    private void fillForm() {

        // получаем объект вопроса по текущему номеру вопроса
        Question question = this.questions.get(this.position);
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
        int answer = questions.get(position).getUserAnswer();
        // устанавливаем его
        variants.check(answer);

        // если ответ такой же, что и был в предыдущем вопросе, то событие не сработает!
        // но надо обновить доступность кнопок НАЗАД и ДАЛЕЕ при крайних вариантах
        if (answer == questions.get(oldPosition).getUserAnswer()) {
            buttonPrevious.setEnabled(position != 0);
            buttonNext.setEnabled(position != questions.size() - 1);
        }
    }

    private void showAnswer() {
        int id = variants.getCheckedRadioButtonId();
        Question question = this.questions.get(this.position);
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
