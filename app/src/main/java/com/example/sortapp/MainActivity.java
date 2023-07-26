package com.example.sortapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> dataList;
    private DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataList = generateRandomData(1000);
        adapter = new DataAdapter(dataList);
        recyclerView.setAdapter(adapter);

        Button randomButton = findViewById(R.id.randomButton);
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataList = generateRandomData(1000);
                adapter.setData(dataList);
            }
        });

        Button sortButton1 = findViewById(R.id.sortButton1);
        sortButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortDataMethod1();
                adapter.setData(dataList);
            }
        });
        Button sortButton2 = findViewById(R.id.sortButton2);
        sortButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortDataMethod2();
                adapter.setData(dataList);
            }
        });

        Button sortButton3 = findViewById(R.id.sortButton3);
        sortButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortDataMethod3();
                adapter.setData(dataList);
            }
        });

        Button sortButton4 = findViewById(R.id.sortButton4);
        sortButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortDataMethod4();
                adapter.setData(dataList);
            }
        });
        Button sortButton5 = findViewById(R.id.sortButton5);
        sortButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortDataMethod5();
                adapter.setData(dataList);
            }
        });


        // Add similar onClickListeners for sortButton2, sortButton3, sortButton4, and sortButton5
    }

    private List<String> generateRandomData(int count) {
        Random random = new Random();
        List<String> data = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int length = random.nextInt(5) + 1;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < length; j++) {
                char c = (char) (random.nextInt(26) + 'a');
                if (random.nextBoolean()) {
                    c = Character.toUpperCase(c);
                }
                sb.append(c);
            }
            data.add(sb.toString());
        }
        return data;
    }

    private void sortDataMethod1() {
        Collections.sort(dataList);
    }

    private void sortDataMethod2() {
        Collections.sort(dataList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });
    }
    private void sortDataMethod3() {
        Collections.sort(dataList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s2.length(), s1.length());
            }
        });
    }
    private void sortDataMethod4() {
        Collections.sort(dataList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.compareTo(s1);
            }
        });
    }
    private void sortDataMethod5() {
        Collections.shuffle(dataList);
    }


    // Implement other sorting methods (sortDataMethod2, sortDataMethod3, ...)
}
