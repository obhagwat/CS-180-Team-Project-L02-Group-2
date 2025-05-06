package Pages;

import Components.*;
import Components.Button;
import Database.Database;
import NetworkIO.Client;
import Objects.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MessagesPage extends Page {
    public MessagesPage(Client client, User currentUser) {
        super(client);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new Margin(20));
        panel.add(new BlueLabel("Your Messages", 24, 0));

        JPanel userListPanel = new JPanel();
        userListPanel.setLayout(new BoxLayout(userListPanel, BoxLayout.Y_AXIS));

        List<? extends User> others = currentUser instanceof Contractor
                ? Database.getInstance().getSolicitors()
                : Database.getInstance().getContractors();

        for (User other : others) {
            if (other.getUsername().equals(currentUser.getUsername())) continue;

            Button chatButton = new Button("Chat with " + other.getUsername(), e -> {
                new ChatPopup(client, currentUser, other);
            }, new Dimension(300, 40));
            userListPanel.add(chatButton);
            userListPanel.add(new Margin(10));
        }

        JScrollPane scrollPane = new JScrollPane(userListPanel);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        panel.add(scrollPane);

        panel.add(new Margin(20));

        // Start new chat dropdown + button
        JPanel newChatPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        newChatPanel.setOpaque(false);

        JComboBox<String> userDropdown = new JComboBox<>();
        for (User other : others) {
            if (!other.getUsername().equals(currentUser.getUsername())) {
                userDropdown.addItem(other.getUsername());
            }
        }

        Button startChatButton = new Button("Start New Chat", e -> {
            String selectedUsername = (String) userDropdown.getSelectedItem();
            if (selectedUsername != null) {
                User recipient = currentUser instanceof Contractor
                        ? Database.getInstance().getSolicitor(selectedUsername)
                        : Database.getInstance().getContractor(selectedUsername);
                new ChatPopup(client, currentUser, recipient);
            }
        }, new Dimension(200, 40));

        newChatPanel.add(userDropdown);
        newChatPanel.add(startChatButton);
        panel.add(newChatPanel);

        panel.add(new Margin(20));

        // Go back button
        Button goBack = new Button("Go Back to Home", e -> {
            if (currentUser instanceof Contractor) {
                GUIWindow.getInstance().switchPage(new ContractorHomePage(client));
            } else {
                GUIWindow.getInstance().switchPage(new SolicitorHomePage(client));
            }
        }, new Dimension(250, 40));

        panel.add(goBack);
        panel.add(new Margin(20));
    }
}
