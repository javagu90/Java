/*
**  For text selection you have two choices. Remove the "xxx" from either
**  editCellAt() or prepareEditor() method.
**  The difference is in how mouse double clicking works
**
**  To place a cell directly into edit mode, use the changeSelection() method.
**  Be aware this will generate a TableModelEvent every time you leave a cell.
**  You can also use either of the above text selection methods.
*/
 
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.table.*;
 
public class TableEditCell extends JFrame
{
	public TableEditCell()
	{
		String[] columnNames = {"Number", "Letter"};
		Object[][] data = { {"1", "A"}, {"2", "B"}, {"3", "C"} };
 
		JTable table = new JTable(data, columnNames)
		{
			//  Place cell in edit mode when it 'gains focus'
 
			public void xxxchangeSelection(
				int row, int column, boolean toggle, boolean extend)
			{
				super.changeSelection(row, column, toggle, extend);
 
				if (editCellAt(row, column))
					getEditorComponent().requestFocusInWindow();
			}
 
			//  Select the text when the cell starts editing
			//  a) text will be replaced when you start typing in a cell
			//  b) text will be selected when you use F2 to start editing
			//  c) text will be selected when double clicking to start editing
 
			public boolean xxxeditCellAt(int row, int column, EventObject e)
			{
				boolean result = super.editCellAt(row, column, e);
				final Component editor = getEditorComponent();
 
				if (editor != null && editor instanceof JTextComponent)
				{
					if (e == null)
					{
						((JTextComponent)editor).selectAll();
					}
					else
					{
						SwingUtilities.invokeLater(new Runnable()
						{
							public void run()
							{
								((JTextComponent)editor).selectAll();
							}
						});
					}
				}
 
				return result;
			}
 
			//  Select the text when the cell starts editing
			//  a) text will be replaced when you start typing in a cell
			//  b) text will be selected when you use F2 to start editing
			//  c) caret is placed at end of text when double clicking to start editing
 
			public Component xxxprepareEditor(
				TableCellEditor editor, int row, int column)
			{
				Component c = super.prepareEditor(editor, row, column);
 
				if (c instanceof JTextComponent)
				{
					((JTextField)c).selectAll();
				}
 
				return c;
			}
		};
 
		JScrollPane scrollPane = new JScrollPane( table );
		getContentPane().add( scrollPane );
	}
 
	public static void main(String[] args)
	{
		TableEditCell frame = new TableEditCell();
		frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
		frame.pack();
		frame.setLocationRelativeTo( null );
		frame.setVisible(true);
	}
}

