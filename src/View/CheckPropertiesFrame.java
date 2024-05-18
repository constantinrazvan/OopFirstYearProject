package View;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Models.Apartment;
import Models.House;
import Models.Property;

public class CheckPropertiesFrame extends Frame implements ActionListener, ItemListener {

    // Buttons
    private Button buttonAdd, buttonUpdate, buttonDelete, buttonExit;
    private Checkbox houseButton, apartmentButton;

    // Labels and TextFields
    private Label address, surface, nrOfFloors, neighbourhood, nrOfRooms, owner, errorLabel;
    private TextField addressField, surfaceField, nrOfFloorsField, neighbourhoodField, nrOfRoomsField, ownerField;

    private ArrayList<Property> properties = new ArrayList<>();
    private List list;

    public CheckPropertiesFrame(String title) {
        super(title);
        setLayout(new BorderLayout(10, 10));
        loadPropertiesFromFile();

        // Left panel for the list with padding
        Panel leftPanel = new Panel(new BorderLayout());
        list = new List();
        for (Property property : properties) {
            list.add(property.toString());
        }

        // Add padding to the list using a Swing JPanel with EmptyBorder
        JPanel listWrapper = new JPanel(new BorderLayout());
        listWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        listWrapper.add(list, BorderLayout.CENTER);
        leftPanel.add(listWrapper, BorderLayout.CENTER);

        // Set background color to white
        leftPanel.setBackground(Color.WHITE);
        listWrapper.setBackground(Color.WHITE);

        // Right panel for the buttons, checkboxes, labels, and textfields
        Panel rightPanel = new Panel();
        rightPanel.setLayout(null); 
        rightPanel.setBackground(Color.WHITE); 

        // CRUD buttons
        buttonAdd = new Button("Add");
        buttonUpdate = new Button("Update");
        buttonDelete = new Button("Delete");
        buttonExit = new Button("Exit");

        // Set button positions and sizes
        buttonAdd.setBounds(10, 10, 80, 30);
        buttonUpdate.setBounds(100, 10, 80, 30);
        buttonDelete.setBounds(190, 10, 80, 30);
        buttonExit.setBounds(280, 10, 80, 30);

        // Add buttons to the right panel
        rightPanel.add(buttonAdd);
        rightPanel.add(buttonUpdate);
        rightPanel.add(buttonDelete);
        rightPanel.add(buttonExit);

        // Create checkboxes
        houseButton = new Checkbox("House");
        apartmentButton = new Checkbox("Apartment");

        // Set checkbox positions
        houseButton.setBounds(10, 50, 100, 30);
        apartmentButton.setBounds(120, 50, 100, 30);

        // Add checkboxes to the right panel
        rightPanel.add(houseButton);
        rightPanel.add(apartmentButton);

        // Add item listeners to checkboxes to clear errors
        houseButton.addItemListener(this);
        apartmentButton.addItemListener(this);

        // Create labels and text fields
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

        // Add text field listeners to clear errors
        addTextListener(addressField);
        addTextListener(surfaceField);
        addTextListener(nrOfFloorsField);
        addTextListener(neighbourhoodField);
        addTextListener(nrOfRoomsField);
        addTextListener(ownerField);

        // Set positions for labels and text fields
        address.setBounds(10, 90, 100, 30);
        addressField.setBounds(120, 90, 150, 30);
        surface.setBounds(10, 130, 100, 30);
        surfaceField.setBounds(120, 130, 150, 30);
        nrOfFloors.setBounds(10, 170, 100, 30);
        nrOfFloorsField.setBounds(120, 170, 150, 30);
        neighbourhood.setBounds(10, 210, 100, 30);
        neighbourhoodField.setBounds(120, 210, 150, 30);
        nrOfRooms.setBounds(10, 170, 100, 30);
        nrOfRoomsField.setBounds(120, 170, 150, 30);
        owner.setBounds(10, 210, 100, 30);
        ownerField.setBounds(120, 210, 150, 30);

        // Add labels and text fields to the right panel
        rightPanel.add(address);
        rightPanel.add(addressField);
        rightPanel.add(surface);
        rightPanel.add(surfaceField);
        rightPanel.add(nrOfFloors);
        rightPanel.add(nrOfFloorsField);
        rightPanel.add(neighbourhood);
        rightPanel.add(neighbourhoodField);
        rightPanel.add(nrOfRooms);
        rightPanel.add(nrOfRoomsField);
        rightPanel.add(owner);
        rightPanel.add(ownerField);

        // Add panels to the frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        // Add listeners
        buttonAdd.addActionListener(this);
        buttonUpdate.addActionListener(this);
        buttonDelete.addActionListener(this);
        buttonExit.addActionListener(this);
        list.addItemListener(this);

        // Error label
        errorLabel = new Label("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setBounds(10, 250, 300, 30);
        rightPanel.add(errorLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == buttonExit) {
                savePropertiesToFile();
                System.exit(0);
            } else if (e.getSource() == buttonAdd) {
                if (!houseButton.getState() && !apartmentButton.getState()) {
                    throw new IllegalStateException("No checkbox selected, please select a property type.");
                }
                validateFields();
                if (houseButton.getState()) {
                    String address = addressField.getText();
                    int surface = Integer.parseInt(surfaceField.getText());
                    int nrOfFloors = Integer.parseInt(nrOfFloorsField.getText());
                    String neighbourhood = neighbourhoodField.getText();
                    House house = new House(address, surface, nrOfFloors, neighbourhood);
                    properties.add(house);
                    list.add(house.toString());

                    clearFields();
                } else if (apartmentButton.getState()) {
                    String address = addressField.getText();
                    int surface = Integer.parseInt(surfaceField.getText());
                    int nrOfRooms = Integer.parseInt(nrOfRoomsField.getText());
                    String owner = ownerField.getText();
                    Apartment apartment = new Apartment(address, surface, nrOfRooms, owner);
                    properties.add(apartment);
                    list.add(apartment.toString());

                    clearFields();
                }
            } else if (e.getSource() == buttonUpdate) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex < 0) {
                    throw new IndexOutOfBoundsException("No item selected, please select an item to update.");
                }
                validateFields();
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

                list.replaceItem(selectedProperty.toString(), selectedIndex);
                clearFields();
            } else if (e.getSource() == buttonDelete) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex < 0) {
                    throw new IndexOutOfBoundsException("No item selected, please select an item to delete.");
                }
                properties.remove(selectedIndex);
                list.remove(selectedIndex);
            }
        } catch (IllegalStateException | IllegalArgumentException | IndexOutOfBoundsException ex) {
            errorLabel.setText(ex.getMessage());
        }
    }

    private void validateFields() {
        if (addressField.getText().isEmpty() || surfaceField.getText().isEmpty() ||
            (houseButton.getState() && (nrOfFloorsField.getText().isEmpty() || neighbourhoodField.getText().isEmpty())) ||
            (apartmentButton.getState() && (nrOfRoomsField.getText().isEmpty() || ownerField.getText().isEmpty()))) {
            throw new IllegalArgumentException("Field cannot be empty, please provide information.");
        }
        if (Integer.parseInt(surfaceField.getText()) < 0 ||
            (houseButton.getState() && Integer.parseInt(nrOfFloorsField.getText()) < 0) ||
            (apartmentButton.getState() && Integer.parseInt(nrOfRoomsField.getText()) < 0)) {
            throw new IllegalArgumentException("Negative values are not allowed.");
        }
    }

    private void clearFields() {
        addressField.setText("");
        surfaceField.setText("");
        nrOfFloorsField.setText("");
        neighbourhoodField.setText("");
        nrOfRoomsField.setText("");
        ownerField.setText("");
    }

    private void addTextListener(TextField textField) {
        textField.addTextListener(new TextListener() {
            @Override
            public void textValueChanged(TextEvent e) {
                if (!addressField.getText().isEmpty() && !surfaceField.getText().isEmpty() &&
                    (!houseButton.getState() || (!nrOfFloorsField.getText().isEmpty() && !neighbourhoodField.getText().isEmpty())) &&
                    (!apartmentButton.getState() || (!nrOfRoomsField.getText().isEmpty() && !ownerField.getText().isEmpty()))) {
                    errorLabel.setText("");
                }
            }
        });
    }

    private void itemStateChangedEvent(ItemEvent event) {
        if (event.getSource() == houseButton) {
            if (houseButton.getState()) {
                // Show house fields
                nrOfFloors.setVisible(true);
                nrOfFloorsField.setVisible(true);
                neighbourhood.setVisible(true);
                neighbourhoodField.setVisible(true);

                // Hide apartment fields
                nrOfRooms.setVisible(false);
                nrOfRoomsField.setVisible(false);
                owner.setVisible(false);
                ownerField.setVisible(false);
            } else {
                // Hide house fields
                nrOfFloors.setVisible(false);
                nrOfFloorsField.setVisible(false);
                neighbourhood.setVisible(false);
                neighbourhoodField.setVisible(false);
            }
        } else if (event.getSource() == apartmentButton) {
            if (apartmentButton.getState()) {
                // Show apartment fields
                nrOfRooms.setVisible(true);
                nrOfRoomsField.setVisible(true);
                owner.setVisible(true);
                ownerField.setVisible(true);

                // Hide house fields
                nrOfFloors.setVisible(false);
                nrOfFloorsField.setVisible(false);
                neighbourhood.setVisible(false);
                neighbourhoodField.setVisible(false);
            } else {
                // Hide apartment fields
                nrOfRooms.setVisible(false);
                nrOfRoomsField.setVisible(false);
                owner.setVisible(false);
                ownerField.setVisible(false);
            }
        } else if (event.getSource() == list && event.getStateChange() == ItemEvent.SELECTED) {
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

                    // Show apartment fields
                    nrOfRooms.setVisible(true);
                    nrOfRoomsField.setVisible(true);
                    owner.setVisible(true);
                    ownerField.setVisible(true);

                    // Hide house fields
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

                    // Show house fields
                    nrOfFloors.setVisible(true);
                    nrOfFloorsField.setVisible(true);
                    neighbourhood.setVisible(true);
                    neighbourhoodField.setVisible(true);

                    // Hide apartment fields
                    nrOfRooms.setVisible(false);
                    nrOfRoomsField.setVisible(false);
                    owner.setVisible(false);
                    ownerField.setVisible(false);
                }
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        itemStateChangedEvent(e);
    }

    private void savePropertiesToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("properties.txt"))) {
            for (Property property : properties) {
                if (property instanceof House) {
                    House house = (House) property;
                    writer.println("House," + house.getAddress() + "," + house.getSurface() + "," + house.getNrOfFloors() + "," + house.getNeighbourhood());
                } else if (property instanceof Apartment) {
                    Apartment apartment = (Apartment) property;
                    writer.println("Apartment," + apartment.getAddress() + "," + apartment.getSurface() + "," + apartment.getNrOfRooms() + "," + apartment.getOwner());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPropertiesFromFile() {
        properties.clear();
        try (Scanner scanner = new Scanner(new File("properties.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts[0].equals("House")) {
                    String address = parts[1];
                    int surface = Integer.parseInt(parts[2]);
                    int nrOfFloors = Integer.parseInt(parts[3]);
                    String neighbourhood = parts[4];
                    House house = new House(address, surface, nrOfFloors, neighbourhood);
                    properties.add(house);
                } else if (parts[0].equals("Apartment")) {
                    String address = parts[1];
                    int surface = Integer.parseInt(parts[2]);
                    int nrOfRooms = Integer.parseInt(parts[3]);
                    String owner = parts[4];
                    Apartment apartment = new Apartment(address, surface, nrOfRooms, owner);
                    properties.add(apartment);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



}
