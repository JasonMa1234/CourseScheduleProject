package ui;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

// Creat TableCellRender that enabled the text wrap in tablecells
class ChangingTableLinesCellRenderer extends JTextArea implements TableCellRenderer {
    public ChangingTableLinesCellRenderer() {
        setLineWrap(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        if (value != null) {
            setText(value.toString());
        } else {
            setText("");
        }
        return this;
    }

}

