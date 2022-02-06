/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estrellaroja.confirmacionviajes.util;

import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author EKS Victor
 */
public class CustomTableCellRenderer extends DefaultTableCellRenderer {

  public CustomTableCellRenderer() {
  }

  @Override
  public Component getTableCellRendererComponent(JTable table, Object obj, boolean isSelected, boolean hasFocus, int row, int column) {
    Component cell = null;
    if (obj instanceof java.sql.Timestamp) {
      String s = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(obj);
      cell = super.getTableCellRendererComponent(table, s, isSelected, hasFocus, row, column);
    } else {
      cell = super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);
    }
    if (isSelected) {
      cell.setBackground(new Color(0, 175, 255));
      cell.setForeground(Color.WHITE);
    } else {
      cell.setForeground(Color.BLACK);
      if (row % 2 == 0) {
        cell.setBackground(new Color(217, 229, 241));
      } else {
        cell.setBackground(Color.WHITE);
      }
    }

    return cell;
  }
}
