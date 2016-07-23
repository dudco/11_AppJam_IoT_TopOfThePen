package com.example.youngchae.topofthe_pen.fragment1;

/**
 * Created by youngchae on 2016-07-23.
 */
public class RecyclerItem {
    String title;
    String bookname;
    int time;
    int goal;
    int ing;

    public RecyclerItem(String title, String  bookname, int time, int goal, int ing) {
        this.title = title;
        this.bookname = bookname;
        this.time = time;
        this.goal = goal;
        this.ing = ing;
    }

    public String getTitle(){
        return this.title;
    }
    public String getBookname(){
        return this.bookname;
    }
    public int getTime(){
        return this.time;
    }
    public int getGoal(){
        return this.goal;
    }
    public int getIng(){
        return this.ing;
    }

    public void setIng(int ing){
        this.ing = ing;
    }
    public void setTime(int time){
        this.time = time;
    }
}
