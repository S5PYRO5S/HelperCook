package GUI;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel
{
    public HomePanel ()
    {
        setName("Home");
        setLayout (new BorderLayout());
        JLabel placeholderLabel = new JLabel("Home");
        placeholderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(placeholderLabel);
    }
}
