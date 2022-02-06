package appletdb;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class applet1 extends JApplet {

  private JButton dropButton;

  public void init() {
    Connection connection;
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      connection = DriverManager
          .getConnection("jdbc:mysql://localhost/pizzeria?user=javagu90&password=heil");
    } catch (Exception connectException) {
      connectException.printStackTrace();
    }
  }
    
}
