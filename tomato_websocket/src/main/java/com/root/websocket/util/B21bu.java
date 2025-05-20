package com.root.websocket.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class B21bu {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner cin = new Scanner(System.in);
        String resultString = new String();
        while (cin.hasNext()) {
            int n = cin.nextInt();
            Student stu[] = new Student[n];
            for (int i = 0; i < stu.length; i++) {
                stu[i] = new Student(cin.next(), cin.next(), cin.next(), cin.nextInt(), cin.next());
            }
            String m = cin.next();
            /**用一个学生信息的列表，存放查询的学生信息，*/
            List<Student> resultStu = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String a = stu[i].site;
                /**如果地区匹配，添加到结果列表种*/
                if (a.equals(m)) {
                    resultStu.add(stu[i]);
                }
            }
            /**对列表结果进行字符串拼接*/
            resultString += "来自" + m + "的学生人数为" + resultStu.size() + ":\n";
            for (Student student : resultStu)
                resultString += student.toString();
        }
        /**最后结果打印*/
        System.out.println(resultString);
    }

}

class Student {
    String num;
    String name;
    String sex;
    int point;
    String site;

    public Student(String num, String name, String sex, int point, String site) {
        super();
        this.num = num;
        this.name = name;
        this.sex = sex;
        this.point = point;
        this.site = site;
    }

    @Override
    public String toString() {
        /**加一个换行符号 "\n" */
        return num + " " + name + " " + sex + " " + point + " " + site + "\n";
    }

}
