package controller;

import model.Class;
import model.Slot;

import java.util.ArrayList;
import java.util.Arrays;

public class A2JConverter {
    private String[] fileData;
    private int clausesLine;

    public A2JConverter(String[] data) throws Exception {
        this.fileData = data;
        clausesLine = findClausesStatement();

    }

    private int findClausesStatement() throws Exception { ;
        for (int i = 0; i < fileData.length; i++) {
            if(fileData[i].equals("clauses")){
                return i;
            }
        }
        throw new Exception("Bad file format. Can't find clauses statement");
    }

    public Class[] convertClasses(){
        ArrayList<Class> list = new ArrayList<Class>();
        Arrays.stream(fileData).forEach(s -> {
            if(s.startsWith("j")){
                Class cs = new Class();
                StringBuffer buffer = new StringBuffer(s);
                buffer.replace(0,3,"");
                buffer.replace(buffer.indexOf(")"), buffer.length(), "");
                String[] statements = buffer.toString().split(",");
                cs.setId(Integer.parseInt(statements[0]));
                cs.setTitle(statements[1].replaceAll("\"", ""));
                buffer = new StringBuffer(statements[12].replaceAll("\"", ""));
                while (buffer.indexOf("\\n") != -1){
                    buffer.replace(buffer.indexOf("\\n"), buffer.indexOf("\\n") + 2, "|" );
                }
                cs.setSlots(new ArrayList<Slot>());
                String[] stringSlots = buffer.toString().split("\\|");
                for (String ss:stringSlots) {
                    String[] dataSlot = ss.split(":");
                    Slot slot = new Slot();
                    if(dataSlot.length > 1){
                        slot.setType(dataSlot[1]);
                    }
                    slot.setName(dataSlot[0]);
                    slot.setValue(null);
                    cs.getSlots().add(slot);
                }
                list.add(cs);
            }
        });
        return list.toArray(Class[]::new);
    }

}
