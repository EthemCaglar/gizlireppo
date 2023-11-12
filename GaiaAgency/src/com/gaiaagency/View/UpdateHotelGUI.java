package com.gaiaagency.View;

import javax.swing.*;
import com.gaiaagency.Model.*;
import com.gaiaagency.Helper.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateHotelGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_name;
    private JTextField fld_city;
    private JTextField fld_province;
    private JTextField fld_features;
    private JTextField fld_adress;
    private JComboBox cmb_star;
    private JTextField fld_phone;
    private JCheckBox cbx_1;
    private JCheckBox cbx_2;
    private JCheckBox cbx_3;
    private JCheckBox cbx_4;
    private JCheckBox cbx_5;
    private JCheckBox cbx_6;
    private JPanel wrapper_bot;
    private JPanel wrapper_top;
    private JPanel wrapper_mid;
    private JButton btn_hotel_update;
    private Hotels hotel;

    public UpdateHotelGUI(Hotels hotel) {
        this.hotel = hotel;

        add(wrapper);
        setSize(400, 400);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        fld_name.setText(hotel.getName());
        fld_city.setText(hotel.getCity());
        fld_province.setText(hotel.getProvince());
        fld_features.setText(hotel.getFeatures());
        fld_adress.setText(hotel.getAdress());
        fld_phone.setText(Long.toString(hotel.getPhone()));

        int star = hotel.getStars().length();
        switch (star){
            case 1:
                cmb_star.setSelectedIndex(1);
                break;
            case 2:
                cmb_star.setSelectedIndex(2);
                break;
            case 3:
                cmb_star.setSelectedIndex(3);
                break;
            case 4:
                cmb_star.setSelectedIndex(4);
                break;
            case 5:
                cmb_star.setSelectedIndex(5);
                break;
        }

        char[] boardsChar = hotel.getBoarding().toCharArray();
        for(Character c : boardsChar){
            switch (c){
                case '1':
                    cbx_1.setSelected(true);
                    break;
                case '2':
                    cbx_2.setSelected(true);
                    break;
                case '3':
                    cbx_3.setSelected(true);
                    break;
                case '4':
                    cbx_4.setSelected(true);
                    break;
                case '5':
                    cbx_5.setSelected(true);
                    break;
                case '6':
                    cbx_6.setSelected(true);
                    break;
            }
        }

        btn_hotel_update.addActionListener(e -> {
            if(isAllFilled()){
                int id = hotel.getId();
                String name = fld_name.getText();
                String city = fld_city.getText();
                String province = fld_province.getText();
                String features = fld_features.getText();
                String adress = fld_adress.getText();
                String boarding = giveCheckBox();
                Long phone = Long.parseLong(fld_phone.getText());
                String stars = cmb_star.getSelectedItem().toString();
                if (Hotels.update(id, name, city, province, features, adress, boarding, phone, stars)){
                    Helper.showMsg("success");
                    dispose();
                }
            }else{
                Helper.showMsg("fill");
            }
        });
    }

    public boolean isAllFilled(){
        if(Helper.isFieldEmpty(fld_name) || Helper.isFieldEmpty(fld_city) || Helper.isFieldEmpty(fld_province)
                || !(cbx_1.isSelected() || cbx_2.isSelected() || cbx_3.isSelected()  || cbx_4.isSelected() || cbx_5.isSelected() || cbx_6.isSelected())
                || Helper.isFieldEmpty(fld_features) && Helper.isFieldEmpty(fld_phone) || cmb_star.getSelectedIndex() == 0){
            return false;
        }else{
            return true;
        }
    }

    public String giveCheckBox(){
        String Check="";
        if(cbx_1.isSelected()){
            Check += "1";
        }
        if(cbx_2.isSelected()){
            Check += "2";
        }
        if(cbx_3.isSelected()){
            Check += "3";
        }
        if(cbx_4.isSelected()){
            Check += "4";
        }
        if(cbx_5.isSelected()){
            Check += "5";
        }
        if(cbx_6.isSelected()){
            Check += "6";
        }
        return Check;
    }

    public static void main(String[] args){
        Hotels h = Hotels.getFetch(1);
        UpdateHotelGUI up = new UpdateHotelGUI(h);
    }
}
