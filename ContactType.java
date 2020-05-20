import java.io.Serializable;
import java.time.LocalDateTime;

public class ContactType implements Serializable {
    public final static long serialVersionUID = 1L;
    private String number;
    private String name;
    private LocalDateTime timeCreated;
    private LocalDateTime timeLastModified;

    public ContactType() {
        this.number = "[no number]";
        this.timeCreated = LocalDateTime.now();
        this.timeLastModified = LocalDateTime.now();
    }

    public ContactType(String number, String name) {
        this.name = name;
        if(number.matches("\\+?\\d?.{0,2}\\d{3}.{0,2}\\d{3}.{0,2}\\d{2}.?\\d{2}")) {
            this.number = number;
        }
        else{
            this.number = "[no number]";
        }
        this.timeCreated = LocalDateTime.now();
        this.timeLastModified = LocalDateTime.now();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if(number.matches("\\+?\\d?.{0,2}\\d{3}.{0,2}\\d{3}.{0,2}\\d{2}.?\\d{2}")) {
            this.timeLastModified = LocalDateTime.now();
            this.number = number;
        }
        else{
            System.out.println("Wrong number format!");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.timeLastModified = LocalDateTime.now();
        this.name = name;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDateTime getTimeLastModified() {
        return timeLastModified;
    }

    public void setTimeLastModified(LocalDateTime timeLastModified) {
        this.timeLastModified = timeLastModified;
    }
}
