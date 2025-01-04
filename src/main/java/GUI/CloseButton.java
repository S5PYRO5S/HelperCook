package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseButton extends JButton
{
    JTabbedPane tabbedPane;
    JPanel tabPanel;

    public CloseButton(final JTabbedPane tabbedPane, final JPanel tabPanel)
    {
        setIcon(Utils.getImageIcon("icons/close_tab_icon.png"));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setPreferredSize(new Dimension(20, 20));

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.remove(tabPanel);  // Remove the tab from the tabbedPane
            }
        });
    }
}
