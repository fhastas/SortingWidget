package com.softgenie.sortingwidget;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    final int initSize = 46;
    private int size;  // 46, 44, 42, 22
    private int[][] prioritize;

    private List<String> included = new ArrayList<>();
    private List<String> excluded = new ArrayList<>();

    public UserInfo(int size, int[][] prioritize, List<String> included, List<String> excluded) {
        this.size = size;
        this.prioritize = prioritize;
        this.included = included;
        this.excluded = excluded;
    }
    public UserInfo(int size) {
        this.size = size;
        this.prioritize = new int[6][4];
        initPrioritize();
    }
    public UserInfo() {
        this.size = initSize;
        this.prioritize = new int[6][4];
        initPrioritize();
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
    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getIncluded() {
        return this.included;
    }
    public List<String> getExcluded() {
        return this.excluded;
    }

    public void setIncluded(List<String> included) {
        this.included = included;
    }
    public void setExcluded(List<String> excluded) {
        this.excluded = excluded;
    }

    public int getPriority(int x, int y) {
        return prioritize[x][y];
    }

    public void setPriority2List(int[][] priority) {
        this.prioritize = priority;
    }
    public void setPriority1List(int x, int[] priority) {
        this.prioritize[x] = priority;
    }
    public void setPriority(int x, int y, int priority) {
        this.prioritize[x][y] = priority;
    }


}
