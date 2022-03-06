package utilities;

import pojos.Appointment;
import pojos.Registrant;

import java.io.BufferedWriter;
import java.io.FileWriter;

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



}