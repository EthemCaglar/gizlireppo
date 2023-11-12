package com.gaiaagency.View;

import com.gaiaagency.Helper.Config;
import com.gaiaagency.Helper.Helper;
import com.gaiaagency.Model.User;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminGUI extends JFrame{
    private JPanel wrapper;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTable tbl_userlist;
    private JPanel pnl_admin_top;
    private JPanel pnl_admin_bottom;
    private JScrollPane scrl_userlist;
    private JPanel pnl_admin_useradd;
    private JTextField fld_admin_name;
    private JTextField fld_admin_usern;
    private JComboBox cmb_admin_type;
    private JButton btn_admin_useradd;
    private JTextField fld_admin_userpass;
    private final User admin;
    private JPopupMenu userPopMenu;
    private DefaultTableModel mdl_userlist;
    private Object[] row_userlist;

    public AdminGUI(User admin){
        this.admin = admin;

        Helper.setLayout();
        add(wrapper);
        setSize(800,600);
        int x = Helper.screenCenter("x",getSize());
        int y = Helper.screenCenter("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_welcome.setText("Welcome " + admin.getName());
//---------------------------------------------------------------------------
        mdl_userlist = new DefaultTableModel(){ // id is uneditable
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        userPopMenu = new JPopupMenu();
        JMenuItem deleteMenu = new JMenuItem("Delete");
        userPopMenu.add(deleteMenu);

        deleteMenu.addActionListener(e -> {
            if(Helper.confirm("areyousure")){
                int select_id = Integer.parseInt(tbl_userlist.getValueAt(tbl_userlist.getSelectedRow(),0).toString());
                if(User.delete(select_id)){
                    Helper.showMsg("success");
                    loadUserTable();
                }else{
                    Helper.showMsg("error");
                }
            }
        });

        Object[] col_userList = {"ID" , "Name" , "UserName" , "Password", "Acc Type"};
        mdl_userlist.setColumnIdentifiers(col_userList);
        row_userlist = new Object[col_userList.length];
        loadUserTable();
        tbl_userlist.setModel(mdl_userlist);
        tbl_userlist.setComponentPopupMenu(userPopMenu);
        tbl_userlist.getColumnModel().getColumn(0).setMaxWidth(35);
        tbl_userlist.getTableHeader().setReorderingAllowed(false);

        tbl_userlist.getModel().addTableModelListener(e -> {
            if(e.getType() == TableModelEvent.UPDATE){
                int user_id = Integer.parseInt(tbl_userlist.getValueAt(tbl_userlist.getSelectedRow(), 0).toString());
                String user_name = tbl_userlist.getValueAt(tbl_userlist.getSelectedRow(), 1).toString();
                String user_usern = tbl_userlist.getValueAt(tbl_userlist.getSelectedRow(), 2).toString();
                String user_pass = tbl_userlist.getValueAt(tbl_userlist.getSelectedRow(), 3).toString();
                String user_type = tbl_userlist.getValueAt(tbl_userlist.getSelectedRow(), 4).toString();

                if(User.update(user_id,user_name,user_usern,user_pass,user_type)){
                    Helper.showMsg("success");
                }
                loadUserTable();

            }
        });

        tbl_userlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_userlist.rowAtPoint(point);
                tbl_userlist.setRowSelectionInterval(selected_row, selected_row);
            }
        });

        btn_admin_useradd.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_admin_name) || Helper.isFieldEmpty(fld_admin_usern) || Helper.isFieldEmpty(fld_admin_userpass)){
                Helper.showMsg("fill");
            }else {
                String name = fld_admin_name.getText();
                String usern = fld_admin_usern.getText();
                String pass = fld_admin_userpass.getText();
                String type = cmb_admin_type.getSelectedItem().toString();
                if (User.addUser(name, usern, pass, type)){
                    Helper.showMsg("success");
                    loadUserTable();
                    fld_admin_name.setText(null);
                    fld_admin_usern.setText(null);
                    fld_admin_userpass.setText(null);
                }
            }
        });
        btn_logout.addActionListener(e -> {
            dispose();
            LoginGUI login = new LoginGUI();
        });
    }

    public void loadUserTable(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_userlist.getModel();
        clearModel.setRowCount(0);

        for(User obj : User.getList()){
            row_userlist[0] = obj.getId();
            row_userlist[1] = obj.getName();
            row_userlist[2] = obj.getUsern();
            row_userlist[3] = obj.getPass();
            row_userlist[4] = obj.getType();
            mdl_userlist.addRow(row_userlist);
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->{
            User op = new User(1, "Admin", "Admin1", "password", "opeator");
            AdminGUI adGUI = new AdminGUI(op);
        });
    }
}

