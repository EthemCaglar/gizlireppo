package com.gaiaagency.Model;

import com.gaiaagency.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class Reservation {
    private int id;
    private String name;
    private int adult;
    private int child;
    private int room_id;
    private String starts;
    private String ends;

    public Reservation(int id, String name, int adult, int child, int room_id, String starts, String ends) {
        this.id = id;
        this.name = name;
        this.adult = adult;
        this.child = child;
        this.room_id = room_id;
        this.starts = starts;
        this.ends = ends;
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

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getStarts() {
        return starts;
    }

    public void setStarts(String starts) {
        this.starts = starts;
    }

    public String getEnds() {
        return ends;
    }

    public void setEnds(String ends) {
        this.ends = ends;
    }

    public static boolean addReservation(String name, int adult, int child, int room_id, String starts, String ends){
        String query = "INSERT INTO reservation (name, adult, child, room_id, startDate, endDate) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setInt(2,adult);
            pr.setInt(3,child);
            pr.setInt(4,room_id);
            pr.setString(5,starts);
            pr.setString(6,ends);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Reservation> getList(){
        ArrayList<Reservation> reservationlist = new ArrayList<>();
        Reservation obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(("SELECT * FROM reservation"));
            while (rs.next()){
                obj = new Reservation(rs.getInt("id"), rs.getString("name"), rs.getInt("adult"), rs.getInt("child"), rs.getInt("room_id"),rs.getString("startDate"), rs.getString("endDate"));
                reservationlist.add(obj);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservationlist;
    }

    public static ArrayList<Reservation> getRoomBasedList(int room_id){
        ArrayList<Reservation> reservationlist = new ArrayList<>();
        Reservation obj;
        String query = "SELECT * FROM reservation WHERE room_id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,room_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Reservation(rs.getInt("id"), rs.getString("name"), rs.getInt("adult"), rs.getInt("child"), rs.getInt("room_id"),rs.getString("startDate"), rs.getString("endDate"));
                reservationlist.add(obj);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservationlist;
    }

    public static boolean delete(int id){
        String query = "DELETE FROM reservation WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
