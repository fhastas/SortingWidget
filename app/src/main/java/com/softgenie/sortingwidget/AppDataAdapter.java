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

    private Context mContext;
    private List<AppData> mAppDataList;

    public AppDataAdapter(@NonNull Context context, @NonNull List<AppData> appDataList) {
        super(context, 0, appDataList);
        mContext = context;
        mAppDataList = appDataList;
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
        iconImageView.setImageDrawable(currentAppData.getAppIcon());

        // 앱 이름 설정
        nameTextView.setText(currentAppData.getAppName());

        // 체크박스 상태 설정
        checkBox.setChecked(currentAppData.isSelected());
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                currentAppData.setSelected(checkBox.isChecked()); // 체크박스 상태 업데이트
            }
        });

        return listItemView;
    }

}
