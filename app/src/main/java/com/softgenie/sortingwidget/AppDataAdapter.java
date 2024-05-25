package com.softgenie.sortingwidget;

import android.content.Context;
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

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(mContext).inflate(
                    R.layout.appitem, parent, false);
        }

        AppData currentAppData = mAppDataList.get(position);

        ImageView iconImageView = listItemView.findViewById(R.id.appimageView);
        TextView nameTextView = listItemView.findViewById(R.id.apptextView);
        CheckBox checkBox = listItemView.findViewById(R.id.checkBox);

        // 앱 아이콘 설정
        if(currentAppData.getAppIcon(mContext) != null) {
            iconImageView.setImageDrawable(currentAppData.getAppIcon(mContext));
        } else{
            iconImageView.setImageResource(R.drawable.ic_launcher_foreground);
        }
        // 앱 이름 설정
        nameTextView.setText(currentAppData.getAppName());

        // 체크박스 상태 설정
        checkBox.setChecked(currentAppData.getSelected());
        checkBox.setOnClickListener(v -> {
            CheckBox checkBox1 = (CheckBox) v;
            currentAppData.setSelected(checkBox1.isChecked()); // 체크박스 상태 업데이트
        });

        return listItemView;
    }

}
