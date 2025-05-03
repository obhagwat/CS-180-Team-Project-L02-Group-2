// CreateContractPopup.java
package Pages;

import NetworkIO.Client;
import javax.swing.*;
import java.time.LocalDateTime;

public class CreateContractPopup {
    public CreateContractPopup(Client client) {
        JTextField title = new JTextField();
        JTextArea description = new JTextArea(5, 20);
        JTextField deadline = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Title:"));
        panel.add(title);
        panel.add(new JLabel("Description:"));
        panel.add(new JScrollPane(description));
        panel.add(new JLabel("Deadline:"));
        panel.add(deadline);

        int result = JOptionPane.showConfirmDialog(null, panel, "Create New Contract", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                LocalDateTime deadlineTime = LocalDateTime.parse(deadline.getText());
                client.getSolicitor().postContract(title.getText(), deadlineTime, null);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid deadline format.");
            }
        }
    }
}

