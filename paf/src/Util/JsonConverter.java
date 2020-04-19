package Util;

import DTO.Doctor;
import DTO.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import DTO.Department;

import java.util.List;


public class JsonConverter {

    private final Gson gson;

    public JsonConverter() {

        gson = new GsonBuilder().create();
    }

    public String convertToJson(List<Doctor> doctors) {

        JsonArray jarray = gson.toJsonTree(doctors).getAsJsonArray();


        return jarray.toString();
    }

    public String convertToJson(Doctor doctor) {

        JsonObject jsonObject = gson.toJsonTree(doctor).getAsJsonObject();

        return jsonObject.toString();
    }

    public Doctor convertToObject(String jsonString){
       return gson.fromJson(jsonString, Doctor.class);
    }
    public String convertToJson(Response response) {

        JsonObject jsonObject = gson.toJsonTree(response).getAsJsonObject();

        return jsonObject.toString();
    }

    public String convertToJsonDepartment(List<Department> departments) {

        JsonArray jarray = gson.toJsonTree(departments).getAsJsonArray();


        return jarray.toString();
    }
}