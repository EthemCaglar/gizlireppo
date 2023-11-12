package com.gaiaagency.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static void setLayout(){
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if("CDE/Motif".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    public static int screenCenter(String axis, Dimension size){
        int point;
        switch (axis){
            case "x":
                point = (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
                break;
            case "y":
                point = (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
                break;
            default:
                point=0;
        }
        return point;
    }

    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }

    public static void showMsg(String str){
        String msg;
        String title;

        switch (str){
            case "fill":
                msg = "Please fill all blank spots.";
                title = "Empty Input Slot";
                break;
            case  "success":
                msg = "New profile saved.";
                title = "Success";
                break;
            case "deleted":
                msg = "Successfully Deleted.";
                title = "Deleted";
                break;
            case "error":
                msg = "You faced with error.";
                title = "Error";
                break;
            case "sameusername":
                msg = "Another profile have same username";
                title = "Check username";
                break;
            default:
                msg = str;
                title = "Message";
        }

        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str){
        String msg;
        switch (str){
            case "areyousure":
                msg = "Are you sure for deleting";
                break;
            default:
                msg = str;
        }

        return JOptionPane.showConfirmDialog(null, msg, "Permanent Action Warning", JOptionPane.YES_NO_OPTION) == 0;
    }
}

