package com.softgenie.sortingwidget;

import androidx.annotation.NonNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//사이즈, 우선순위, 포함하는 앱 목록 관리
public class UserInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    final int initSize = 64;
    private int size;  // 64, 44, 24, 22
    private int[][] prioritize;

    public UserInfo(int size, int[][] prioritize) {
        this.size = size;
        this.prioritize = prioritize;
    }
    public UserInfo() {
        this.size = initSize;
        this.prioritize = new int[6][4];
        initPrioritize();
    }


    @NonNull
    @Override
    public String toString() {
        return "UserInfo{" +
                "\nsize=" + size +
                "\n, prioritize=" + Arrays.deepToString(prioritize) +
                '}';
    }
    public void addInclude(String appName) {
        this.include.add(appName);
    }
    private void initPrioritize(){
        this.prioritize[0] = new int[]{4, 4, 4, 4};
        this.prioritize[1] = new int[]{4, 4, 4, 4};
        this.prioritize[2] = new int[]{3, 3, 3, 3};
        this.prioritize[3] = new int[]{3, 2, 2, 2};
        this.prioritize[4] = new int[]{3, 2, 1, 1};
        this.prioritize[5] = new int[]{3, 2, 1, 1};
    }

    public int getSize() {
        return this.size;
    }

    public int getPriority(int x, int y) {
        return prioritize[x][y];
    }

}
