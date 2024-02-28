import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Iterator;

public class PhoneBookDriver implements Serializable {
   //feilds
   private BinarySearchTree<Person> bst;
   private JPanel mainPanel;
   private JPanel entryPanel;
   private JLabel phoneBookLabel;
   private JLabel nameLabel;
   private JLabel addressLabel;
   private JLabel phoneNumberLabel;
   private JTextField nameTextField;
   private JTextField addressTextField;
   private JTextField phoneNumberTextField;
   private JPanel buttonPanel;
   private JButton addContactButton;
   private JButton removeContactButton;
   private JButton modifyContactButton;
   private JButton contactInformationButton;
   private JButton printEntirePhoneBookButton;
   private JButton resetEntriesButton;
   private JButton resetDisplayPanelButton;
   private JButton savePhoneBookToFileButton;
   private JButton openOldPhoneBookButton;
   private JTextArea textArea1;
   private JTextField textField1;

   /*
   public PhoneBookDriver(){
       bst = new BinarySearchTree<>();
   }
   */

   //constructor
   public PhoneBookDriver() throws IOException{
      bst = new BinarySearchTree<>();
   
      //add method
      addContactButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //save feild strings
               String strName = nameTextField.getText();
               String strAddress = addressTextField.getText();
               String strPhoneNumber = phoneNumberTextField.getText();
            
            //create person with feilds
               Person newPerson = new Person(strName, strAddress, strPhoneNumber);
            
            //add to the bst
               bst.add(newPerson);
            
            //print that it was added
               textArea1.setText(textArea1.getText()+"\n"+strName+"\n"+strAddress+"\n"+strPhoneNumber);
            }
         });
      //remove method
      removeContactButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //get name to remove
               String strName = nameTextField.getText();
            
            //create iterator with tree
               Iterator<Person> itr = bst.iterator();
            
            //find the name object and remove that object
               while(itr.hasNext()){
                  Person temp = itr.next();
                  if(temp.getName().equals(strName)) {
                     bst.remove(temp);
                     textArea1.setText(strName + " was removed\n");
                     break;
                  }
               }
            }
         });
      //modify method
      modifyContactButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //get fields
               String strName = nameTextField.getText();
               String strPhoneNumber = phoneNumberTextField.getText();
               String strAddress = addressTextField.getText();
               Iterator<Person> itr = bst.iterator();
            
            //find object and set the feilds accordingly
               while(itr.hasNext()){
                  Person temp = itr.next();
                  if(temp.getName().equals(strName)) {
                     temp.setPhoneNumber(strPhoneNumber);
                     temp.setAddress(strAddress);
                     textArea1.setText(textArea1.getText()+"\n"+strName+" contact modified\n");
                     break;
                  }
               }
            }
         });
      //method to get contact info
      contactInformationButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //get name
               String strName = nameTextField.getText();
               Iterator<Person> itr = bst.iterator();
            //find object and then print phone number
               while(itr.hasNext()){
                  Person temp = itr.next();
                  if(temp.getName().equals(strName)) {
                     textArea1.setText(textArea1.getText()+"\n"+strName+"s" +
                        " contact information: "+temp.getPhoneNumber());
                     break;
                  }
               }
            }
         });
      //reset boxes method
      resetEntriesButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //set the text to empty strings
               nameTextField.setText("");
               addressTextField.setText("");
               phoneNumberTextField.setText("");
            }
         });
      //reset the display
      resetDisplayPanelButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //set the textarea to an empty string
               textArea1.setText("");
            }
         });
      //print contents of the tree method
      printEntirePhoneBookButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
               Iterator<Person> itr = bst.iterator();
            // loop through the tree and call the tostring and print on screen
               while(itr.hasNext()) {
                  Person temp = itr.next();
                  String str = temp.toString();
                  textArea1.setText(textArea1.getText() + str+ "\n");
               }
            }
         });
      //save to file method
      savePhoneBookToFileButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try {
               //save tree to file
                  FileOutputStream fileOutputStream = new FileOutputStream("bst.txt");
                  ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                  objectOutputStream.writeObject(bst);
                  fileOutputStream.close();
               }catch (IOException ex) {
                  throw new RuntimeException(ex);
               }
            
            }
         });
      //open old book
      openOldPhoneBookButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //open file get tree and set the bst to the tree
               try {
                  FileInputStream fileInputStream = new FileInputStream("bst.txt");
                  ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                  bst = (BinarySearchTree<Person>) objectInputStream.readObject();
                  fileInputStream.close();
               }catch (IOException ex) {
                  throw new RuntimeException(ex);
               }catch (ClassNotFoundException ex) {
                  throw new RuntimeException(ex);
               }
            }
         });
   }

   //main
   public static void main(String[] args) throws IOException{
      //set up jframe
      JFrame frame = new JFrame("Phone Book App");
      frame.setContentPane(new PhoneBookDriver().mainPanel);
      frame.pack();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
}
