package subProcesos;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

class ColoredRenderer extends DefaultTableCellRenderer{
        private Color color1;
        private Color color2;
    
           public ColoredRenderer()
           {
            super();
            color1 = new Color(255,255,255);
            color2 = new Color(255,255,180);
            

           }

    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)

        {

            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if(value==null)
                setText("");
            else
                 setText(String.valueOf(value));
                 component.setBackground(row%2==0 ? color1 : color2);
               return component;

           }

       }

 

