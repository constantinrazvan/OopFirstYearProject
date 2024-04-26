package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import Models.Apartment;
import Models.House;
import Models.Property;

public class CheckPropertiesFrame extends Frame implements ActionListener, ItemListener {

    //Buttons
    private Button buttonAdd, buttonUpdate, buttonDelete, buttonExit;
    private Checkbox houseButton, apartmentButton; // Adaugare modificatori de acces

    //Panel
    private Label address, surface, nrOfFloors, neighbourhood, nrOfRooms, owner; 
    
    private TextField addressField, surfaceField, nrOfFloorsField, neighbourhoodField, nrOfRoomsField, ownerField;

    ArrayList<Property> properties = new ArrayList<>();
   
    private List list;

    public CheckPropertiesFrame(String title) {
        super(title);
        setLayout(new FlowLayout());

        House house = new House("Adresa 1", 200, 2, "Cartier 1");
        Apartment apartment = new Apartment("Adresa 2", 3, 4, "Owner 1");

        properties.add(house);
        properties.add(apartment);

        list = new List();
        for (Property property : properties) {
            list.add(property.toString());
        }
        list.setBounds(20, 10, 300, 500);

        buttonAdd = new Button("Add");
        buttonUpdate = new Button("Update");
        buttonDelete = new Button("Delete");
        buttonExit = new Button("Exit");
        
        add(list);
        add(buttonAdd);
        add(buttonUpdate);
        add(buttonDelete);
        add(buttonExit);

        buttonAdd.addActionListener(this);
        buttonUpdate.addActionListener(this);
        buttonDelete.addActionListener(this);
        buttonExit.addActionListener(this);
        
        //Initializare variabile
        houseButton = new Checkbox("House"); 
        apartmentButton = new Checkbox("Apartment");

        houseButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    address.setVisible(true);
                    addressField.setVisible(true);

                    surface.setVisible(true);
                    surfaceField.setVisible(true);

                    nrOfFloors.setVisible(true);
                    nrOfFloorsField.setVisible(true);

                    neighbourhood.setVisible(true);
                    neighbourhoodField.setVisible(true);
                } else if (event.getStateChange() == ItemEvent.DESELECTED) {
                    address.setVisible(false);
                    addressField.setVisible(false);

                    surface.setVisible(false);
                    surfaceField.setVisible(false);

                    nrOfFloors.setVisible(false);
                    nrOfFloorsField.setVisible(false);

                    neighbourhood.setVisible(false);
                    neighbourhoodField.setVisible(false);
                }
            }
        });

        apartmentButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    address.setVisible(true);
                    addressField.setVisible(true);

                    surface.setVisible(true);
                    surfaceField.setVisible(true);
                    
                    nrOfRooms.setVisible(true);
                    nrOfRoomsField.setVisible(true);

                    owner.setVisible(true);
                    ownerField.setVisible(true);
                } else if (event.getStateChange() == ItemEvent.DESELECTED) {
                    address.setVisible(false);
                    addressField.setVisible(false);

                    surface.setVisible(false);
                    surfaceField.setVisible(false);

                    nrOfRooms.setVisible(false);
                    nrOfRoomsField.setVisible(false);

                    owner.setVisible(false);
                    ownerField.setVisible(false);
                }
            }
        });

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


        list.addItemListener(this);

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
        if (e.getSource() == buttonExit) {
            System.exit(0);
        } else if (e.getSource() == buttonAdd) {
            if (houseButton.getState()) {

                address.setVisible(true);
                addressField.setVisible(true);

                surface.setVisible(true);
                surfaceField.setVisible(true);


                String address = addressField.getText();
                int surface = Integer.parseInt(surfaceField.getText());
                int nrOfFloors = Integer.parseInt(nrOfFloorsField.getText());
                String neighbourhood = neighbourhoodField.getText();
                House house = new House(address, surface, nrOfFloors, neighbourhood);
                properties.add(house);
                list.add(house.toString());

                addressField.setText(null);
                surfaceField.setText(null);
                nrOfFloorsField.setText(null);
                neighbourhoodField.setText(null);
            } else if (apartmentButton.getState()) {

                
                address.setVisible(true);
                addressField.setVisible(true);

                surface.setVisible(true);
                surfaceField.setVisible(true);


                String address = addressField.getText();
                int surface = Integer.parseInt(surfaceField.getText());
                int nrOfRooms = Integer.parseInt(nrOfRoomsField.getText());
                String owner = ownerField.getText();
                Apartment apartment = new Apartment(address, surface, nrOfRooms, owner);
                properties.add(apartment);
                list.add(apartment.toString());

                addressField.setText(null);
                surfaceField.setText(null);
                nrOfRoomsField.setText(null);
                ownerField.setText(null);
            }
        } else if (e.getSource() == buttonUpdate) {
            int selectedIndex = list.getSelectedIndex();
            if (selectedIndex >= 0) {
                Property selectedProperty = properties.get(selectedIndex);
                selectedProperty.setAddress(addressField.getText());
                selectedProperty.setSurface(Integer.parseInt(surfaceField.getText()));
                if (selectedProperty instanceof House) {
                    House house = (House) selectedProperty;
                    house.setNrOfFloors(Integer.parseInt(nrOfFloorsField.getText()));
                    house.setNeighbourhood(neighbourhoodField.getText());
                } else if (selectedProperty instanceof Apartment) {
                    Apartment apartment = (Apartment) selectedProperty;
                    apartment.setNrOfRooms(Integer.parseInt(nrOfRoomsField.getText()));
                    apartment.setOwner(ownerField.getText());
                }

                list.remove(selectedIndex);
                list.add(selectedProperty.toString(), selectedIndex);

                addressField.setText(null);
                surfaceField.setText(null);
                nrOfFloorsField.setText(null);
                neighbourhoodField.setText(null);
                nrOfRoomsField.setText(null);
                ownerField.setText(null);
            }

        } else if (e.getSource() == buttonDelete) {
            Property item = properties.get(list.getSelectedIndex());
            properties.remove(item);
            list.remove(item.toString());
        }
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getSource() == list && event.getStateChange() == ItemEvent.SELECTED) {
            int index = list.getSelectedIndex();
            if (index >= 0) {
                Property property = properties.get(index);
                addressField.setText(property.getAddress());
                surfaceField.setText(String.valueOf(property.getSurface()));

                if (property instanceof Apartment) {
                    apartmentButton.setState(true);
                    houseButton.setState(false);
                    Apartment apartment = (Apartment) property;
                    nrOfRoomsField.setText(String.valueOf(apartment.getNrOfRooms()));
                    ownerField.setText(apartment.getOwner());

                    address.setVisible(true);
                    addressField.setVisible(true);

                    surface.setVisible(true);
                    surfaceField.setVisible(true);
                    
                    nrOfRooms.setVisible(true);
                    nrOfRoomsField.setVisible(true);

                    owner.setVisible(true);
                    ownerField.setVisible(true);

                    nrOfFloors.setVisible(false);
                    nrOfFloorsField.setVisible(false);

                    neighbourhood.setVisible(false);
                    neighbourhoodField.setVisible(false);
                } else if (property instanceof House) {
                    apartmentButton.setState(false);
                    houseButton.setState(true);
                    House house = (House) property;
                    nrOfFloorsField.setText(String.valueOf(house.getNrOfFloors()));
                    neighbourhoodField.setText(house.getNeighbourhood());

                    address.setVisible(true);
                    addressField.setVisible(true);

                    surface.setVisible(true);
                    surfaceField.setVisible(true);

                    nrOfFloors.setVisible(true);
                    nrOfFloorsField.setVisible(true);

                    neighbourhood.setVisible(true);
                    neighbourhoodField.setVisible(true);

                    nrOfRooms.setVisible(false);
                    nrOfRoomsField.setVisible(false);

                    owner.setVisible(false);
                    ownerField.setVisible(false);
                }
            }
        }
    }
}