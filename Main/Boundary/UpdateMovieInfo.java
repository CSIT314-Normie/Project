package Main.Boundary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Main.Controller.MovieController;

public class UpdateMovieInfo extends JFrame implements ActionListener {
    private ArrayList<String> userInfo;
    private String[] movieInfo; // movie name, movie description, movie status, movie duration
    private ArrayList<JTextField> textfieldList = new ArrayList<>();
    private String[] labelList = {"Movie Name: ", "Movie Description: ", "Movie Status: ", "Movie Duration: "};
    private String[] statusList = {"Available", "Unavailable"}; 
    private JComboBox<String> statusDropDown = new JComboBox<>(statusList);
    private ArrayList<String> modifiedMovieInfo;
    private String selectedStatus;
    
    JButton homeButton = new JButton("Home");
    JButton updateButton = new JButton("Update");

    public UpdateMovieInfo(ArrayList<String> userInfo, String[] movieInfo) {
         // Set up of the frame
         super("Update Movie Info - " + movieInfo[0]);
         setLayout(new FlowLayout());
         setSize(1035, 750);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setVisible(true);
         setResizable(false);
         setLocationRelativeTo(null);

        this.userInfo = userInfo;
        this.movieInfo = movieInfo;
        selectedStatus = movieInfo[2];

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setPreferredSize(new Dimension(1035, 50));

        topPanel.add(homeButton);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS)); 
        formPanel.setPreferredSize(new Dimension(750, 470));

        // add form title
        JLabel formTitle = new JLabel("Edit Movie Info: " + movieInfo[0]);
        formTitle.setFont(new Font("Serif", Font.PLAIN, 25));
        formPanel.add(formTitle);

        // add form fields
        for (int i = 0; i < labelList.length; i++) {
            JPanel fieldPanel = new JPanel(new BorderLayout());

            JLabel label = new JLabel(labelList[i]);
            label.setFont(new Font("Serif", Font.PLAIN, 18));
             
            // movie status is a dropdown
            if (i == 2) {
                statusDropDown.setSelectedIndex(movieInfo[i].equals("Available") ? 0 : 1);
                fieldPanel.add(statusDropDown, BorderLayout.EAST);

            } else {
                JTextField field = new JTextField(movieInfo[i], 20);
                field.setPreferredSize(new Dimension(200, 20)); 
                textfieldList.add(field);
                fieldPanel.add(field, BorderLayout.EAST);
            }
            
        
            fieldPanel.add(label, BorderLayout.WEST);
            fieldPanel.setBorder(BorderFactory.createEmptyBorder(25, 0, 25, 0));
            

            formPanel.add(fieldPanel);
        }

        // add listener for status dropdown
        statusDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedStatus = statusDropDown.getSelectedItem().toString();
                System.out.println("[+] Selected Status: " + selectedStatus);
            }
        });

        // add update button
        formPanel.add(updateButton);

        add(topPanel); 
        add(formPanel);

        homeButton.addActionListener(this);
        updateButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Home":
                dispose();
                new Home(userInfo);
                break;

            case "Update":
                MovieController movieController = new MovieController();
                // update movie info
                modifiedMovieInfo = new ArrayList<>();
                modifiedMovieInfo.add(0, textfieldList.get(0).getText()); // add movie name
                modifiedMovieInfo.add(1, textfieldList.get(1).getText()); // add movie description
                modifiedMovieInfo.add(2, selectedStatus); // add movie status
                modifiedMovieInfo.add(3, textfieldList.get(2).getText()); // add movie duration

                // check if all fields are filled
                if (modifiedMovieInfo.contains("")) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields"); 
                } else {
                    if (movieController.updateMovieInfo(modifiedMovieInfo, movieInfo[0])) {
                        JOptionPane.showMessageDialog(null, "Movie info updated successfully");
                        // dispose();
                        // new Home(userInfo);
                    } else {
                        JOptionPane.showMessageDialog(null, "Movie info update failed");
                    }
                } 
                modifiedMovieInfo.clear();
                break;
        }
    }
    
}
