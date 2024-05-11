package com.softgenie.sortingwidget;

public class UserInfo {

    final int initSize = 24;
    private int size;  // 46, 44, 42, 22
    private int[][] prioritize;

    public UserInfo(int size, int[][] prioritize) {
        this.size = size;
        this.prioritize = prioritize;
    }
    public UserInfo(int size) {
        this.size = size;
        initPrioritize();
    }
    public UserInfo() {
        this.size = initSize;
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

    int getPriority(int x, int y) {
        return prioritize[x][y];
    }

    int getSize() {
        return size;
    }

    void setPriorityList(int x, int[] priority) {
        this.prioritize[x] = priority;
    }
    void setPriority(int x, int y, int priority) {
        this.prioritize[x][y] = priority;
    }

    void setSize(int size) {
        this.size = size;
    }
}
