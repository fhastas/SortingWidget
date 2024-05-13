package com.softgenie.sortingwidget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Comparator;


public class Show extends AppCompatActivity {
    private final String TAG = Show.class.getSimpleName();
    Button back5, done;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show);

        back5 = findViewById(R.id.back5);
        done = findViewById(R.id.done);

        GridView gridView = findViewById(R.id.showgird);
        gridViewAdapter adapter = new gridViewAdapter();

        AppList appList = SharedPreferencesHelper.loadAppList(getApplicationContext());
        if (appList == null) {
            appList = new AppList(getApplicationContext());
            appList.getAppList().sort(Comparator.comparing(AppData::getAppName));
            SharedPreferencesHelper.saveAppList(getApplicationContext(), appList);
        }
        adapter.appList = appList;
        gridView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        back5.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Exclude.class);
            finish();
            startActivity(intent);
        });

        done.setOnClickListener(v -> finish());
    }

    class gridViewAdapter extends BaseAdapter {
        AppList appList;

        @Override
        public int getCount() {
            return appList.getAppList().size();
        }

        @Override
        public Object getItem(int position) {
            return appList.getAppList().get(position);
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            final Context context = viewGroup.getContext();
            final AppData appData = this.appList.getAppList().get(position);

            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.show_list_item, viewGroup, false);

                TextView tv_name = convertView.findViewById(R.id.tv_name);
                ImageView iv_icon = convertView.findViewById(R.id.iv_icon);

                tv_name.setText(appData.getAppName());
                if(appData.getAppIcon() == null) {
                    iv_icon.setImageBitmap(appData.getAppIcon());
                } else{
                    iv_icon.setImageResource(R.drawable.ic_launcher_foreground);
                }
                Log.d(TAG, "getView() - [ "+position+" ]" + appData.getAppName());
            }else{
                View view = new View(context);
                view = convertView;
            }

            convertView.setOnClickListener(v -> {
                Intent intent = appData.getShortcut();
                Show.this.startActivity(intent);
            });

            return convertView;
        }
    }
}
