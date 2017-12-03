package com.gui.view;

import com.gui.model.ConnectionMariaDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ManageSystemUI extends JFrame{

    //表格字段（列名）

    //职工信息表格
    private JTable jTable = null;
    //组件
    DefaultTableModel model;
    JScrollPane jScrollPane;
    //设置表格内容

    //面板
    private JPanel mainPanel = null;
    public ManageSystemUI(int count) {
        String[] colnames = {"工号", "姓名", "性别", "出生日期", "手机号", "联系地址"};
        model = new DefaultTableModel(colnames, count);
        jTable = new JTable(model);
        jTable.setPreferredScrollableViewportSize(new Dimension(1000,800));
        jTable.setRowHeight(25);
        jScrollPane = new JScrollPane(jTable);
        jScrollPane.setSize(900,1300);
        mainPanel = new JPanel();
        mainPanel.add(jScrollPane);
        setData();
        //添加入Frame
        this.add(mainPanel);
        this.setTitle("职工管理系统");
        this.setSize(1050,1300);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


    private void setData() {
        ConnectionMariaDB connDB = new ConnectionMariaDB();
        ResultSet resultSet = connDB.getAll();
        try {
            int count = 0;
            while (resultSet.next()) {
                int flag = 0;
                jTable.setValueAt(resultSet.getString("id"), count, flag);
                flag++;
                jTable.setValueAt(resultSet.getString("name"), count, flag);
                flag++;
                jTable.setValueAt(resultSet.getString("sex"), count, flag);
                flag++;
                jTable.setValueAt(resultSet.getString("date_of_birth"), count, flag);
                flag++;
                jTable.setValueAt(resultSet.getString("phone"), count, flag);
                flag++;
                jTable.setValueAt(resultSet.getString("address"),count, flag);
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
} 
