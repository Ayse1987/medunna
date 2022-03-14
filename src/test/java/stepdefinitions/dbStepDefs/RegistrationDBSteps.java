package stepdefinitions.dbStepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.DatabaseUtility;

import java.util.ArrayList;
import java.util.List;

import static utilities.ReadTxt.getSSNIDs;
import static utilities.WriteToTxt.saveRegistrantDataDB;

public class RegistrationDBSteps {


        List< Object> allDBSSNs;

         @Given("user creates a connection with db using {string} , {string} and {string}")
         public void userCreatesAConnectionWithDbUsingAnd(String url, String username, String password) {
            DatabaseUtility.createConnection(url, username, password);
             System.out.println("connection ok");

         }
        @Given("user sends the query to DB and gets the column data {string} and {string}")
        public void user_sends_the_query_to_db_and_gets_the_column_data_and(String query, String columnName) {
            allDBSSNs = DatabaseUtility.getColumnData(query, columnName);
            System.out.println(allDBSSNs);
        }
        @Given("user saves the DB records to correspondent files")
        public void user_saves_the_db_records_to_correspondent_files() {
            saveRegistrantDataDB(allDBSSNs);
        }
        @Then("user validates DB Registrant data")
        public void user_validates_db_registrant_data() {
            List<String> expectedSSNIDs = new ArrayList<>();
            expectedSSNIDs.add("662-32-9756");
            expectedSSNIDs.add("369-93-5260");

            List<String> actualData = getSSNIDs();//All records

            Assert.assertTrue(actualData.containsAll(expectedSSNIDs));


        }


        @Given("get the registrants SSN numbers using {string} and {string}")
        public void getTheRegistrantsSSNNumbersUsingAnd(String query, String column) {

            allDBSSNs=DatabaseUtility.getColumnData(query,column);
            System.out.println(allDBSSNs.size());
            for(int i=0; i<allDBSSNs.size();i++){
           // System.out.println(registrantCurrentSSNs.get(i).toString().trim());
            }


    }

    @Then("validate registrants SSNs contains {string}")
    public void validateRegistrantsSSNsContains(String expectedSSN) {
        Assert.assertTrue(allDBSSNs.contains(expectedSSN));
    }

    @Then("user closes connection")
    public void userClosesConnection() {
        DatabaseUtility.closeConnection();
    }


}
