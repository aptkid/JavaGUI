package com.gui.view;

import com.gui.model.ConnectionMariaDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener{

    //面板
    private JPanel userPanel;
    private JPanel passPanel;
    private JPanel buttonPanel;
    //按钮
    private JButton loginButton;
    private JButton signButton;
    private JButton jButton3;
    //文本输入框
    private JTextField usernameField;
    //密码输入框
    private JPasswordField passwordField;
    //列表
    private JLabel userLabel;
    private JLabel passLabel;

    //数据库返回的结果集
    private ResultSet resultSet;

    public Login(){
        //创建各个面板
        userPanel = new JPanel();
        passPanel = new JPanel();
        buttonPanel = new JPanel();

        //创建登录按钮
        loginButton = new JButton("登录");
        signButton = new JButton("注册");

        //创建列表
        userLabel = new JLabel("用户名：");
        passLabel = new JLabel("密  码： ");

        //创建信息输入框
        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);

        //为按钮添加监听事件
        loginButton.addActionListener(this);
        signButton.addActionListener(this);

        //将需要展示的内容添加到对应的面板中
        userPanel.add(userLabel);
        userPanel.add(usernameField);
        passPanel.add(passLabel);
        passPanel.add(passwordField);
        buttonPanel.add(loginButton);
        buttonPanel.add(signButton);


        //将登录面板加入到Frame中
        this.add(userPanel);
        this.add(passPanel);
        this.add(buttonPanel);
        //设置面板标题
        this.setTitle("欢迎使用");
        //设置面板大小
        this.setSize(400,200);
        //设置窗体初始位置
//        this.setLocation(1000,800);
        //居中显示窗体
        this.setLocationRelativeTo(null);
        //设置布局管理器
        this.setLayout(new GridLayout(3,1));
        //设置面板可见
        this.setVisible(true);
        //当面板被关闭时关掉JVM
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //按钮监听回调方法
    @Override
    public void actionPerformed(ActionEvent e) {
        String username;
        String password;

        ConnectionMariaDB connDB = new ConnectionMariaDB();
        if (e.getActionCommand() == "登录") {
            username = usernameField.getText();
            password = String.valueOf(passwordField.getPassword());
            if ((username.equals("") || username == null) && (password.equals("") || password == null)){
                return;
            }
            resultSet = connDB.queryUser(usernameField.getText());
            try {
                if (resultSet.next()){
                    if (resultSet.getString("username").equals(username) && resultSet.getString("password").equals(password)){
                        System.out.println("登录成功");
                        //登录成功，关闭当前页面
                        dispose();
                        //显示新页面，根据数据库中的职工人数，生成指定长度的表格
                        connDB.getCount();
                        new ManageSystemUI(10);
                    }

                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }


        }

        if (e.getActionCommand() == "注册") {
            username = usernameField.getText();
            password = String.valueOf(passwordField.getPassword());
            if ((password.equals("") || password == null) && (username.equals("") || username == null)) {
                return;
            }
            connDB.insertUser(usernameField.getText(), password);
            JOptionPane.showMessageDialog(null, "注册成功","提示", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
