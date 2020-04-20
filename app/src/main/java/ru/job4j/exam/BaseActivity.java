package ru.job4j.exam;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {

    public static final String MY_LOG = "myLog";

    @Override
    protected void onCreate(@Nullable Bundle saved) {
        super.onCreate(saved);
        Log.d(MY_LOG, "BaseActivity - onCreate");

        setContentView(R.layout.host_frg);

        FragmentManager fm = getSupportFragmentManager();

        if (fm.findFragmentById(R.id.content) == null) {
            Log.d(MY_LOG, "BaseActivity - current fragment == null");
            fm
                    .beginTransaction()
                    //  куда вставляем / что за фрагмент вставляем
                    .add(R.id.content, loadFrg())
                    .commit();
        } else {
            Log.d(MY_LOG, "BaseActivity - current fragment != null");

            StringBuilder sb = new StringBuilder();
            List<Fragment> fragments = fm.getFragments();

            String lineSeparator = System.lineSeparator();
            for (int i = 0; i < fragments.size(); i++) {
                Fragment fr = fragments.get(i);
                sb
                        .append("     fragment № ").append(i)
                        .append(", id = ").append(fr.getId())
                        .append(" , host = ").append(fr.getHost())
                        .append(lineSeparator);
            }
            Log.d(MY_LOG, sb.toString());
        }
    }

    public abstract Fragment loadFrg();
}