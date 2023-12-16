/*
  Andrew Mainella
  Ms Latimer
  15 December 2023

  Assignment Five: Client information database
  Level: Gold
*/
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

//Class that stores client information
class UserItem {
  public String name;
  public int clientNumber;
  public String address;
  public String phoneNumber;
  public String email;
  public UserItem(String nameIn, int clientNumberIn, String addressIn, String phoneNumberIn, String emailIn) {
    name = nameIn;
    clientNumber = clientNumberIn;
    address = addressIn;
    phoneNumber = phoneNumberIn;
    email = emailIn;
  }
}

class PrintReport {
  //Find the longest value the original value is the value of name field header
  public static int getLongestName(UserItem[] searchedData) {
    int longest = 5;
    for (int index = 0; index < searchedData.length; index++) {
      if (searchedData[index].name.length() >= longest) {
        longest = searchedData[index].name.length() + 1;
      }
    }
    return longest;
  }
  //Find the longest value the original value is the value of clientfield header
  public static int getLongestClientNumber(UserItem[] searchedData) {
    int longest = 14;
    for (int index = 0; index < searchedData.length; index++) {
      if (Integer.toString(searchedData[index].clientNumber).length() >= longest) {
        longest = Integer.toString(searchedData[index].clientNumber).length() + 1;
      }
    }
    return longest;
  }
  //Find the longest value the original value is the value of address field header
  public static int getLongestAddress(UserItem[] searchedData) {
    int longest = 8;
    for (int index = 0; index < searchedData.length; index++) {
      if (searchedData[index].address.length() >= longest) {
        longest = searchedData[index].address.length() + 1;
      }
    }
    return longest;
  }
  //Find the longest value the original value is the value of phone field header
  public static int getLongestPhoneNumber(UserItem[] searchedData) {
    int longest = 13;
    for (int index = 0; index < searchedData.length; index++) {
      if (searchedData[index].phoneNumber.length() >= longest) {
        longest = searchedData[index].phoneNumber.length() + 1;
      }
    }
    return longest;
  }
  //Find the longest value the original value is the value of email field header
  public static int getLongestEmail(UserItem[] searchedData) {
    int longest = 4;
    for (int index = 0; index < searchedData.length; index++) {
      if (searchedData[index].email.length() >= longest) {
        longest = searchedData[index].email.length();
      }
    }
    return longest;
  }
  //Main Print returns report and sizes each coloumn correctly. This prints the report based on the value that is searched.
  public static void main(UserItem[] searchedData) {
    //Find the longest lengths
    int nameLength = getLongestName(searchedData);
    int clientLenth = getLongestClientNumber(searchedData);
    int addressLength = getLongestAddress(searchedData);
    int phoneLength = getLongestPhoneNumber(searchedData);
    int emailLength = getLongestEmail(searchedData);
    //Set the header
    String result = "-".repeat(nameLength + clientLenth + addressLength + emailLength + phoneLength + 12) + "\n";
    result += "| Name" + " ".repeat(nameLength - 4);
    result += "| Client Number" + " ".repeat(clientLenth - 13);
    result += "| Address" + " ".repeat(addressLength - 7);
    result += "| Phone Number" + " ".repeat(phoneLength - 12);
    result += "| Email" + " ".repeat(emailLength - 5) + " |\n";
    result += "-".repeat(nameLength + clientLenth + addressLength + emailLength + phoneLength + 12);
    //Iterate through the entries
    for (int index = 0; index < searchedData.length; index++) {
      result += "\n| " + searchedData[index].name + " ".repeat(nameLength - searchedData[index].name.length());
      result += "| " + searchedData[index].clientNumber + " ".repeat(clientLenth - Integer.toString(searchedData[index].clientNumber).length());
      result += "| " + searchedData[index].address + " ".repeat(addressLength - searchedData[index].address.length());
      result += "| " + searchedData[index].phoneNumber + " ".repeat(phoneLength - searchedData[index].phoneNumber.length());
      result += "| " + searchedData[index].email + " ".repeat(emailLength - searchedData[index].email.length()) + " |";
    }
    //End line
    result += "\n" + "-".repeat(nameLength + clientLenth + addressLength + emailLength + phoneLength + 12);
    //Print the result to the console
    System.out.println(result);
  }
}

