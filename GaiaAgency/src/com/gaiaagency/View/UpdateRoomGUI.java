package com.gaiaagency.View;

import javax.swing.*;

import com.gaiaagency.Helper.*;
import com.gaiaagency.Model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateRoomGUI extends JFrame{
    private JPanel wrapper;
    private JComboBox cmb_roomtype;
    private JComboBox cmb_board;
    private JComboBox cmb_season;
    private JTextField fld_available;
    private JTextField fld_adult;
    private JTextField fld_child;
    private JTextField fld_hotel;
    private JButton btn_room_update;
    private Rooms room;
    private Hotels hotel;
    private Boarding boarding;
    private RoomType roomType;

    public UpdateRoomGUI(Rooms room){
        this.room = room;

        add(wrapper);
        setSize(500, 400);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        loadSeasonCombo();
        loadBoardCombo();
        loadRoomTypeCombo();

        Hotels htl = Hotels.getFetch(room.getHotel_id());

        fld_hotel.setText(htl.getName());
        fld_adult.setText(Integer.toString(room.getAdult_price()));
        fld_child.setText(Integer.toString(room.getChild_price()));
        fld_available.setText(Integer.toString(room.getAvailable()));
        cmb_roomtype.setSelectedIndex(room.getRoom_typeID()-1);
        cmb_season.setSelectedIndex(room.getSeason_id()-1);

        btn_room_update.addActionListener(e -> {
            if(isAllFilled()){
                int id = room.getId();
                int hotel_id = room.getHotel_id();
                int season_id = Seasons.getFetch(cmb_season.getSelectedItem().toString()).getId();
                int boarding_id = Boarding.getFetch(cmb_board.getSelectedItem().toString()).getId();
                int room_typeID = RoomType.getFetch(cmb_roomtype.getSelectedIndex()+1).getId();
                int available = Integer.parseInt(fld_available.getText());
                int adult_price = Integer.parseInt(fld_adult.getText());
                int child_price = Integer.parseInt(fld_child.getText());
                if (Rooms.update(id, hotel_id, season_id, boarding_id, room_typeID, available, adult_price, child_price)){
                    Helper.showMsg("success");
                    dispose();
                }
            }else{
                Helper.showMsg("fill");
            }
        });
    }

    public void loadSeasonCombo(){
        cmb_season.removeAllItems();
        for(Seasons obj : Seasons.getList()){
            cmb_season.addItem(new Item(obj.getId(), obj.getName()));
        }
    }

    public void loadBoardCombo(){
        cmb_board.removeAllItems();
        Hotels obje = Hotels.getFetch(room.getHotel_id());
        int counter = 1;
        for(String b : Boarding.getBoardingforCMB(obje.getBoarding())){
            cmb_board.addItem(new Item(counter, b));
            counter++;
        }
    }

    public void loadRoomTypeCombo(){
        cmb_roomtype.removeAllItems();
        for(RoomType obj : RoomType.getList()){
            cmb_roomtype.addItem(new Item(obj.getId(), obj.getName() + " - " + obj.getBed()));
        }
    }

    public boolean isAllFilled(){
        if(Helper.isFieldEmpty(fld_adult) || Helper.isFieldEmpty(fld_child) || Helper.isFieldEmpty(fld_available)){
            return false;
        }else{
            return true;
        }
    }

    public static void main(String[] args){
        Rooms h = Rooms.getFetch(1);
        UpdateRoomGUI rm = new UpdateRoomGUI(h);
    }
}
