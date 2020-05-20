import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Person extends ContactType implements Contact {
    public final static long serialVersionUID = 1L;
    private String surname;
    private String gender;
    private String birthDate;

    public Person(){
        super();
    }

    public Person(String number, String name, String surname, String gender, String birthDate) {
        super(number, name+" "+surname);
        this.surname = surname;
        super.setTimeCreated(LocalDateTime.now());
        super.setTimeLastModified(LocalDateTime.now());
        if(birthDate.matches("(\\d{4}.{1}\\d{2}.{1}\\d{2})|(\\d{2}.{1}\\d{2}.{1}\\d{4})")){
            this.birthDate = birthDate;
        }
        else{
            this.birthDate = null;
        }
        if(gender.matches("[MF]")){
            this.gender = gender;
        }
        else{
            this.gender = "[no data]";
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        super.setTimeLastModified(LocalDateTime.now());
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if(gender.matches("[MF]")) {
            this.gender = gender;
            super.setTimeLastModified(LocalDateTime.now());
        }
        else{
            this.gender = "[no data]";
            System.out.println("Wrong format of gender!");
        }
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        if(birthDate.matches("(\\d{4}.{1}\\d{2}.{1}\\d{2})|(\\d{2}.{1}\\d{2}.{1}\\d{4})")){
            this.birthDate = birthDate;
            super.setTimeLastModified(LocalDateTime.now());
        }
        else{
            System.out.println("Wrong format of date!");
        }
    }

    @Override
    public String toString() {
        if(birthDate != null) {
            return String.format("Name: %s\n" +
                            "Surname: %s\n" +
                            "Birth date: %s\n" +
                            "Gender: %s\n" +
                            "Number: %s\n" +
                            "Time created: %s\n" +
                            "Time last edit: %s", super.getName(), surname, birthDate, getGender(),
                    super.getNumber(), super.getTimeCreated(), super.getTimeLastModified());
        }
        else{
            return String.format("Name: %s\n" +
                            "Surname: %s\n" +
                            "Birth date: %s\n" +
                            "Gender: %s\n" +
                            "Number: %s\n" +
                            "Time created: %s\n" +
                            "Time last edit: %s", super.getName(), surname, "[no data]", getGender(),
                    super.getNumber(), super.getTimeCreated(), super.getTimeLastModified());
        }
    }

    @Override
    public List<String> getFields() {
        return List.of("name", "surname", "birth date", "gender", "number");
    }

    @Override
    public String getFieldsAsString() {
        return "name, surname, birth date, gender, number";
    }

    @Override
    public void modifyField(String fieldName, String value) {
        if(getFields().contains(fieldName)){
            if(fieldName.equals("name")){
                super.setName(value);
            }
            else if(fieldName.equals("number")){
                super.setNumber(value);
            }
            else if(fieldName.equals("surname")){
                setSurname(value);
            }
            else if(fieldName.equals("gender")){
                setGender(value);
            }
            else if(fieldName.equals("birth date")){
                setBirthDate(value);
            }
        }
    }

    @Override
    public String getField(String fieldName) {
        if(fieldName.equals("name")){
            return super.getName()+" "+getSurname();
        }
        else if(fieldName.equals("number")){
            return super.getNumber();
        }
        else if(fieldName.equals("surname")){
            return getSurname();
        }
        else if(fieldName.equals("gender")){
            return getGender();
        }
        else if(fieldName.equals("birth date")){
            return getBirthDate();
        }
        else{
            return "[no such field]";
        }
    }
}
