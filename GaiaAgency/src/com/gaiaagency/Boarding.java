package com.gaiaagency.Model;

import com.gaiaagency.Helper.DBConnector;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Boarding {
    private int id;
    private String type;

    public Boarding(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public static ArrayList<Boarding> getList(){
        ArrayList<Boarding> boardlist = new ArrayList<>();
        Boarding obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(("SELECT * FROM boarding"));
            while (rs.next()){
                obj = new Boarding(rs.getInt("id"), rs.getString("type"));
                boardlist.add(obj);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return boardlist;

    }
    public static String getBoardingtoStr(String boards){
        char[] boardsChar = boards.toCharArray();
        String result = "";
        for(Character c : boardsChar){
            result += Boarding.getFetch(Character.getNumericValue(c)).getType() + " ";
        }
        return result;
    }

    public static ArrayList<String> getBoardingforCMB(String boards){
        char[] boardsChar = boards.toCharArray();
        ArrayList<String> result= new ArrayList<>();
        for(Character c : boardsChar){
            result.add(Boarding.getFetch(Character.getNumericValue(c)).getType());
        }
        return result;
    }

    public static Boarding getFetch(String type){ // are there any boarding named as "name"
        Boarding obj = null;
        String query = "SELECT * FROM boarding WHERE type = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,type);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = new Boarding(rs.getInt("id"), rs.getString("type"));
            }

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return obj;
    }

    public static Boarding getFetch(int id){ // are there any hotels named as "name"
        Boarding obj = null;
        String query = "SELECT * FROM boarding WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = new Boarding(rs.getInt("id"), rs.getString("type"));
            }

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return obj;
    }
}
