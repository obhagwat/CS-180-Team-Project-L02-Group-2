package Pages;

import Database.Database;
import NetworkIO.Client;
import Objects.*;

import javax.swing.*;
import java.awt.*;

public class ChatPopup extends JFrame {
    private JTextArea messageArea;
    private JTextField inputField;
    private Chat chat;

    public ChatPopup(Client client, User currentUser, User otherUser) {
        setTitle("Chat with " + otherUser.getUsername());
        setSize(500, 400);
        setLayout(new BorderLayout());

        //Ensure chat exists
        chat = Database.getInstance().getChatBetweenUsers(currentUser, otherUser);
        if (chat == null) {
            chat = new Chat(currentUser, otherUser);
            Database.getInstance().addChat(chat);
        }

        //Message display area
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        add(scrollPane, BorderLayout.CENTER);
        refreshMessages();

        //Message input field + send
        inputField = new JTextField();
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> {
            String text = inputField.getText().trim();
            if (!text.isEmpty()) {
                Database.getInstance().sendMessage(text, currentUser, otherUser);
                inputField.setText("");
                refreshMessages();
            }
        });

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(inputPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void refreshMessages() {
        StringBuilder sb = new StringBuilder();
        for (Message msg : chat.getMessages()) {
            sb.append(msg.toString()).append("\n");
        }
        messageArea.setText(sb.toString());
    }
}
