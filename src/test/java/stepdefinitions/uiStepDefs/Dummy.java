package stepdefinitions.uiStepDefs;

import com.github.javafaker.Faker;

public class Dummy {

    public static void main(String[] args) {

        String name="Ibrahim";
        Faker faker=new Faker();
        name=faker.name().fullName();

        System.out.println(name);


    }
}
