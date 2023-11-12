package com.gaiaagency.View;

import javax.swing.*;
import com.gaiaagency.Helper.*;
import com.gaiaagency.Model.*;

public class LoginGUI extends JFrame{
    private JPanel wrapper;
    private JPanel wrapper_top;
    private JPanel wrapper_bottom;
    private JLabel agency_icon;
    private JTextField fld_login_user;
    private JPasswordField fld_login_password;
    private JButton btn_login;

    public LoginGUI(){
        add(wrapper);
        setSize(600,720);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        btn_login.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_login_user) || Helper.isFieldEmpty(fld_login_password)){
                Helper.showMsg("success");
            }else{
                User u = User.getFetch(fld_login_user.getText(), fld_login_password.getText());
                if(u == null){
                    Helper.showMsg("nousername");
                }else{
                    switch (u.getType()){
                        case "admin":
                            AdminGUI adGUI = new AdminGUI(u);
                            break;
                        case "agent_worker":
                            PersonelGUI edGUI = new PersonelGUI(u);
                            break;
                    }
                }
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        LoginGUI login = new LoginGUI();
    }
}

