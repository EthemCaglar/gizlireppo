package com.gaiaagency.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.gaiaagency.Helper.*;

public class Hotels {
    private int id;
    private String name;
    private String city;
    private String province;
    private String features;
    private String adress;
    private String boarding;
    private Long phone;
    private String stars;
    private Boarding board;

    public Hotels(int id, String name, String city, String province, String features, String adress, String boarding, Long phone, String stars) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.province = province;
        this.features = features;
        this.adress = adress;
        this.boarding = boarding;
        this.phone = phone;
        this.stars = stars;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getBoarding() {
        return boarding;
    }

    public void setBoarding(String boarding) {
        this.boarding = boarding;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public static ArrayList<Hotels> getList(){
        ArrayList<Hotels> hotelslist = new ArrayList<>();
        Hotels obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(("SELECT * FROM hotels"));
            while (rs.next()){
                obj = new Hotels(rs.getInt("id"),rs.getString("name"),rs.getString("city"), rs.getString("province"), rs.getString("features"), rs.getString("adress"), rs.getString("boarding"), rs.getLong("phone"), rs.getString("stars"));
                hotelslist.add(obj);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelslist;
    }

    public static ArrayList<Hotels> getListbyCity(String city){
        ArrayList<Hotels> hotelslist = new ArrayList<>();
        Hotels obj;
        String query = "SELECT * FROM hotels WHERE city = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,city);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Hotels(rs.getInt("id"),rs.getString("name"),rs.getString("city"), rs.getString("province"), rs.getString("features"), rs.getString("adress"), rs.getString("boarding"), rs.getLong("phone"), rs.getString("stars"));
                hotelslist.add(obj);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelslist;
    }

    public static boolean addHotel(String name, String city, String province, String features, String adress, String boarding , Long phone, String stars){
        String query = "INSERT INTO hotels (name,city,province,features,adress,boarding,phone,stars) VALUES (?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,city);
            pr.setString(3,province);
            pr.setString(4,features);
            pr.setString(5,adress);
            pr.setString(6,boarding);
            pr.setLong(7,phone);
            pr.setString(8,stars);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean update(int id, String name, String city, String province, String features, String adress, String boarding, Long phone, String stars){
        String query = "UPDATE hotels SET name = ?, city = ?, province = ?, features = ?, adress = ?, boarding = ?, phone = ?, stars = ? WHERE id = ?";
        Hotels findHotel = Hotels.getFetch(name);
        if(findHotel != null && findHotel.getId() != id){ // if list have same hotel name findhotel must have data
            Helper.showMsg("same username");
            return false;
        }

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,city);
            pr.setString(3,province);
            pr.setString(4,features);
            pr.setString(5,adress);
            pr.setString(6,boarding);
            pr.setLong(7,phone);
            pr.setString(8,stars);
            pr.setInt(9,id);

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean delete(int id){
        String query = "DELETE FROM hotels WHERE id = ?";
        ArrayList<Rooms> roomlist = Rooms.getListbyHotel(id);
        for(Rooms r: roomlist){
            Rooms.delete(r.getId());
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Hotels getFetch(String name){ // are there any hotels named as "name"
        Hotels obj = null;
        String query = "SELECT * FROM hotels WHERE name = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = new Hotels(rs.getInt("id"),rs.getString("name"),rs.getString("city"), rs.getString("province"), rs.getString("features"), rs.getString("adress"), rs.getString("boarding"), rs.getLong("phone"), rs.getString("stars"));
            }

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return obj;
    }

    public static Hotels getFetch(int id){ // are there any hotels named as "name"
        Hotels obj = null;
        String query = "SELECT * FROM hotels WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = new Hotels(rs.getInt("id"),rs.getString("name"),rs.getString("city"), rs.getString("province"), rs.getString("features"), rs.getString("adress"), rs.getString("boarding"), rs.getLong("phone"), rs.getString("stars"));
            }

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return obj;
    }
}
