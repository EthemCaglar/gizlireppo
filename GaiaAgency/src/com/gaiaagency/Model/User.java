package com.gaiaagency.Model;

import com.gaiaagency.Helper.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String usern;
    private String pass;
    private String type;

    public User(int id, String name, String usern, String pass, String type) {
        this.id = id;
        this.name = name;
        this.usern = usern;
        this.pass = pass;
        this.type = type;
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

    public String getUsern() {
        return usern;
    }

    public void setUsern(String usern) {
        this.usern = usern;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static ArrayList<User> getList(){
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM user";
        User obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                obj = new User(rs.getInt("id"),rs.getString("name"),rs.getString("usern"),rs.getString("pass"),rs.getString("type"));
                userList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public static boolean addUser(String name, String usern, String pass, String type){
        String query = "INSERT INTO user (name,usern,pass,type) VALUES (?,?,?,?)";
        User findUser = User.getFetch(usern);
        if(findUser != null){
            Helper.showMsg("same username");
            return false;
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,usern);
            pr.setString(3,pass);
            pr.setString(4,type);

            int response = pr.executeUpdate();

            if(response == -1){
                Helper.showMsg("error");
            }
            return response != -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getFetch(String usern){
        User obj = null;
        String query = "SELECT * FROM user WHERE usern = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,usern);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = new User(rs.getInt("id"),rs.getString("name"),rs.getString("usern"),rs.getString("pass"),rs.getString("type"));
            }

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return obj;
    }

    public static User getFetch(String usern, String pass){
        User obj = null;
        String query = "SELECT * FROM user WHERE usern = ? AND pass = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,usern);
            pr.setString(2,pass);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = new User(rs.getInt("id"),rs.getString("name"),rs.getString("usern"),rs.getString("pass"),rs.getString("type"));
            }

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return obj;
    }

    public static boolean delete(int id){
        String query = "DELETE FROM user WHERE ID = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean update(int id,String name, String usern, String pass, String type) {
        String query = "UPDATE user SET name=?,usern=?, pass=?, type=? WHERE id = ?";
        User findUser = User.getFetch(usern);
        if(findUser != null && findUser.getId() != id){
            Helper.showMsg("sameusername");
            return false;
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,usern);
            pr.setString(3,pass);
            pr.setString(4,type);
            pr.setInt(5,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
