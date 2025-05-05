package Pages;

import NetworkIO.Client;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class CreateContractPopup {
    public CreateContractPopup(Client client, Runnable refreshAction) {
        JTextField title = new JTextField();
        JTextArea description = new JTextArea(5, 20);
        JTextField deadline = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Title:"));
        panel.add(title);
        panel.add(new JLabel("Description:"));
        panel.add(new JScrollPane(description));
        panel.add(new JLabel("Deadline: (Format \"yyyy-MM-dd HH:mm:ss\")"));
        panel.add(deadline);
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        int result = JOptionPane.showConfirmDialog(null, panel, "Create New Contract", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(deadline.getText(), formatter);
                client.getSolicitor().postContract(title.getText(), description.getText(), dateTime, new ArrayList<>());
                int lastIndex = client.getSolicitor().getContractsSolicited().size() - 1;
                System.out.println("New Contract Created: " + client.getSolicitor().getContractsSolicited().get(lastIndex));
                Database.Database.getInstance().serializeDatabase();

                refreshAction.run();

            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Invalid deadline format.");
            }
        }
    }
}

