package gui;

import ex3.SingleMulticastClientServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDate;

public class ChatFrame extends JFrame implements MessageClient {

    private final SingleMulticastClientServer clientServer;
    private JTextArea chatArea;
    private String localHost;
    private String extendedLocalhost;
    private JTextArea inputArea;
    private boolean firstMessage;

    public ChatFrame() throws IOException {
        super("Chat");

        Dimension dim = new Dimension(800, 800);

        setPreferredSize(dim);

        JPanel mainPanel = setupMainPanel();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(mainPanel);
        this.pack();
        setLocationRelativeTo(null);


        clientServer = new SingleMulticastClientServer(InetAddress.getByName("224.0.0.1"),
                7654);

        clientServer.addListener(this);
        localHost = InetAddress.getLocalHost().getHostName();
        extendedLocalhost = localHost + ".fil.univ-lille1.fr";
        firstMessage = true;
    }

    private JPanel setupMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());


        JPanel chatPanel = setupChatPanel();
        JPanel inputPanel = setupInputPanel();

        mainPanel.add(chatPanel, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);
        return mainPanel;
    }

    private JPanel setupChatPanel() {
        JPanel chatPanel = new JPanel();


        chatArea = new JTextArea();
        JScrollPane scrollChat = new JScrollPane(chatArea);
        scrollChat.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                e.getAdjustable().setValue(e.getAdjustable().getValue());
            }
        });

        scrollChat.setPreferredSize(new Dimension(775, 700));

        chatPanel.add(scrollChat);
        return chatPanel;
    }

    private JPanel setupInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1, 2));

        inputArea = new JTextArea();
        inputArea.requestFocus();
        inputArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                boolean isEnter = keyEvent.getKeyChar() == '\n';
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER || isEnter) {
                    String input = inputArea.getText();
                    input = input.substring(0, input.length() - 1);

                    sendMessage(input);
                }
            }
        });

        JButton enterButton = new JButton("Send");
        enterButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sendMessage(inputArea.getText());
            }
        });

        inputPanel.add(inputArea);
        inputPanel.add(enterButton);

        return inputPanel;
    }

    private void sendMessage(String inputText) {
        clientServer.send(inputText);
        inputArea.setText("");
    }

    public static void main(String[] args) throws IOException {
        ChatFrame chatFrame = new ChatFrame();
        chatFrame.setVisible(true);
        chatFrame.run();
    }

    private void run() {
        clientServer.run();
    }

    @Override
    public void receiveMessage(String text, String host) {
        if (localHost.equals(host) || extendedLocalhost.equals(host)) {
            host = "you";
        }
        host += ":";

        String append = String.format("%-30s %s", host, text);

        if (firstMessage) {
            chatArea.setText(append);
            firstMessage = false;
        } else {
            chatArea.append("\n" + append);
        }
    }
}
