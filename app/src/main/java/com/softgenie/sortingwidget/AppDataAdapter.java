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

public class AppDataAdapter extends ArrayAdapter<AppData> {

    private final Context mContext;
    private final List<AppData> mAppDataList;

    public AppDataAdapter(@NonNull Context context, @NonNull List<AppData> appDataList) {
        super(context, 0, appDataList);
        this.mContext = context;
        this.mAppDataList = appDataList;
    }

    private static class ViewHolder {
        ImageView iconImageView;
        TextView nameTextView;
        CheckBox checkBox;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.appitem, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.iconImageView = convertView.findViewById(R.id.appimageView);
            viewHolder.nameTextView = convertView.findViewById(R.id.apptextView);
            viewHolder.checkBox = convertView.findViewById(R.id.checkBox);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        AppData currentAppData = mAppDataList.get(position);

        // 앱 아이콘 설정
        Drawable appIcon = currentAppData.getAppIcon();
        if (appIcon != null) {
            viewHolder.iconImageView.setImageDrawable(appIcon);
        } else {
            viewHolder.iconImageView.setImageResource(R.drawable.ic_launcher_foreground);
        }

        // 앱 이름 설정
        viewHolder.nameTextView.setText(currentAppData.getAppName());

        // 체크박스 상태 설정
        viewHolder.checkBox.setChecked(currentAppData.getSelected());
        viewHolder.checkBox.setOnClickListener(v -> {
            CheckBox checkBox1 = (CheckBox) v;
            currentAppData.setSelected(checkBox1.isChecked()); // 체크박스 상태 업데이트
        });

        return convertView;
    }
}

