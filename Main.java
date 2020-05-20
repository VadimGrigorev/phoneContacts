import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Contact> contacts = new ArrayList<>();
    private static String databaseName = "phonebook.db";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        File file = new File(databaseName);
        if(file.exists()) {
            loadFromDB();
        }

        while(true){
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            String action = scanner.nextLine();

            if(action.equals("add")){
                addContact();
            }
            else if(action.equals("list")){
                list();
            }
            else if(action.equals("search")){
                search();
            }
            else if(action.equals("count")){
                int num = contacts.size();
                System.out.printf("The phone book has %d records.\n", num);
            }
            else if(action.equals("exit")){
                break;
            }
        }

    }

    private static void loadFromDB() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(databaseName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);

        contacts = (List<Contact>) ois.readObject();
        System.out.println("open "+databaseName+"\n");
        ois.close();
    }
    private static void saveToDB() throws IOException{
        FileOutputStream fos = new FileOutputStream(databaseName,false);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(contacts);
        oos.close();
    }

    private static void search() throws IOException, ClassNotFoundException {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter search query: ");
            String toSearch = String.format("(?i).*%s.*",scanner.nextLine());
            List<Contact> contactsFound = new ArrayList<>();
            for (Contact c : contacts) {
                List<String> fieldsToCheck = c.getFields();
                for(String s : fieldsToCheck){
                    if(c.getField(s).matches(toSearch)){
                        contactsFound.add(c);
                    }
                }
            }
            System.out.printf("Found %d results", contactsFound.size());
            for (int i = 0; i < contactsFound.size(); i++) {
                System.out.printf("%d. %s\n", i, contactsFound.get(i).getField("name"));
            }

            System.out.print("\n[search] Enter action ([number], back, again): ");
            String action = scanner.nextLine();
            if(action.matches(".*\\d.*")){
                toRecord(Integer.parseInt(action)-1);
                break;
            }
            else if(action.equals("back")){
                break;
            }
            else if(action.equals("again")){
                continue;
            }
        }

    }

    private static void toRecord(int contactIndex) throws IOException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n[record] Enter action (edit, menu): ");
        String nextAction = scanner.nextLine();
        if (nextAction.equals("edit")) {
            editContact(contactIndex);
        }
    }

    private static void list() throws IOException, ClassNotFoundException {
        if(!contacts.isEmpty()) {
            for (int i = 0; i < contacts.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, contacts.get(i).getField("name"));
            }
            Scanner scanner = new Scanner(System.in);
            System.out.print("\n[list] Enter action ([number], back): ");
            String action = scanner.nextLine();

            if(action.matches(".*\\d.*")){
                System.out.println(contacts.get(Integer.parseInt(action)-1));
                toRecord(Integer.parseInt(action)-1);
            }
            else if(!action.equals("back")){
                System.out.println("going back to menu");
            }
        }
        else{
            System.out.println("no records\n");
        }
    }

    private static void editContact(int contactIndex) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        //read input
        System.out.print("Select a field (name, surname, birth, gender, number): ");
        String fieldToEdit = scanner.nextLine();
        Contact contactToEdit = contacts.get(contactIndex);
        System.out.printf("Enter %s: ", fieldToEdit);
        String newValue = scanner.nextLine();

        //edit value
        contactToEdit.modifyField(fieldToEdit, newValue);

        //output result
        System.out.println("Saved");
        System.out.println(contacts.get(contactIndex));
        saveToDB();
    }

    private static void addContact() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        //read input
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine();

        //create a contact
        Contact newContact;
        if(type.equals("organization")){
            newContact = new Organization();
        }
        else{
            newContact = new Person();
        }
        contacts.add(newContact);

        //fill the fields
        List<String> fieldsToFill = newContact.getFields();
        for(String s : fieldsToFill){
            System.out.printf("Enter the %s: ", s);
            String value = scanner.nextLine();
            newContact.modifyField(s, value);
        }

        System.out.println("The record added.\n");
        saveToDB();
    }
}
