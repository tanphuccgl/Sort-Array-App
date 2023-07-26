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
        Button sortBubbleButton = findViewById(R.id.sortBubbleButton);
        sortBubbleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortBubble();
                adapter.setData(dataList);
            }
        });

        Button sortSelectionButton = findViewById(R.id.sortSelectionButton);
        sortSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortSelection();
                adapter.setData(dataList);
            }
        });

        Button sortInsertionButton = findViewById(R.id.sortInsertionButton);
        sortInsertionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortInsertion();
                adapter.setData(dataList);
            }
        });

        Button sortMergeButton = findViewById(R.id.sortMergeButton);
        sortMergeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortMerge();
                adapter.setData(dataList);
            }
        });

        Button sortQuickButton = findViewById(R.id.sortQuickButton);
        sortQuickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortQuick(dataList, 0, dataList.size() - 1);
                adapter.setData(dataList);
            }
        });
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

    private void sortBubble() {
        int n = dataList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (dataList.get(j).compareTo(dataList.get(j + 1)) > 0) {
                    Collections.swap(dataList, j, j + 1);
                }
            }
        }
    }

    private void sortSelection() {
        int n = dataList.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (dataList.get(j).compareTo(dataList.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            Collections.swap(dataList, i, minIndex);
        }
    }

    private void sortInsertion() {
        int n = dataList.size();
        for (int i = 1; i < n; i++) {
            String key = dataList.get(i);
            int j = i - 1;
            while (j >= 0 && dataList.get(j).compareTo(key) > 0) {
                dataList.set(j + 1, dataList.get(j));
                j--;
            }
            dataList.set(j + 1, key);
        }
    }

    private void sortMerge() {
        mergeSort(dataList, 0, dataList.size() - 1);
    }

    private void mergeSort(List<String> arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(List<String> arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        List<String> L = new ArrayList<>();
        List<String> R = new ArrayList<>();

        for (int i = 0; i < n1; i++) {
            L.add(arr.get(left + i));
        }
        for (int j = 0; j < n2; j++) {
            R.add(arr.get(mid + 1 + j));
        }

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L.get(i).compareTo(R.get(j)) <= 0) {
                arr.set(k, L.get(i));
                i++;
            } else {
                arr.set(k, R.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr.set(k, L.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            arr.set(k, R.get(j));
            j++;
            k++;
        }
    }

    private void sortQuick(List<String> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            sortQuick(arr, low, pi - 1);
            sortQuick(arr, pi + 1, high);
        }
    }

    private int partition(List<String> arr, int low, int high) {
        String pivot = arr.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr.get(j).compareTo(pivot) < 0) {
                i++;
                Collections.swap(arr, i, j);
            }
        }
        Collections.swap(arr, i + 1, high);
        return i + 1;
    }
}