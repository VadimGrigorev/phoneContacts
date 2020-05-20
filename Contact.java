import java.util.List;

public interface Contact {
    public List<String> getFields();
    public String getFieldsAsString();
    public void modifyField(String fieldName, String value);
    public String getField(String fieldName);

}
