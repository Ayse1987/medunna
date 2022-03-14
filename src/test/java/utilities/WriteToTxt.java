package utilities;

import pojos.Appointment;
import pojos.Registrant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class WriteToTxt {



    public static void saveRegistrantData(Registrant registrant){

        try{

            //src/resources/testdata/Registrantdata.txt
            FileWriter fileWriter = new FileWriter(ConfigurationReader.getProperty("registrant_filename"), true);

            BufferedWriter writer = new BufferedWriter(fileWriter);


            writer.append(registrant+"\n");


            writer.close();


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void saveAppointmentData(Appointment appointment){

        try{

            //src/resources/testdata/Registrantdata.txt
            FileWriter fileWriter = new FileWriter(ConfigurationReader.getProperty("appointment_filename"), true);

            BufferedWriter writer = new BufferedWriter(fileWriter);


            writer.append(appointment+"\n");


            writer.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void saveApiRegistrantData(Registrant registrant){

        try{

            //src/resources/testdata/Registrantdata.txt
            FileWriter fileWriter = new FileWriter(ConfigurationReader.getProperty("api_registrant_data"), true);

            BufferedWriter writer = new BufferedWriter(fileWriter);


            writer.append(registrant+"\n");


            writer.close();


        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public static void saveRegistrantDataDB(List<Object> SSNIds){
        try{
            //src/resources/testdata/Registrantdata.txt
            FileWriter fileWriter = new FileWriter(ConfigurationReader.getProperty("database_registrant_data"), false);

            BufferedWriter writer = new BufferedWriter(fileWriter);

            for(Object eachSSN: SSNIds) {
                writer.append(eachSSN + ",\n");
            }

            writer.close();



        }catch (Exception e){
            e.printStackTrace();
        }


    }

    //=========
    public static void saveAllRegistrantsData(Registrant[] registrants){

        try{


            FileWriter fileWriter = new FileWriter(ConfigurationReader.getProperty("all_registrants_filename"), true);

            BufferedWriter writer = new BufferedWriter(fileWriter);


            writer.append(registrants+"\n");

            for(Object w: registrants) {
                writer.append(w + ",\n");
            }

            writer.close();


        }catch (Exception e){
            e.printStackTrace();
        }

    }






}