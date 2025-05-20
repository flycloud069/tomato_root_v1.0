//package com.root.websocket.util;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//
//
///**
// * @author ：fuyunxiang
// * @date ：Created in 2023年3月26日,0026 21:58
// */
//
//public class Student {
//    public String xh;
//    public String name;
//    public String fraction;
//
//    //覆写toString（）
//    public String toString() {
//        return xh + " " + name + " " + fraction;
//    }
//
//    //初始化构造方法
//    public Student(String xh, String name, String fraction) {
//        this.xh = xh;
//        this.name = name;
//        this.fraction = fraction;
//    }
//}
//
//class test {
//
//
//    public static List CreatList(int n) {
//        List<Student> list = new ArrayList();
//        Student student1 = new Student("20170866601", "张山", "99");
//        Student student2 = new Student("20170866639", "李四", "98 ");
//        Student student3 = new Student("20170866620", "正五", "97 ");
//        Student student4 = new Student("20170866619", "陈二", "99 ");
//        Student student5 = new Student("20170866607", "吴用", "98 ");
//        list.add(student1);
//        list.add(student2);
//        list.add(student3);
//        list.add(student4);
//        list.add(student5);
//        return list;
//    }
//
//    public static void PrintList(List<Student> list) {
//        for (Student student : list) {
//            System.out.println(student.toString());
//        }
//        System.out.println("按学号排序后的：");
//        // 将List按序号排序
//        list.sort(new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//                return o1.xh.compareTo(o2.xh);
//            }
//        });
//        for (Student student : list) {
//            System.out.println(student.toString());
//        }
//    }
//
//    public static void main(String[] args) {
//        //创建list
//        List<Student> list = CreatList(5);
//        //打印list
//        PrintList(list);
//    }
//
//}
//
