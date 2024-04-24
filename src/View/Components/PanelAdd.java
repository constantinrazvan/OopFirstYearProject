package View.Components;

import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.JRadioButton;

public class PanelAdd extends Panel {
    @SuppressWarnings("unused")
    private JRadioButton houseButton, apartmentButton;

    @SuppressWarnings("unused") 
    private Label address, surface, nrOfFloors, neighbourhood, nrOfRooms, owner; 

    @SuppressWarnings("unused")
    private TextField addressField, surfaceField, nrOfFloorsField, neighbourhoodField, nrOfRoomsField, ownerField;

    public PanelAdd() {
        houseButton = new JRadioButton("House"); 
        apartmentButton = new JRadioButton("Apartment");

        // Labelul si text field-ul pentru Owner
        owner = new Label("Owner");
        add(owner);

        ownerField = new TextField(20);
        add(ownerField);

        // Labelul si text field-ul pentru Adresa
        address = new Label("Address"); 
        add(address); 

        addressField = new TextField(20);
        add(addressField);

        // Labelul si text field-ul pentru Surface
        surface = new Label("Surface"); 
        add(surface);

        surfaceField = new TextField(20);
        add(surfaceField);

        // Labelul si text field-ul pentru Nr. of floors
        nrOfFloors = new Label("Nr. of floors");
        add(nrOfFloors);

        nrOfFloorsField = new TextField(20);
        add(nrOfFloorsField);

        // Labelul si text field-ul pentru Neighbourhood
        neighbourhood = new Label("Neighbourhood");
        add(neighbourhood);

    }

}
