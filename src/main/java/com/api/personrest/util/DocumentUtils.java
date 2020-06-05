package com.api.personrest.util;

public class DocumentUtils {

    public static String unmaskDocument(String document){
        return document.replaceAll("[.-]", "");
    }
}
