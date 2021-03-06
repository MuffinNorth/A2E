package model;

public class Slot {
    private String name;
    private String type;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        if(type == null){
            return "";
        }
        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public String getValue() {
        if(value == null){
            return "";
        }
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
