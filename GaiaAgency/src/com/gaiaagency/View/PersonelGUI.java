package com.gaiaagency.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.gaiaagency.Model.*;
import com.gaiaagency.Helper.*;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;

public class PersonelGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tab_personel;
    private JButton btn_logout;
    private JPanel pnl_top;
    private JPanel pnl_reserv;
    private JPanel pnl_rooms;
    private JPanel pnl_hotels;
    private JPanel pnl_broad_season;
    private JTable tbl_hotel;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_city;
    private JButton btn_hotel_add;
    private JLabel lbl_title;
    private JPanel pnl_hotel_add;
    private JScrollPane scrl_hotels;
    private JScrollPane scrl_boarding;
    private JScrollPane scrl_seasons;
    private JTable tbl_seasons;
    private JTable tbl_boarding;
    private JComboBox cmb_hotel_stars;
    private JTextField fld_hotel_province;
    private JTextField fld_hotel_phone;
    private JTextField fld_hotel_features;
    private JTextField fld_hotel_adress;
    private JScrollPane scrl_rooms;
    private JTable tbl_rooms;
    private JComboBox cmb_room_hotel;
    private JComboBox cmb_room_season;
    private JComboBox cmb_room_board;
    private JComboBox cmb_room_type;
    private JTextField fld_adultprice;
    private JTextField fld_childprice;
    private JCheckBox cbx_1;
    private JCheckBox cbx_3;
    private JCheckBox cbx_2;
    private JCheckBox cbx_4;
    private JCheckBox cbx_5;
    private JCheckBox cbx_6;
    private JButton btn_room_add;
    private JTextField fld_available;
    private JTabbedPane tabbedPane1;
    private JPanel pnl_rez_search;
    private JPanel pnl_rez_roomlist;
    private JPanel pnl_rez_list;
    private JTable tbl_rez_list;
    private JTable tbl_rez_roomlist;
    private JScrollPane scrl_rez_list;
    private JPanel pnl_rezroomlist_top;
    private JScrollPane scrl_rezroomlist;
    private JTextField fld_rez_roomAdult;
    private JTextField fld_rez_roomChild;
    private JButton btn_rez_fromRoomlist;
    private JTextField fld_rez_roomName;
    private JTextField fld_rez_roomStart;
    private JTextField fld_rez_roomEnd;
    private JScrollPane scrl_roomtype;
    private JTable tbl_roomtype;
    private JComboBox cmb_search_hotel;
    private JComboBox cmb_search_city;
    private JComboBox cmb_search_board;
    private JComboBox cmb_search_roomtype;
    private JTextField fld_search_name;
    private JTextField fld_search_adult;
    private JTextField fld_search_child;
    private JTextField fld_search_start;
    private JTextField fld_search_end;
    private JPanel pnl_search_top;
    private JScrollPane scrl_search;
    private JButton btn_search;
    private JTable tbl_search;
    private JPanel pnl_anno;
    private JLabel lbl_icon;
    private final User personel;
    // ------------------------------------------------------------------
    private DefaultTableModel mdl_rezlist;
    private Object[] row_rezlist;
    private DefaultTableModel mdl_rez_roomlist;
    private Object[] row_rez_roomlist;
    //-------------------------------------------------------------------------
    private DefaultTableModel mdl_searchlist;
    private Object[] row_searchlist;
    // ------------------------------------------------------------------
    private DefaultTableModel mdl_roomlist;
    private Object[] row_roomlist;
    //-------------------------------------------------------------------------
    private DefaultTableModel mdl_hotellist;
    private Object[] row_hotellist;
    //-------------------------------------------------------------------------
    private  DefaultTableModel mdl_boardlist;
    private  Object[] row_boardlist;
    private  DefaultTableModel mdl_seasonlist;
    private  Object[] row_seasonlist;
    private  DefaultTableModel mdl_roomtypelist;
    private  Object[] row_roomtypelist;
    // ------------------------------------------------------------------
    private JPopupMenu hotelsMenu;
    private JPopupMenu roomsMenu;
    private JPopupMenu rezlistMenu;

    public PersonelGUI(User personel) {
        this.personel = personel;

        Helper.setLayout();
        add(wrapper);
        setSize(1280, 720);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        lbl_title.setText(lbl_title.getText() + " " + personel.getName() + "-san");

        //-----------------------ROOMS TAB STARTS-----------------------------------------------------------------
        mdl_roomlist = new DefaultTableModel();

        roomsMenu = new JPopupMenu();
        JMenuItem deleteRoomMenu = new JMenuItem("Delete");
        JMenuItem updateRoomMenu = new JMenuItem("Update");
        roomsMenu.add(deleteRoomMenu);
        roomsMenu.add(updateRoomMenu);


        tbl_rooms.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_rooms.rowAtPoint(point);
                tbl_rooms.setRowSelectionInterval(selected_row, selected_row);
            }
        });

        mdl_roomlist = new DefaultTableModel();
        Object[] col_roomsList = {"ID", "Hotel Name", "City", "Season", "Boarding", "Room Type", "Bed", "Available", "Room Size", "Adult Price", "Child Price"};
        mdl_roomlist.setColumnIdentifiers(col_roomsList);
        row_roomlist = new Object[col_roomsList.length];
        tbl_rooms.setComponentPopupMenu(roomsMenu);

        loadRoomsTable();

        tbl_rooms.setModel(mdl_roomlist);
        tbl_rooms.getColumnModel().getColumn(0).setMaxWidth(30);
        tbl_rooms.getTableHeader().setReorderingAllowed(false);

        cmb_room_hotel.addActionListener(e -> {
                loadRoomBoardCombo();
        });

        btn_room_add.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_adultprice) || Helper.isFieldEmpty(fld_childprice) || Helper.isFieldEmpty(fld_available)
                || cmb_room_hotel.getSelectedIndex() == -1 || cmb_room_season.getSelectedIndex() == -1 || cmb_room_board.getSelectedIndex() == -1
                || cmb_room_type.getSelectedIndex() == -1) {
                Helper.showMsg("fill");
            }else{
                int hotelID = Hotels.getFetch(cmb_room_hotel.getSelectedItem().toString()).getId();
                int seasonID = Seasons.getFetch(cmb_room_season.getSelectedItem().toString()).getId();
                int boardID = Boarding.getFetch(cmb_room_board.getSelectedItem().toString()).getId();
                int roomTypeID = RoomType.getFetch(cmb_room_type.getSelectedIndex()).getId();
                int available = Integer.parseInt(fld_available.getText());
                int adultprice = Integer.parseInt(fld_adultprice.getText());
                int childprice = Integer.parseInt(fld_childprice.getText());

                if (Rooms.addRoom(hotelID, seasonID, boardID, roomTypeID, available, adultprice, childprice)){
                    Helper.showMsg("success");
                    loadHotelTable();
                    fld_childprice.setText(null);
                    fld_adultprice.setText(null);
                    fld_available.setText(null);
                    cmb_room_board.setSelectedIndex(-1);
                    cmb_room_hotel.setSelectedIndex(-1);
                    cmb_room_type.setSelectedIndex(-1);
                    cmb_room_season.setSelectedIndex(-1);
                }
                loadRoomsTable();
                loadRezRoomsTable();
            }
        });

        updateRoomMenu.addActionListener(e -> {
            int selected_id = Integer.parseInt(tbl_rooms.getValueAt(tbl_rooms.getSelectedRow(),0).toString());
            UpdateRoomGUI upR = new UpdateRoomGUI(Rooms.getFetch(selected_id));
            upR.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomsTable();
                    loadRezRoomsTable();
                }
            });
        });

        deleteRoomMenu.addActionListener(e -> {
            if(Helper.confirm("areyousure")){
                int select_id = Integer.parseInt(tbl_rooms.getValueAt(tbl_rooms.getSelectedRow(),0).toString());
                if(Rooms.delete(select_id)){
                    Helper.showMsg("deleted");
                }else{
                    Helper.showMsg("error");
                }
                loadRoomsTable();
                loadRezRoomsTable();
            }
        });

        //-----------------------ROOMS TAB ENDS-------------------------------------------------------------------
        //-----------------------HOTELS TAB STARTS----------------------------------------------------------------
        mdl_hotellist = new DefaultTableModel();

        hotelsMenu = new JPopupMenu();
        JMenuItem deleteHotelMenu = new JMenuItem("Delete");
        JMenuItem updateHotelMenu = new JMenuItem("Update");
        hotelsMenu.add(deleteHotelMenu);
        hotelsMenu.add(updateHotelMenu);

        tbl_hotel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_hotel.rowAtPoint(point);
                tbl_hotel.setRowSelectionInterval(selected_row, selected_row);
            }
        });

        Object[] col_hotelList = {"ID", "Name", "City", "Province", "Features", "Adress", "Boarding", "Phone", "Stars"};
        mdl_hotellist.setColumnIdentifiers(col_hotelList);
        row_hotellist = new Object[col_hotelList.length];
        tbl_hotel.setComponentPopupMenu(hotelsMenu);

        loadHotelTable();

        tbl_hotel.setModel(mdl_hotellist);
        tbl_hotel.getColumnModel().getColumn(0).setMaxWidth(30);
        tbl_hotel.getColumnModel().getColumn(1).setMaxWidth(450);
        tbl_hotel.getColumnModel().getColumn(2).setMaxWidth(100);
        tbl_hotel.getColumnModel().getColumn(3).setMaxWidth(350);
        tbl_hotel.getColumnModel().getColumn(4).setMaxWidth(2000);
        tbl_hotel.getColumnModel().getColumn(5).setMaxWidth(1000);
        tbl_hotel.getColumnModel().getColumn(6).setMaxWidth(1000);
        tbl_hotel.getColumnModel().getColumn(7).setMaxWidth(250);
        tbl_hotel.getColumnModel().getColumn(8).setMaxWidth(40);
        tbl_hotel.getTableHeader().setReorderingAllowed(false);

        btn_hotel_add.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_hotel_name) || Helper.isFieldEmpty(fld_hotel_city) || Helper.isFieldEmpty(fld_hotel_province)
                    || !(cbx_1.isSelected() || cbx_2.isSelected() || cbx_3.isSelected()  || cbx_4.isSelected() || cbx_5.isSelected() || cbx_6.isSelected())
                    || Helper.isFieldEmpty(fld_hotel_features) || Helper.isFieldEmpty(fld_hotel_phone) || cmb_hotel_stars.getSelectedIndex() == 0){
                System.out.println(cmb_hotel_stars.getSelectedIndex());
                Helper.showMsg("fill");
            }else {
                String name = fld_hotel_name.getText();
                String city = fld_hotel_city.getText();
                String province = fld_hotel_province.getText();
                String features = fld_hotel_features.getText();
                String adress = fld_hotel_adress.getText();
                String boarding = giveCheckBox();
                Long phone = Long.parseLong(fld_hotel_phone.getText());
                String stars = cmb_hotel_stars.getSelectedItem().toString();
                if (Hotels.addHotel(name, city, province, features, adress, boarding, phone, stars)){
                    Helper.showMsg("success");
                    loadHotelTable();
                    loadRoomHotelCombo();
                    loadRoomBoardCombo();
                }
                fld_hotel_name.setText(null);
                fld_hotel_city.setText(null);
                fld_hotel_province.setText(null);
                fld_hotel_features.setText(null);
                fld_hotel_adress.setText(null);
                fld_hotel_phone.setText(null);
                cmb_hotel_stars.setSelectedIndex(-1);
                cbx_1.setSelected(false);
                cbx_2.setSelected(false);
                cbx_3.setSelected(false);
                cbx_4.setSelected(false);
                cbx_5.setSelected(false);
                cbx_6.setSelected(false);
            }
        });

        updateHotelMenu.addActionListener(e -> {
            int selected_id = Integer.parseInt(tbl_hotel.getValueAt(tbl_hotel.getSelectedRow(),0).toString());
            UpdateHotelGUI up = new UpdateHotelGUI(Hotels.getFetch(selected_id));
            up.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                    loadRoomHotelCombo();
                    loadRoomBoardCombo();
                }
            });
        });

        deleteHotelMenu.addActionListener(e -> {
            if(Helper.confirm("areyousure")){
                int select_id = Integer.parseInt(tbl_hotel.getValueAt(tbl_hotel.getSelectedRow(),0).toString());
                if(Hotels.delete(select_id)){
                    Helper.showMsg("deleted");
                }else{
                    Helper.showMsg("error");
                }
                loadHotelTable();
                loadRoomHotelCombo();
                loadRoomBoardCombo();
                loadRoomsTable();
                loadRezRoomsTable();
                loadReservationTable();
            }
        });
        // -------------------------------HOTELS TAB ENDS----------------------------------------------------------
        // ------------------------------RESERVATION TAB STARTS----------------------------------------------------

        // Reservation List ----------------------------------
        mdl_rezlist = new DefaultTableModel();

        tbl_rez_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_rez_list.rowAtPoint(point);
                tbl_rez_list.setRowSelectionInterval(selected_row, selected_row);
            }
        });
        rezlistMenu = new JPopupMenu();
        JMenuItem deleteRezlistMenu = new JMenuItem("Delete");
        rezlistMenu.add(deleteRezlistMenu);

        Object[] col_rezList = {"ID", "Name", "Hotel Name", "Adult", "Child", "Room Type" ,"Cost", "Starts", "Ends"};
        mdl_rezlist.setColumnIdentifiers(col_rezList);
        row_rezlist = new Object[col_rezList.length];
        loadReservationTable();
        tbl_rez_list.setComponentPopupMenu(rezlistMenu);
        tbl_rez_list.setModel(mdl_rezlist);
        tbl_rez_list.getColumnModel().getColumn(0).setMaxWidth(40);
        tbl_rez_list.getTableHeader().setReorderingAllowed(false);

        deleteRezlistMenu.addActionListener(e -> {
            if(Helper.confirm("areyousure")){
                int select_id = Integer.parseInt(tbl_rez_list.getValueAt(tbl_rez_list.getSelectedRow(),0).toString());
                if(Reservation.delete(select_id)){
                    Helper.showMsg("deleted");
                }else{
                    Helper.showMsg("error");
                }
                loadReservationTable();
                loadRoomsTable();
                loadRezRoomsTable();
            }
        });
        // Reservation List Ends ---------------------------------------

        // Reservation From Room List ----------------------------------
        mdl_rez_roomlist = new DefaultTableModel();
        Object[] col_rez_roomsList = {"ID", "Hotel Name", "City", "Season", "Boarding", "Room Type", "Bed", "Available", "Room Size", "Adult Price", "Child Price"};
        mdl_rez_roomlist.setColumnIdentifiers(col_rez_roomsList);
        row_rez_roomlist = new Object[col_rez_roomsList.length];
        loadRezRoomsTable();
        tbl_rez_roomlist.setModel(mdl_rez_roomlist);
        tbl_rez_roomlist.getColumnModel().getColumn(0).setMaxWidth(40);
        tbl_rez_roomlist.getTableHeader().setReorderingAllowed(false);

        btn_rez_fromRoomlist.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_rez_roomName) || Helper.isFieldEmpty(fld_rez_roomAdult) || Helper.isFieldEmpty(fld_rez_roomChild)
                    || Helper.isFieldEmpty(fld_rez_roomStart) || Helper.isFieldEmpty(fld_rez_roomEnd)){
                Helper.showMsg("fill");
            }else{
                int selected_id = Integer.parseInt(tbl_rez_roomlist.getValueAt(tbl_rez_roomlist.getSelectedRow(),0).toString());
                String name = fld_rez_roomName.getText();
                int adult = Integer.parseInt(fld_rez_roomAdult.getText());
                int child = Integer.parseInt(fld_rez_roomChild.getText());
                String start = fld_rez_roomStart.getText();
                String end = fld_rez_roomEnd.getText();

                if(adult+child <= RoomType.getFetch(Rooms.getFetch(selected_id).getRoom_typeID()).getMaxCostumer()){
                    if(Rooms.getFetch(selected_id).getAvailable() - Reservation.getRoomBasedList(Rooms.getFetch(selected_id).getId()).size() > 0) {
                        System.out.println(Seasons.seasonCheck(start, end, Rooms.getFetch(selected_id).getSeason_id()));
                        switch (Seasons.seasonCheck(start, end, Rooms.getFetch(selected_id).getSeason_id())) {
                            case "true":
                                if (Reservation.addReservation(name, adult, child, selected_id, start, end)) {
                                    Helper.showMsg("success");
                                    loadRezRoomsTable();
                                    loadRoomsTable();
                                    loadReservationTable();

                                    fld_rez_roomName.setText(null);
                                    fld_rez_roomAdult.setText(null);
                                    fld_rez_roomChild.setText(null);
                                    fld_rez_roomStart.setText(null);
                                    fld_rez_roomEnd.setText(null);
                                } else {
                                    Helper.showMsg("error");
                                }
                                break;
                            case "endWrong":
                                Helper.showMsg("endWrong");
                                break;
                            case "startWrong":
                                Helper.showMsg("startWrong");
                                break;
                            case "false":
                                Helper.showMsg("Wrong Season Selection\n" + Seasons.getFetch(Rooms.getFetch(selected_id).getSeason_id()).getName()
                                        + " in between " + Seasons.getFetch(Rooms.getFetch(selected_id).getSeason_id()).getStartDate() + " and " + Seasons.getEndDate(Seasons.getFetch(Rooms.getFetch(selected_id).getSeason_id()).getId()));
                                break;
                            case "end before start":
                                Helper.showMsg("End date cant be before Start Date");
                                break;
                        }
                    }else{
                        Helper.showMsg("No Available Rooms");
                    }
                }else{
                    Helper.showMsg("Not enough bed");
                }
            }
        });
        // Reservation From Room List Ends----------------------------------
        // Reservation Search Starts -------------------------------------------
        mdl_searchlist = new DefaultTableModel();
        Object[] col_searchlist = {"ID", "Hotel", "Available", " Boarding", "Room Type", "Features"};
        mdl_searchlist.setColumnIdentifiers(col_searchlist);
        row_searchlist = new Object[col_searchlist.length];

        tbl_search.setModel(mdl_searchlist);
        tbl_search.getColumnModel().getColumn(0).setMaxWidth(35);
        tbl_search.getTableHeader().setReorderingAllowed(false);

        loadSearchCityCombo();


        btn_search.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_search_name) || Helper.isFieldEmpty(fld_search_adult) || Helper.isFieldEmpty(fld_search_child)
                    || Helper.isFieldEmpty(fld_search_start) || Helper.isFieldEmpty(fld_search_end) || cmb_search_city.getSelectedIndex() == -1){
                Helper.showMsg("fill");
            }else{
                loadSearchTable();
            }
        });


        // Reservation Search Ends -------------------------------------------
        // ------------------------------RESERVATION TAB ENDS------------------------------------------------------
        // ----------------------------------INFO TAB--------------------------------------------------------------
        mdl_boardlist = new DefaultTableModel();
        Object[] col_boardingList = {"ID" , "Type"};
        mdl_boardlist.setColumnIdentifiers(col_boardingList);
        row_boardlist = new Object[col_boardingList.length];
        loadBoardingTable();
        tbl_boarding.setModel(mdl_boardlist);
        tbl_boarding.getColumnModel().getColumn(0).setMaxWidth(40);
        tbl_boarding.getTableHeader().setReorderingAllowed(false);
        //     --------------------------------
        mdl_seasonlist = new DefaultTableModel();
        Object[] col_seasonsList = {"ID" , "Name", "Start Date", "End Date"};
        mdl_seasonlist.setColumnIdentifiers(col_seasonsList);
        row_seasonlist = new Object[col_seasonsList.length];
        loadSeasonTable();
        tbl_seasons.setModel(mdl_seasonlist);
        tbl_seasons.getColumnModel().getColumn(0).setMaxWidth(40);
        tbl_seasons.getTableHeader().setReorderingAllowed(false);
        // -------------------------------
        mdl_roomtypelist = new DefaultTableModel();
        Object[] col_roomtypeList = {"ID" , "Name", "Bed", "Max Constumer", "Room Size"};
        mdl_roomtypelist.setColumnIdentifiers(col_roomtypeList);
        row_roomtypelist = new Object[col_roomtypeList.length];
        loadRoomTypeTable();
        tbl_roomtype.setModel(mdl_roomtypelist);
        tbl_roomtype.getColumnModel().getColumn(0).setMaxWidth(40);
        tbl_roomtype.getTableHeader().setReorderingAllowed(false);
        // --------------------------------INFO TAB ENDS-----------------------------------------------------------

        btn_logout.addActionListener(e -> {
            LoginGUI login = new LoginGUI();
            dispose();
        });


    }

    // ------------------   TABLE ---------------------------------------------------------
    public void loadReservationTable(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_rez_list.getModel();
        clearModel.setRowCount(0);

        for(Reservation obj: Reservation.getList()){
            Period period = Period.between(LocalDate.parse(obj.getStarts()), LocalDate.parse(obj.getEnds()));
            row_rezlist[0] = obj.getId();
            row_rezlist[1] = obj.getName();
            row_rezlist[2] = Hotels.getFetch(Rooms.getFetch(obj.getRoom_id()).getHotel_id()).getName();
            row_rezlist[3] = obj.getAdult();
            row_rezlist[4] = obj.getChild();
            row_rezlist[5] = RoomType.getFetch(Rooms.getFetch(obj.getRoom_id()).getRoom_typeID()).getName() +" "+ RoomType.getFetch(Rooms.getFetch(obj.getRoom_id()).getRoom_typeID()).getBed();
            row_rezlist[6] = (Rooms.getFetch(obj.getRoom_id()).getAdult_price() * obj.getAdult() + Rooms.getFetch(obj.getRoom_id()).getChild_price() * obj.getChild()) * (period.getDays()+1);
            row_rezlist[7] = obj.getStarts();
            row_rezlist[8] = obj.getEnds();
            mdl_rezlist.addRow(row_rezlist);
        }
    }

    public void loadSearchTable() {
        ArrayList<Hotels> htl = Hotels.getListbyCity(cmb_search_city.getSelectedItem().toString());

        String start = fld_search_start.getText();
        String end = fld_search_end.getText();
        int season_id = Seasons.getSeason(start, end);
        switch (season_id) {
            case 0:
                Helper.showMsg("End date cant be before Start Date");
                break;
            case 99:
                Helper.showMsg("error");
                break;
        }
        for (Hotels h : htl) {
            ArrayList<Rooms> room = Rooms.getListbyHotelandBoard(h.getId(), season_id);
            for (Rooms r : room){
                row_searchlist[0] = r.getId();
                row_searchlist[1] = Hotels.getFetch(r.getHotel_id()).getName();
                row_searchlist[2] = r.getAvailable() - Reservation.getRoomBasedList(r.getId()).size();
                row_searchlist[3] = Boarding.getFetch(r.getBoarding_id()).getType();
                row_searchlist[4] = RoomType.getFetch(r.getRoom_typeID()).getName();
                row_searchlist[5] = Hotels.getFetch(r.getHotel_id()).getFeatures();

            }
        }


    }

    public void loadRoomsTable(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_rooms.getModel();
        clearModel.setRowCount(0);

        for(Rooms obj : Rooms.getList()){
            row_roomlist[0] = obj.getId();
            row_roomlist[1] = obj.getHotel().getName();
            row_roomlist[2] = obj.getHotel().getCity();
            row_roomlist[3] = obj.getSeason().getName();
            row_roomlist[4] = obj.getBoard().getType();
            row_roomlist[5] = obj.getRoomsType().getName();
            row_roomlist[6] = obj.getRoomsType().getBed();
            row_roomlist[7] = obj.getAvailable() - Reservation.getRoomBasedList(obj.getId()).size();
            row_roomlist[8] = obj.getRoomsType().getRoom_size();
            row_roomlist[9] = obj.getAdult_price();
            row_roomlist[10] = obj.getChild_price();
            mdl_roomlist.addRow(row_roomlist);
        }
        loadRoomHotelCombo();
        loadRoomSeasonCombo();
        loadRoomBoardCombo();
        loadRoomTypeCombo();
    }

    public void loadRezRoomsTable() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_rez_roomlist.getModel();
        clearModel.setRowCount(0);

        for (Rooms obj : Rooms.getList()) {
            row_rez_roomlist[0] = obj.getId();
            row_rez_roomlist[1] = obj.getHotel().getName();
            row_rez_roomlist[2] = obj.getHotel().getCity();
            row_rez_roomlist[3] = obj.getSeason().getName();
            row_rez_roomlist[4] = obj.getBoard().getType();
            row_rez_roomlist[5] = obj.getRoomsType().getName();
            row_rez_roomlist[6] = obj.getRoomsType().getBed();
            row_rez_roomlist[7] = obj.getAvailable() - Reservation.getRoomBasedList(obj.getId()).size();
            row_rez_roomlist[8] = obj.getRoomsType().getRoom_size();
            row_rez_roomlist[9] = obj.getAdult_price();
            row_rez_roomlist[10] = obj.getChild_price();
            mdl_rez_roomlist.addRow(row_rez_roomlist);
        }
    }

    public void loadHotelTable(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel.getModel();
        clearModel.setRowCount(0);

        for(Hotels obj : Hotels.getList()){
            row_hotellist[0] = obj.getId();
            row_hotellist[1] = obj.getName();
            row_hotellist[2] = obj.getCity();
            row_hotellist[3] = obj.getProvince();
            row_hotellist[4] = obj.getFeatures();
            row_hotellist[5] = obj.getAdress();
            row_hotellist[6] = Boarding.getBoardingtoStr(obj.getBoarding());
            row_hotellist[7] = obj.getPhone();
            row_hotellist[8] = obj.getStars();
            mdl_hotellist.addRow(row_hotellist);
        }
    }

    public void loadBoardingTable(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_boarding.getModel();
        clearModel.setRowCount(0);

        for(Boarding obj : Boarding.getList()){
            row_boardlist[0] = obj.getId();
            row_boardlist[1] = obj.getType();
            mdl_boardlist.addRow(row_boardlist);
        }
    }

    public void loadRoomTypeTable(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_roomtype.getModel();
        clearModel.setRowCount(0);

        for(RoomType obj : RoomType.getList()){
            row_roomtypelist[0] = obj.getId();
            row_roomtypelist[1] = obj.getName();
            row_roomtypelist[2] = obj.getBed();
            row_roomtypelist[3] = obj.getMaxCostumer();
            row_roomtypelist[4] = obj.getRoom_size();
            mdl_roomtypelist.addRow(row_roomtypelist);
        }
    }

    public void loadSeasonTable(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_seasons.getModel();
        clearModel.setRowCount(0);


        for(Seasons obj : Seasons.getList()){
            row_seasonlist[0] = obj.getId();
            row_seasonlist[1] = obj.getName();
            row_seasonlist[2] = obj.getStartDate();
            row_seasonlist[3] = Seasons.getEndDate(obj.getId());
            mdl_seasonlist.addRow(row_seasonlist);
        }
    }

    // ------------------ COMBO BOX --------------------------------------------
    public void loadRoomHotelCombo(){
        cmb_room_hotel.removeAllItems();
        cmb_room_hotel.addItem(null);
        for(Hotels obj : Hotels.getList()){
            cmb_room_hotel.addItem(new Item(obj.getId(), obj.getName()));
        }
    }

    public void loadSearchCityCombo(){
        cmb_search_city.removeAllItems();
        cmb_search_city.addItem(null);
        HashSet<String> htlcty = new HashSet<>();
        int count=1;
        for(Hotels obj : Hotels.getList()){
            htlcty.add(obj.getCity());
        }
        for (String s : htlcty) {
            cmb_search_city.addItem(new Item(count, s));
            count++;
        }
    }


    public void loadRoomSeasonCombo(){
        cmb_room_season.removeAllItems();
        cmb_room_season.addItem(null);
        for(Seasons obj : Seasons.getList()){
            cmb_room_season.addItem(new Item(obj.getId(), obj.getName()));
        }
    }

    public void loadRoomBoardCombo(){
        cmb_room_board.removeAllItems();
        cmb_room_board.addItem(null);
        if(cmb_room_hotel.getSelectedIndex() == -1){
            for(Boarding obj : Boarding.getList()){
                cmb_room_board.addItem(new Item(obj.getId(), obj.getType()));
            }
        }else{
            Hotels obje = Hotels.getFetch(cmb_room_hotel.getSelectedItem().toString());
            int counter = 1;
            for(String b : Boarding.getBoardingforCMB(obje.getBoarding())){
                cmb_room_board.addItem(new Item(counter, b));
                counter++;
            }
        }
    }

    public void loadSearchBoardCombo(){
        cmb_search_board.removeAllItems();
        cmb_search_board.addItem(null);
        for(Boarding obj : Boarding.getList()){
            cmb_search_board.addItem(new Item(obj.getId(), obj.getType()));
        }
    }

    public void loadRoomTypeCombo(){
        cmb_room_type.removeAllItems();
        cmb_room_type.addItem(null);
        for(RoomType obj : RoomType.getList()){
            cmb_room_type.addItem(new Item(obj.getId(), obj.getName() + " - " + obj.getBed()));
        }
    }

    public void loadSearchRoomTypeCombo(){
        cmb_search_roomtype.removeAllItems();
        cmb_search_roomtype.addItem(null);
        for(RoomType obj : RoomType.getList()){
            cmb_search_roomtype.addItem(new Item(obj.getId(), obj.getName() + " - " + obj.getBed()));
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

    public static void main(String[] args) {
        Helper.setLayout();
        User aw = new User(1, "Admin", "Admin1", "password", "agency_worker");
        PersonelGUI pres = new PersonelGUI(aw);
    }
}