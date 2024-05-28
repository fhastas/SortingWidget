package com.softgenie.sortingwidget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
// 앱 이름, 아이콘, 체크박스
public class AppDataAdapter extends ArrayAdapter<AppData> {
    private Context context;
    private List<AppData> appDataList;

    public AppDataAdapter(@NonNull Context context, @NonNull List<AppData> objects) {
        super(context, R.layout.appitem, objects);
        this.context = context;
        this.appDataList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.appitem, parent, false);
        }

        TextView appNameTextView = convertView.findViewById(R.id.apptextView);
        ImageView appIconImageView = convertView.findViewById(R.id.appimageView);
        CheckBox checkBox = convertView.findViewById(R.id.checkBox);

        AppData appData = appDataList.get(position);
        appNameTextView.setText(appData.getAppName());
        appIconImageView.setImageDrawable(appData.getAppIcon());
        checkBox.setChecked(appData.getSelected());

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            appData.setSelected(isChecked);
            if (isChecked) {
                // 선택된 경우 UserInfo에 앱 이름 추가
                ((Include) context).userInfo.addInclude(appData.getAppName());
            } else {
                // 선택 해제된 경우 UserInfo에서 앱 이름 제거
                // UserInfo 클래스에 선택 해제 메소드 추가 필요
            }
        });
        return convertView;
    }
}
