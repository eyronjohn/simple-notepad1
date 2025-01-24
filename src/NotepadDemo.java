package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;

//Project: Simple Notepad using BorderLayout(Manager)
public class NotepadDemo {

    public static void main(String[] args) {

        Frame frame = new Frame("Notepad Demo");
        frame.setLayout(new BorderLayout());

        //header panel
        Panel headerPan = new Panel();
        headerPan.setBackground(Color.decode("#DDDDDD"));
        Label headerLb = new Label("Notepad Demo");
        headerLb.setFont(new Font("Times New Roman", Font.BOLD, 17));
        headerPan.add(headerLb);
        frame.add(headerPan, BorderLayout.NORTH);

        //textArea: allow user to type
        TextArea textArea = new TextArea();
        frame.add(textArea, BorderLayout.CENTER);
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        //footer panel: buttons are located
        Button save = new Button("Save");
        Button clear = new Button("Clear");
        save.setBackground(Color.decode("#0D92F4"));
        clear.setBackground(Color.decode("#0D92F4"));
        save.setFont(new Font("Times New Roman", Font.BOLD, 14));
        clear.setFont(new Font("Times New Roman", Font.BOLD, 14));
        save.setForeground(Color.white);
        clear.setForeground(Color.white);

        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(save);
        buttonPanel.add(clear);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                FileDialog fileDialog = new FileDialog(frame, "Save", FileDialog.SAVE);
                fileDialog.setVisible(true);
                String fileName = fileDialog.getFile();
                if (fileName != null) {
                    try {
                        FileWriter fileWriter = new FileWriter(fileDialog.getDirectory() + fileName);
                        String text = textArea.getText();
                        fileWriter.write(text);
                        fileWriter.close();
                        System.out.println("File saved successfully.");
                    } catch (IOException ed) {
                        ed.printStackTrace();
                    }
                } else {
                    // The user canceled the save operation; print a message to the console.
                    System.out.println("Save command cancelled by the user.");
                }
            }
        });

        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });

        //allows the program to be terminated
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //set the size of the frame + make it visible
        frame.setSize(700, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }//main
}//class