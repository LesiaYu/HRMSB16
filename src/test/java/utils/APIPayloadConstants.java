package utils;

import org.json.JSONObject;

public class APIPayloadConstants {
    public static String createEmployeePayload(){
        String createEmployeePayload="{\n" +
                "  \"emp_firstname\": \"Lesia\",\n" +
                "  \"emp_lastname\": \"Firadi\",\n" +
                "  \"emp_middle_name\": \"ms\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"1987-05-20\",\n" +
                "  \"emp_status\": \"happy\",\n" +
                "  \"emp_job_title\": \"QA\"\n" +
                "}";
        return createEmployeePayload;
    }

    public static String createEmployeeJsonPayload(){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname","Lesia");
        obj.put("emp_lastname","Firadi");
        obj.put("emp_middle_name","ms");
        obj.put("emp_gender","F");
        obj.put("emp_birthday","1987-05-20");
        obj.put("emp_status","happy");
        obj.put("emp_job_title","QA");
        return obj.toString();
    }

    public static String createEmployeeJsonPayloadDynamic(String fn, String ln, String mn, String gender,
                         String dob, String status, String jobTitle){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname",fn);
        obj.put("emp_lastname",ln);
        obj.put("emp_middle_name",mn);
        obj.put("emp_gender",gender);
        obj.put("emp_birthday",dob);
        obj.put("emp_status",status);
        obj.put("emp_job_title",jobTitle);
        return obj.toString();
    }

    public static String updateEmployeePayload(String empID){
        String updateEmployeePayload="{\n" +
                "        \"employee_id\": \""+empID+"\",\n" +
                "        \"emp_firstname\": \"LesiaSofiVer\",\n" +
                "        \"emp_middle_name\": \"yes\",\n" +
                "        \"emp_lastname\": \"Dirana\",\n" +
                "        \"emp_birthday\": \"2013-10-01\",\n" +
                "        \"emp_gender\": \"F\",\n" +
                "        \"emp_job_title\": \"Family\",\n" +
                "        \"emp_status\": \"Happy\"\n" +
                "}";
        return updateEmployeePayload;
    }

    public static String updateEmployeeJsonPayload(String empID){
        JSONObject obj= new JSONObject();
        obj.put("employee_id", empID);
        obj.put("emp_firstname", "LesiaSofiVer");
        obj.put("emp_middle_name", "yes");
        obj.put("emp_lastname", "Dirana");
        obj.put("emp_birthday", "2013-10-01");
        obj.put("emp_gender", "F");
        obj.put("emp_job_title", "Family");
        obj.put("emp_status", "Happy");
              return obj.toString();
    }

    public static String updateEmployeeJsonPayloadDynamic(String empId, String fn, String mn, String ln, String eb, String gender, String jT, String status){
        JSONObject obj= new JSONObject();
        obj.put("employee_id", empId);
        obj.put("emp_firstname", fn);
        obj.put("emp_middle_name", mn);
        obj.put("emp_lastname", ln);
        obj.put("emp_birthday", eb);
        obj.put("emp_gender", gender);
        obj.put("emp_job_title", jT);
        obj.put("emp_status", status);
        return obj.toString();
    }
}
