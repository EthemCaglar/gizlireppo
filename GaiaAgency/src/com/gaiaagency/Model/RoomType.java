package com.gaiaagency.Model;

import com.gaiaagency.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RoomType {
    private int id;
    private String name;
    private String bed;

    private int maxCostumer;
    private int room_size;

    public RoomType(int id, String name, String bed, int maxCostumer, int room_size) {
        this.id = id;
        this.name = name;
        this.bed = bed;
        this.maxCostumer = maxCostumer;
        this.room_size = room_size;
    }
    public int getMaxCostumer() {
        return maxCostumer;
    }
    public void setMaxCostumer(int maxCostumer) {
        this.maxCostumer = maxCostumer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public int getRoom_size() {
        return room_size;
    }

    public void setRoom_size(int room_size) {
        this.room_size = room_size;
    }

    public static ArrayList<RoomType> getList(){
        ArrayList<RoomType> roomtypelist = new ArrayList<>();
        RoomType obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(("SELECT * FROM room_type"));
            while (rs.next()){
                obj = new RoomType(rs.getInt("id"), rs.getString("name"), rs.getString("bed"), rs.getInt("maxCostumer"),rs.getInt("room_size"));
                roomtypelist.add(obj);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomtypelist;
    }

    public static RoomType getFetch(int id){ // are there any hotels named as "name"
        RoomType obj = null;
        String query = "SELECT * FROM room_type WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = new RoomType(rs.getInt("id"), rs.getString("name"), rs.getString("bed"), rs.getInt("maxCostumer"), rs.getInt("room_size"));
            }

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return obj;
    }

    public static RoomType getFetch(String name){ // are there any hotels named as "name"
        RoomType obj = null;
        String query = "SELECT * FROM room_type WHERE name = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = new RoomType(rs.getInt("id"), rs.getString("name"), rs.getString("bed"), rs.getInt("maxCostumer"), rs.getInt("room_size"));
            }

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return obj;
    }
}
