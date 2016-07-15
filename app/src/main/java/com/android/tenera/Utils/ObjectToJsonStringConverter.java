package com.android.tenera.Utils;


public class ObjectToJsonStringConverter {
    /*public static String convert(Object object) {
        ObjectMapper mapper = new ObjectMapper();

        String jsonString = null;

        try {
            jsonString = mapper.writeValueAsString(object);
        } catch (JsonProcessingException ignored) {
            System.out.println(ignored.getMessage());
        }

        return jsonString;
    }

    public static <T> T getObject(String jsonValue, TypeReference<T> type) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        T object = null;
        try {
            object = mapper.readValue(jsonValue, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return object;
    }*/
}
