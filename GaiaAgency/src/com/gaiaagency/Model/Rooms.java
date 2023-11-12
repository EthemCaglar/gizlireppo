package com.gaiaagency.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.gaiaagency.Helper.*;

public class Rooms {
    private int id;
    private int hotel_id;
    private int season_id;
    private int boarding_id;
    private int room_typeID;
    private int available;
    private int adult_price;
    private int child_price;
    //---------------------------------------
    private Hotels hotel;
    private Seasons season;
    private Boarding board;
    private RoomType roomsType;

    public Rooms(int id, int hotel_id, int season_id, int boarding_id, int room_typeID, int available, int adult_price, int child_price) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.season_id = season_id;
        this.boarding_id = boarding_id;
        this.room_typeID = room_typeID;
        this.available = available;
        this.adult_price = adult_price;
        this.child_price = child_price;
        this.hotel = Hotels.getFetch(hotel_id);
        this.season = Seasons.getFetch(season_id);
        this.board = Boarding.getFetch(boarding_id);
        this.roomsType = RoomType.getFetch(room_typeID);
    }

    public int getAvailable() {
        return available;
    }
    public void setAvailable(int available) {
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getHotel_id() {
        return hotel_id;
    }
    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }
    public int getSeason_id() {
        return season_id;
    }
    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }
    public int getBoarding_id() {
        return boarding_id;
    }
    public void setBoarding_id(int boarding_id) {
        this.boarding_id = boarding_id;
    }
    public int getRoom_typeID() {
        return room_typeID;
    }
    public void setRoom_typeID(int room_typeID) {
        this.room_typeID = room_typeID;
    }
    public int getAdult_price() {
        return adult_price;
    }
    public void setAdult_price(int adult_price) {
        this.adult_price = adult_price;
    }
    public int getChild_price() {
        return child_price;
    }
    public void setChild_price(int child_price) {
        this.child_price = child_price;
    }
    public Hotels getHotel() {
        return hotel;
    }
    public void setHotel(Hotels hotel) {
        this.hotel = hotel;
    }
    public Seasons getSeason() {
        return season;
    }
    public void setSeason(Seasons season) {
        this.season = season;
    }
    public Boarding getBoard() {
        return board;
    }
    public void setBoard(Boarding board) {
        this.board = board;
    }
    public RoomType getRoomsType() {
        return roomsType;
    }
    public void setRoomsType(RoomType roomsType) {
        this.roomsType = roomsType;
    }

    public static ArrayList<Rooms> getList(){
        ArrayList<Rooms> roomsList = new ArrayList<>();

        Rooms obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM rooms");
            while (rs.next()){
                obj = new Rooms(rs.getInt("id"), rs.getInt("hotel_id"), rs.getInt("season_id"), rs.getInt("boarding_id"), rs.getInt("room_typeID"), rs.getInt("available"), rs.getInt("adult_price"), rs.getInt("child_price"));
                roomsList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomsList;
    }

    public static boolean addRoom(int hotel_id, int season_id, int boarding_id, int room_typeID, int available, int adult_price, int child_price){
        String query = "INSERT INTO rooms (hotel_id,season_id,boarding_id,room_typeID,available,adult_price,child_price) VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            pr.setInt(2,season_id);
            pr.setInt(3,boarding_id);
            pr.setInt(4,room_typeID);
            pr.setInt(5,available);
            pr.setInt(6,adult_price);
            pr.setInt(7,child_price);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean update(int id, int hotel_id, int season_id, int boarding_id, int room_typeID, int available, int adult_price, int child_price){
        String query = "UPDATE rooms SET hotel_id = ?, season_id = ?, boarding_id = ?, room_typeID = ?, available = ?, adult_price = ? , child_price = ? WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            pr.setInt(2,season_id);
            pr.setInt(3,boarding_id);
            pr.setInt(4,room_typeID);
            pr.setInt(5,available);
            pr.setInt(6,adult_price);
            pr.setInt(7,child_price);
            pr.setInt(8,id);

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean delete(int id){
        String query = "DELETE FROM rooms WHERE id = ?";
        ArrayList<Reservation> rezList = Reservation.getRoomBasedList(id);
        for (Reservation r : rezList){
            Reservation.delete(r.getId());
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Rooms> getListbyHotelandBoard(int hotel_id, int boarding_id){
        ArrayList<Rooms> roomlist = new ArrayList<>();
        Rooms obj;

        String query = "SELECT * FROM rooms WHERE hotel_id = ? AND boarding_id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            pr.setInt(2,boarding_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Rooms(rs.getInt("id"), rs.getInt("hotel_id"), rs.getInt("season_id"), rs.getInt("boarding_id"), rs.getInt("room_typeID"), rs.getInt("available"), rs.getInt("adult_price"), rs.getInt("child_price"));
                roomlist.add(obj);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomlist;
    }

    public static ArrayList<Rooms> getListbyHotel(int hotel_id){
        ArrayList<Rooms> roomlist = new ArrayList<>();
        Rooms obj;

        String query = "SELECT * FROM rooms WHERE hotel_id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Rooms(rs.getInt("id"), rs.getInt("hotel_id"), rs.getInt("season_id"), rs.getInt("boarding_id"), rs.getInt("room_typeID"), rs.getInt("available"), rs.getInt("adult_price"), rs.getInt("child_price"));
                roomlist.add(obj);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomlist;
    }

    public static Rooms getFetch(int id){ // are there any hotels named as "name"
        Rooms obj = null;
        String query = "SELECT * FROM rooms WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            int result;
            if(rs.next()){
                obj = new Rooms(rs.getInt("id"),rs.getInt("hotel_id"),rs.getInt("season_id"), rs.getInt("boarding_id"), rs.getInt("room_typeID"), rs.getInt("available"), rs.getInt("adult_price"), rs.getInt("child_price"));
            }

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return obj;
    }
}