class Index {
  //Main variables that are saved accross states
  public static boolean isAdd = false;
  public static boolean isRendered = false;
  public static UserItem[] bankedData = {new UserItem("Hello", 0, "Hello", "TESting", "Testing")};//new UserItem[0];
  public static UserItem[] searchedData = bankedData;
  public static String searchMode = "name"; //Values name, client, address, phone, email
  public static String search = "";
  //Adding data to the search results
  public static void pushDataSearch(UserItem item) {
    //new result is one greater than before
    UserItem[] newArray = new UserItem[searchedData.length + 1];
    //adding previous items
    for (int index = 0; index < searchedData.length; index++) {
      newArray[index] = searchedData[index];
    }
    //adding new items
    newArray[searchedData.length] = item;
    searchedData = newArray;
  }
  //
  public static void Search() {
    //Check if search is nothing. If so show all entries.
    if (search.equalsIgnoreCase("")) {
      searchedData = bankedData;
      return;
    }

    searchedData = new UserItem[0];
    for (int index = 0; index < bankedData.length; index++) {
      //Iterate through all results and find what is the current search mode.
      switch (searchMode) {
        case "name":
          if (bankedData[index].name.equalsIgnoreCase(search)) {
            pushDataSearch(bankedData[index]);
          }
          break;
        case "client":
          if (Integer.toString(bankedData[index].clientNumber).equalsIgnoreCase(search)) {
            pushDataSearch(bankedData[index]);
          }
          break;
        case "address":
          if (bankedData[index].address.equalsIgnoreCase(search)) {
            pushDataSearch(bankedData[index]);
          }
          break;
        case "phone":
          if (bankedData[index].phoneNumber.equalsIgnoreCase(search)) {
            pushDataSearch(bankedData[index]);
          }
          break;
        case "email":
          if (bankedData[index].email.equalsIgnoreCase(search)) {
            pushDataSearch(bankedData[index]);
          }
          break;
        default:
          break;
      }
      System.out.println(searchedData);
    }
  }
  //Convert Objects to a string array for use in table
  public static String[][] convertToData() {
    Search();
    String[][] result = new String[searchedData.length][5];
    for (int index = 0; index < searchedData.length; index++) {
      result[index][0] = searchedData[index].name;
    }
    for (int index = 0; index < searchedData.length; index++) {
      result[index][1] = Integer.toString(searchedData[index].clientNumber);
    }
    for (int index = 0; index < searchedData.length; index++) {
      result[index][2] = searchedData[index].address;
    }
    for (int index = 0; index < searchedData.length; index++) {
      result[index][3] = searchedData[index].phoneNumber;
    }
    for (int index = 0; index < searchedData.length; index++) {
      result[index][4] = searchedData[index].email;
    }
    return result;
  }
  //Add ne to banked data
  public static void pushData(UserItem item) {
    //Make the array one greater than before
    UserItem[] newArray = new UserItem[bankedData.length + 1];
    //add the pervious items
    for (int index = 0; index < bankedData.length; index++) {
      newArray[index] = bankedData[index];
    }
    //Add the last item
    newArray[bankedData.length] = item;
    bankedData = newArray;
    
  }
  public static void addData() {
    //Render frame
    JFrame frame = new JFrame();
    frame.setSize(300, 700);
    
    //Labels and fields are as you would expect.
    JLabel addDataLabel = new JLabel("Add Data");
    addDataLabel.setBounds(0, 0, 300, 50);
    frame.add(addDataLabel);

    JLabel nameLabel = new JLabel("Name");
    nameLabel.setBounds(0, 50, 300, 50);
    frame.add(nameLabel);

    JTextField nameField = new JTextField();
    nameField.setBounds(0, 100, 300, 50);
    frame.add(nameField);

    JLabel clientNumberLabel = new JLabel("Client Number");
    clientNumberLabel.setBounds(0, 150, 300, 50);
    frame.add(clientNumberLabel);

    JTextField clientNumberField = new JTextField();
    clientNumberField.setBounds(0, 200, 300, 50);
    frame.add(clientNumberField);

    JLabel addressLabel = new JLabel("Address");
    addressLabel.setBounds(0, 250, 300, 50);
    frame.add(addressLabel);

    JTextField addressField = new JTextField();
    addressField.setBounds(0, 300, 300, 50);
    frame.add(addressField);

    JLabel phoneNumberLabel = new JLabel("Phone Number");
    phoneNumberLabel.setBounds(0, 350, 300, 50);
    frame.add(phoneNumberLabel);

    JTextField phoneNumberField = new JTextField();
    phoneNumberField.setBounds(0, 400, 300, 50);
    frame.add(phoneNumberField);

    JLabel emailLabel = new JLabel("Email");
    emailLabel.setBounds(0, 450, 300, 50);
    frame.add(emailLabel);

    JTextField emailField = new JTextField();
    emailField.setBounds(0, 500, 300, 50);
    frame.add(emailField);

    //Button to add data changes on success or failed
    JButton addButton = new JButton("ADD DATA");
    addButton.addActionListener(new ActionListener(){  
      public void actionPerformed(ActionEvent e){
        try {
          pushData(new UserItem(nameField.getText(), Integer.parseInt(clientNumberField.getText()), addressField.getText(), phoneNumberField.getText(), emailField.getText()));
          addButton.setText("User Added");
        } catch (Exception error) {
          addButton.setText("Something Went Wrong");
        }
      }  
    });
    addButton.setBounds(0, 550, 300, 50);
    frame.add(addButton);

    //Go back to main page
    JButton backButton = new JButton("Back");
    backButton.addActionListener(new ActionListener(){  
      public void actionPerformed(ActionEvent e){  
        frame.setVisible(false);
        isAdd = false;
        isRendered = false;
      }  
    });
    backButton.setBounds(0, 600, 300, 50);
    frame.add(backButton);

    JPanel container = new JPanel();
    frame.add(container);

    frame.setVisible(true);
  }
  //overview holds main table and search functions.
  public static void mainOverview() {
    //Setting main frame
    JFrame frame = new JFrame();
    frame.setSize(450, 600);
    //Savings
    frame.setTitle("User Directory");

    //Building table
    String[] columnNames = { "Names", "Client Number", "Address", "Phone Number", "Email"};
    JTable table = new JTable();
    table.setModel(new DefaultTableModel(
      convertToData(),
      columnNames
    ));
    // adding it to JScrollPane
    JScrollPane jsp = new JScrollPane();
    jsp.setViewportView(table);
    JTabbedPane jtp = new JTabbedPane();
    jtp.add("User Table", jsp);
    jtp.setBounds(0, 0, 450, 300);
    frame.add(jtp);
    //Search Button
    JButton searchModeButton = new JButton("Search by " + searchMode);
    searchModeButton.addActionListener(new ActionListener(){  
      public void actionPerformed(ActionEvent e){ 
        switch (searchMode) {
          //name, client, address, phone, email
          //Changing the search mode in the order above.
          case "name":
            searchMode = "client";
            searchModeButton.setText("Search by client");
            break;
          case "client":
            searchMode = "address";
            searchModeButton.setText("Search by address");
            break;
          case "address":
            searchMode = "phone";
            searchModeButton.setText("Search by phone");
            break;
          case "phone":
            searchMode = "email";
            searchModeButton.setText("Search by email");
            break;
          case "email":
            searchMode = "name";
            searchModeButton.setText("Search by name");
            break;
          default:
            searchMode = "name";
            searchModeButton.setText("Search by name");
            break;
        }
        frame.setVisible(false);
        isRendered = false;
      }  
    });
    searchModeButton.setBounds(0, 300, 450, 50);
    frame.add(searchModeButton);

    //Search field and funcion
    JTextField searchField = new JTextField(search);
    searchField.setBounds(0, 350, 450, 50);
    frame.add(searchField);

    JButton searchButton = new JButton("SEARCH");
    searchButton.addActionListener(new ActionListener(){  
      public void actionPerformed(ActionEvent e){ 
        search = searchField.getText();
        Search();
        frame.setVisible(false);
        isRendered = false;
      }  
    });
    searchButton.setBounds(0, 400, 450, 50);
    frame.add(searchButton);

    //Button to go to add data page
    JButton addButton = new JButton("ADD DATA");
    addButton.addActionListener(new ActionListener(){  
      public void actionPerformed(ActionEvent e){  
        frame.setVisible(false);
        isAdd = true;
        isRendered = false;
      }  
    });
    addButton.setBounds(0, 450, 450, 50);
    frame.add(addButton);

    JButton printButton = new JButton("PRINT TO CONSOLE");
    printButton.addActionListener(new ActionListener(){  
      public void actionPerformed(ActionEvent e){  
        PrintReport.main(searchedData);
      }  
    });
    printButton.setBounds(0, 500, 450, 50);
    frame.add(printButton);

    //Render function
    JPanel container = new JPanel();
    frame.add(container);
    frame.setVisible(true);
  }
  //Main Method
  public static void main(String[] args) {
    //Choose program option holds code
    //Reason for loop and rendered state is to stop unnessesary rerenders.
    while (true) {
      //Checking if rendered
      if (!isRendered) {
        //Checking if in add data mode
        if (isAdd) {
          addData();
        } else {
          mainOverview();
        }
        isRendered = true;
      }
    }
  }
}