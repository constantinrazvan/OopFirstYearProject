package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public class CheckPropertiesFrame extends Frame implements ActionListener {

    //Buttons
    private Button buttonAdd, buttonUpdate, buttonDelete, buttonExit;
    private JRadioButton houseButton, apartmentButton; // Adaugare modificatori de acces

    //Panel
    @SuppressWarnings("unused")
    private Label address, surface, nrOfFloors, neighbourhood, nrOfRooms, owner; 
    
    @SuppressWarnings("unused")
    private TextField addressField, surfaceField, nrOfFloorsField, neighbourhoodField, nrOfRoomsField, ownerField;

    public CheckPropertiesFrame(String title) {
        super(title);
        setLayout(new FlowLayout());

        buttonAdd = new Button("Add");
        buttonUpdate = new Button("Update");
        buttonDelete = new Button("Delete");
        buttonExit = new Button("Exit");
        
        add(buttonAdd);
        add(buttonUpdate);
        add(buttonDelete);
        add(buttonExit);

        buttonAdd.addActionListener(this);
        buttonUpdate.addActionListener(this);
        buttonDelete.addActionListener(this);
        buttonExit.addActionListener(this);

        
        //Initializare variabile
        houseButton = new JRadioButton("House"); 
        apartmentButton = new JRadioButton("Apartment");

        houseButton.addActionListener(this);
        apartmentButton.addActionListener(this);

        address = new Label("Address"); 
        addressField = new TextField(20);

        surface = new Label("Surface"); 
        surfaceField = new TextField(20);

        nrOfRooms = new Label("Nr. of rooms");
        nrOfRoomsField = new TextField(20);
        nrOfRooms.setVisible(false);
        nrOfRoomsField.setVisible(false);

        nrOfFloors = new Label("Nr. of floors");
        nrOfFloorsField = new TextField(20);
        nrOfFloors.setVisible(false);
        nrOfFloorsField.setVisible(false);

        neighbourhood = new Label("Neighbourhood");
        neighbourhoodField = new TextField(20);
        neighbourhood.setVisible(false);
        neighbourhoodField.setVisible(false);

        owner = new Label("Owner");
        ownerField = new TextField(20);
        owner.setVisible(false);
        ownerField.setVisible(false);

        // Adaugare componentelor la fereastra
        add(houseButton);
        add(apartmentButton);
        add(address); 
        add(addressField);
        add(surface);
        add(surfaceField);
        add(nrOfFloors);
        add(nrOfFloorsField);
        add(neighbourhood);
        add(neighbourhoodField);
        add(nrOfRooms);
        add(nrOfRoomsField);
        add(owner);
        add(ownerField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonAdd) {

        } else if (e.getSource() == buttonUpdate) {
            System.out.println("Update");
        } else if (e.getSource() == buttonDelete) {
            System.out.println("Delete");
        } else if (e.getSource() == buttonExit) {
            System.exit(0);
        } else if (e.getSource() == houseButton) {
            houseButton.setSelected(true);
            apartmentButton.setSelected(false);
            nrOfFloors.setVisible(true);
            nrOfFloorsField.setVisible(true);
            neighbourhood.setVisible(true);
            neighbourhoodField.setVisible(true);

            nrOfRooms.setVisible(false);
            nrOfRoomsField.setVisible(false);
            owner.setVisible(false);
            ownerField.setVisible(false);
        } else if (e.getSource() == apartmentButton) {
            houseButton.setSelected(false);
            apartmentButton.setSelected(true);

            nrOfFloors.setVisible(false);
            nrOfFloorsField.setVisible(false);
            neighbourhood.setVisible(false);
            neighbourhoodField.setVisible(false);


            nrOfRooms.setVisible(true);
            nrOfRoomsField.setVisible(true);
            owner.setVisible(true);
            ownerField.setVisible(true);
        }
    }
}
