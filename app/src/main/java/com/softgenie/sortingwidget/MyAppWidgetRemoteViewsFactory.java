package com.softgenie.sortingwidget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

public class MyAppWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private List<String> mDataList;

    public MyAppWidgetRemoteViewsFactory(Context context) {
        mContext = context;
        mDataList = new ArrayList<>();
    }

    @Override
    public void onCreate() {
        // 초기화 작업 수행
        // 예: 데이터 로드
        mDataList.add("앱 1");
        mDataList.add("앱 2");
        // 추가적인 초기화 작업 수행 가능
    }

    @Override
    public void onDataSetChanged() {
        // 데이터가 변경될 때 호출됨
        // 여기서 데이터를 업데이트하거나 새로고침할 수 있음
        // 예: 새로운 데이터를 로드하거나 데이터베이스에서 데이터를 가져오기
    }

    @Override
    public void onDestroy() {
        // 정리 작업 수행
        // 예: 데이터 리소스 해제
        mDataList.clear();
    }

    @Override
    public int getCount() {
        // 아이템 개수 반환
        return mDataList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        // position 위치의 아이템 RemoteViews 반환
        // 이 메서드에서 각 아이템에 대한 RemoteViews를 설정하고 반환
        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.app_widget_item);
        views.setTextViewText(R.id.app_item_layout, mDataList.get(position));
        // 필요에 따라 다른 데이터나 이미지를 설정할 수 있음
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        // 로딩 중에 표시할 뷰를 반환
        // 예: ProgressBar나 로딩 텍스트 등을 포함한 레이아웃을 반환
        return null; // 로딩 중일 때 표시할 뷰를 반환하도록 수정해야 함
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    // 나머지 메서드들도 구현해야 하지만 예제에 포함하지 않음
}
