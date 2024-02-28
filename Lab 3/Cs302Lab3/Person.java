import java.io.Serializable;

public class Person implements Serializable, Comparable<Person> {

   //feilds
   private String name;
   private String address;
   private String  phoneNumber;

   //constructor
   Person(String setName, String setAddress, String setPhoneNumber){
      name = setName;
      address = setAddress;
      phoneNumber = setPhoneNumber;
   }

   //getters
   public String getName(){
      return name;
   }

   public String getAddress(){
      return address;
   }
   public String getPhoneNumber(){
      return phoneNumber;
   }

   //setters

   public void setName(String setName){
      name = setName;
   }

   public void setAddress(String setAddress){
      address = setAddress;
   }
   public void setPhoneNumber(String setPhoneNumber){
      phoneNumber = setPhoneNumber;
   }
   public String toString(){
      String str = name + "\n" + address + "\n" +phoneNumber;
      return str;
   }

   //for compareable
   @Override
   public int compareTo(Person o) {
      return this.name.compareTo(o.name);
   }
}
