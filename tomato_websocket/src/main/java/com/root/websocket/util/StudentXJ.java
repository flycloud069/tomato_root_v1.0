package com.root.websocket.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author ：fuyunxiang
 * @date ：Created in 2023年3月27日,0027 21:36
 */
  class StudentXJ implements Comparable<StudentXJ>{
   String id;
   String name;
   int score;
 StudentXJ(String id,String name,int score) {

        this.id=id;
        this.name=name;
        this.score=score;
    }
    public String toString() {
        return this.id+" "+this.name+" "+this.score;
    }
    @Override
    public int compareTo(StudentXJ o) {
        // TODO Auto-generated method stub
        return this.id.compareTo(o.id);
    }
}


   class StudentMain {

        public static void main(String[] args) {
            // TODO Auto-generated method stub
            Scanner cin=new Scanner(System.in);
            while(cin.hasNext()) {
                int n=cin.nextInt();
                List<StudentXJ> list = new ArrayList<StudentXJ>();
                for(int i=0;i<n;i++) {
                    list.add(new StudentXJ(cin.next(),cin.next(),cin.nextInt()));
                }
                System.out.println("集合中的内容是：");
                for(int i=0;i<list.size();i++) {
                    System.out.println(list.get(i));
                }
                list.sort(null);
                System.out.println("按学号排序后是：");
                for(int i=0;i<list.size();i++) {
                    System.out.println(list.get(i));
                }
            }
        }
    }

