package controller;

import model.Class;

import java.util.Arrays;

public class J2EConverter {
    private String name;

    public J2EConverter(String name){
        this.name = name;
    }

    public String convertFromJavaClass(Class[] classes){
        var CYRILLIC_TO_LATIN = "Russian-Latin/BGN";
        StringBuilder builder = new StringBuilder();
        builder.append("<Structure>");
        builder.append("<KnowledgeBase>");
        builder.append("<Name>Конвертированная база</Name>");
        builder.append("<ShortName>Convertirovanya baza</ShortName>");
        builder.append("<Kind>0</Kind>" +
                "<Description></Description>" +
                "<Vars></Vars>");
        builder.append("<Templates>");
        Arrays.stream(classes).forEach(aClass -> {
            builder.append("<Template>");
            builder.append("<ID>"+ String.valueOf(aClass.getId()) +"</ID>");
            builder.append("<Name>"+ aClass.getTitle() +"</Name>");
            builder.append("<ShortName></ShortName>");
            builder.append("<Description></Description>" +
                    "<PackageName></PackageName>" +
                    "<RootPackageName></RootPackageName>");
            builder.append("<Slots>");
            aClass.getSlots().forEach(slot -> {
                builder.append("<Slot>");
                builder.append("<Name>"+ slot.getName() +"</Name>" +
                        "<ShortName></ShortName>" +
                        "<Description></Description>" +
                        "<Value>" + slot.getValue() +"</Value>" +
                        "<DataType>" + slot.getType() +"</DataType>" +
                        "<Constraint></Constraint>");
                builder.append("</Slot>");
            });
            builder.append("</Slots>");
            builder.append("</Template>");
        });
        builder.append("</Templates>");
        builder.append("</KnowledgeBase>");
        builder.append("</Structure>");
        return builder.toString();
    }
}
