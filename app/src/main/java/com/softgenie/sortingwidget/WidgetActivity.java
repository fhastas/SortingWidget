package com.softgenie.sortingwidget;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WidgetActivity extends AppCompatActivity {

    TextView widgetTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_layout);

        // TextView를 레이아웃 파일과 연결
        widgetTextView = findViewById(R.id.widget_gridLayout);

        // 데이터 설정
        String data = "표시할 데이터";
        widgetTextView.setText(data);
    }
}