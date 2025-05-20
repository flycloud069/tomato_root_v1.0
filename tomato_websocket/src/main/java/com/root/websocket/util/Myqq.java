//package com.root.websocket.util;
////项目
//import java.awt.BorderLayout;
//import java.awt.Container;
//import java.awt.Label;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.Vector;
//
//import javax.imageio.ImageIO;
//import javax.swing.BorderFactory;
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JCheckBox;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JPasswordField;
//import javax.swing.JRadioButton;
//import javax.swing.JScrollPane;
//import javax.swing.JSplitPane;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//
//class Myqq extends JFrame {
//    public Myqq() {
//        this.setTitle("20220865126-曾梦容-QQ聊天");
//        JLabel a = new JLabel("账    号："); //实例化JLabel对象 标签JLabel
//        JLabel b = new JLabel("密    码：");
//        JPasswordField d = new JPasswordField();//实例化密码框JPasswordField
//        d.setEchoChar('*');//将输入密码用*显示出来
//        JButton e = new JButton("登录");//实例化两个按钮JButton
//        JButton f = new JButton("重置");
//        JCheckBox jcb1 = new JCheckBox("自动登录");//复选框JCheckBox
//        JCheckBox jcb2 = new JCheckBox("记住密码");
//        JRadioButton jrb1 = new JRadioButton("找回密码");//单选钮JRadioButton
//        Vector<String> v = new Vector<String>();//定义一个Vector集合
//        JComboBox c = new JComboBox(v);//下拉列表框JComboBox 实例化JComboBox
//        v.add("20220865126");//添加四个账号
//        v.add("20200865218");
//        v.add("123654987");
//        v.add("77889955445");
//        v.add("2244998866");
//        c.setEditable(true);//可编辑列表框
//        c.setMaximumRowCount(3);//显示的最大行数3，多的通过下拉显示
//        this.setVisible(true);//使窗体可视化
//        Container mk = getContentPane();//获取一个容器
//        mk.add(a);//将显示内容都添加进去
//        mk.add(b);
//        mk.add(c);
//        mk.add(d);
//        mk.add(e);
//        mk.add(f);
//        mk.add(jcb1);
//        mk.add(jcb2);
//        mk.add(jrb1);
//        this.setSize(350, 300);//setSize(int width, int height)窗体宽高
//        mk.setLayout(null);//布局显示的位置坐标 绝对定位
//        a.setBounds(10, 40, 50, 18);//setBounds(int x横坐标, int y纵坐标
//    }