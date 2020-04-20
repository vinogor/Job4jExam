package ru.job4j.exam.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.job4j.exam.HintActivity;
import ru.job4j.exam.R;
import ru.job4j.exam.ResultActivity;
import ru.job4j.exam.store.Option;
import ru.job4j.exam.store.Question;
import ru.job4j.exam.store.QuestionStore;

import static ru.job4j.exam.BaseActivity.MY_LOG;
import static ru.job4j.exam.QuestionActivity.HINT_FOR;
import static ru.job4j.exam.QuestionActivity.RESULT_FOR;

public class QuestionFragment extends Fragment {


    private final QuestionStore store = QuestionStore.getInstance();
    private final int size = store.size();

    private Button buttonPrevious;
    private Button buttonNext;
    private Button buttonHint;
    private Button buttonCheck;
    private RadioGroup variants;
    private TextView tvQuestion;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(MY_LOG, "QuestionFragment - onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(MY_LOG, "QuestionFragment - onCreateView");

        View view = inflater.inflate(R.layout.activity_exam, container, false);

        buttonPrevious = view.findViewById(R.id.button_previous);
        buttonCheck = view.findViewById(R.id.button_check);
        buttonHint = view.findViewById(R.id.hint);
        buttonNext = view.findViewById(R.id.button_next);
        variants = view.findViewById((R.id.variants));
        tvQuestion = view.findViewById(R.id.question);

        buttonPrevious.setEnabled(false);
        buttonCheck.setEnabled(false);
        buttonNext.setEnabled(false);

        buttonPrevious.setOnClickListener(this::btnPrevious);
        buttonCheck.setOnClickListener(this::btnCheck);
        buttonHint.setOnClickListener(this::btnHint);
        buttonNext.setOnClickListener(this::btnNext);
        variants.setOnCheckedChangeListener(this::rBtnChange);

        this.fillForm();

        return view;
    }

    private void btnPrevious(View view) {
        store.decPosition();
        fillForm();
    }

    private void btnCheck(View view) {
        showAnswer();
    }

    private void btnHint(View view) {
        Intent intent = new Intent(getActivity(), HintActivity.class);
        intent.putExtra(HINT_FOR, store.getPosition());
        startActivity(intent);
    }

    private void btnNext(View view) {
        store.incPosition();

        // если вопрос последний то кн. ДАЛЕЕ ведёт к подведению итогов
        if (store.getPosition() == size) {
            Intent intent = new Intent(getActivity(), ResultActivity.class);
            StringBuilder sb = new StringBuilder();
            int counter = 0;

            for (int i = 0; i < size; i++) {
                Question question = store.get(i);
                int userAnswer = question.getUserAnswer();
                int rightAnswer = question.getRightAnswer();
                sb
                        .append("Вопрос ").append(i + 1)
                        .append(": ваш ответ - ").append(userAnswer)
                        .append(", верный ответ - ").append(rightAnswer)
                        .append(System.lineSeparator());
                if (userAnswer == rightAnswer) {
                    counter++;
                }
            }
            sb
                    .append(System.lineSeparator()).append("Итого верно: ").append(counter)
                    .append(" из ").append(size).append(" вопросов");
            intent.putExtra(RESULT_FOR, sb.toString());
            startActivity(intent);
        } else {
            fillForm();
        }
    }

    private void rBtnChange(RadioGroup group, int checkedId) {

        int position = store.getPosition();

        // сохранение ответа пользователя
        store.get(position).setUserAnswer(checkedId);

        // обновление доступности кнопок
        buttonPrevious.setEnabled(position != 0 && store.get(position - 1).getUserAnswer() != -1);
        buttonCheck.setEnabled(store.get(position).getUserAnswer() != -1);
        buttonNext.setEnabled(store.get(position).getUserAnswer() != -1);
    }

    private void fillForm() {
        int position = store.getPosition();
        int oldPosition = store.getOldPosition();

        // получаем объект вопроса по текущему номеру вопроса
        Question question = this.store.get(position);
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
        if (answer != -1 && answer == store.get(oldPosition).getUserAnswer()) {
            buttonPrevious.setEnabled(position != 0);
            // теперь ДАЛЕЕ вадёт к итогам, а не блочится
            // buttonNext.setEnabled(position != size - 1);
        }
    }

    private void showAnswer() {
        int id = variants.getCheckedRadioButtonId();
        int position = store.getPosition();
        Question question = this.store.get(position);
        Toast.makeText(
                getActivity(),
                "Your answer is " + id + ", correct is " + question.getRightAnswer(),
                Toast.LENGTH_SHORT
        ).show();
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.d(MY_LOG, "QuestionFragment - onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(MY_LOG, "QuestionFragment - onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(MY_LOG, "QuestionFragment - onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(MY_LOG, "QuestionFragment - onDetach");
    }
}
