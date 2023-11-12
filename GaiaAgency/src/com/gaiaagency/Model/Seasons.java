package com.gaiaagency.Model;

import com.gaiaagency.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Seasons {
    private int id;
    private String name;
    private String startDate;

    public Seasons(int id, String name, String startDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {

        return LocalDate.parse(startDate);
    }

    public static LocalDate getEndDate(int id){
        Seasons obj = null;
        String query = "SELECT * FROM seasons WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = new Seasons(rs.getInt("id"),rs.getString("name"), rs.getString("startDate"));
            }

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        LocalDate enddate = obj.getStartDate().plusMonths(4).minusDays(1);
        return enddate;
    }

    public static ArrayList<Seasons> getList(){
        ArrayList<Seasons> seasonslist = new ArrayList<>();
        Seasons obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(("SELECT * FROM seasons"));
            while (rs.next()){
                obj = new Seasons(rs.getInt("id"), rs.getString("name"), rs.getString("startDate"));
                seasonslist.add(obj);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seasonslist;
    }

    public static Seasons getFetch(int id){ // are there any season named as "name"
        Seasons obj = null;
        String query = "SELECT * FROM seasons WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = new Seasons(rs.getInt("id"),rs.getString("name"),rs.getString("startDate"));
            }

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return obj;
    }

    public static Seasons getFetch(String name){ // are there any season named as "name"
        Seasons obj = null;
        String query = "SELECT * FROM seasons WHERE name = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = new Seasons(rs.getInt("id"),rs.getString("name"),rs.getString("startDate"));
            }

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return obj;
    }

    public static int getSeason(String start, String end){
        LocalDate startLD = LocalDate.parse(start);
        LocalDate endLD = LocalDate.parse(end);
        boolean startEnd = !startLD.isAfter(endLD);
        ArrayList<Seasons> seasonslist = Seasons.getList();
        if(startEnd){
            for(Seasons s: seasonslist){
                boolean isStartWithinRange = !startLD.isBefore(s.getStartDate()) && !startLD.isAfter(getEndDate(s.getId()));
                boolean isEndWithinRange = !endLD.isBefore(s.getStartDate()) && !endLD.isAfter(getEndDate(s.getId()));
                if (isEndWithinRange && isStartWithinRange){
                    System.out.println(s.getName());
                    return s.getId();
                }
            }
        }else {
            return 0;
        }
        return 99;
    }

    public static String seasonCheck(String start, String end, int season){
        Seasons selectSeason = Seasons.getFetch(season);
        LocalDate startLD = LocalDate.parse(start);
        LocalDate endLD = LocalDate.parse(end);
        boolean startEnd = !startLD.isAfter(endLD);
        boolean isStartWithinRange = !startLD.isBefore(selectSeason.getStartDate()) && !startLD.isAfter(getEndDate(season));
        boolean isEndWithinRange = !endLD.isBefore(selectSeason.getStartDate()) && !endLD.isAfter(getEndDate(season));

        if(startEnd) {
            if (isEndWithinRange && isStartWithinRange) {
                return "true";
            } else if (!isEndWithinRange && isStartWithinRange) {
                return "endWrong";
            } else if (isEndWithinRange && !isStartWithinRange) {
                return "startWrong";
            } else {
                return "false";
            }
        }else {
            return "end before start";
        }
    }
}
