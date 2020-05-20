import java.time.LocalDateTime;
import java.util.List;

public class Organization extends ContactType implements Contact {
    public final static long serialVersionUID = 1L;

    private String address;

    public Organization(){
        super();
    }

    public Organization(String number, String name, String address) {
        super(number, name);
        this.address = address;
        super.setTimeCreated(LocalDateTime.now());
        super.setTimeLastModified(LocalDateTime.now());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        super.setTimeLastModified(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return String.format("Organization name: %s\n" +
                "Address: %s\n" +
                "Number: %s\n" +
                "Time created: %s\n" +
                "Time last edit: %s", super.getName(), address, super.getNumber(),
                super.getTimeCreated(), super.getTimeLastModified());
    }

    @Override
    public List<String> getFields() {
        return List.of("name", "address", "number");
    }

    @Override
    public String getFieldsAsString() {
        return "name, address, number";
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
            else if(fieldName.equals("address")){
                address = value;
            }
        }
    }

    @Override
    public String getField(String fieldName) {
        if(getFields().contains(fieldName)){
            if(fieldName.equals("name")){
                return super.getName();
            }
            else if(fieldName.equals("number")){
                return super.getNumber();
            }
            else if(fieldName.equals("address")){
                return address;
            }
        }
        return "[no such field]";
    }
}
