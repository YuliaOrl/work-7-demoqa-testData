package demoqa.test;

import com.github.javafaker.Faker;
import demoqa.pages.RegistrationFormPage;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;

public class RegistrationFormWithFakerTest extends TestBase {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            gender = "Female",
            mobileNumber = faker.phoneNumber().subscriberNumber(10),
            day = faker.number().numberBetween(10, 29) + "",
            month = "June",
            year = faker.number().numberBetween(2004, 2008) + "",
            subjects = "History",
            hobby_1 = "Sports",
            hobby_2 = "Reading",
            hobby_3 = "Music",
            filePathResources = "src/test/resources/",
            fileName = "file.txt",
            currentAddress = faker.address().fullAddress(),
            state = "NCR",
            city = "Delhi";

    @Test
    void fillFormTest () {
        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setNumber(mobileNumber)
                .setBirthDate(day, month, year)
                .setSubjects(subjects)
                .setHobby(hobby_1)
                .setHobby(hobby_2)
                .setHobby(hobby_3)
                .uploadFile(filePathResources + fileName)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .pressSubmit();

        String expectedFullName = format("%s %s", firstName, lastName);
        String expectedDateOfBirth = format("%s %s,%s", day, month, year);
        String expectedHobby = format("%s, %s, %s", hobby_1, hobby_2, hobby_3);
        String expectedStateAndCity = format("%s %s", state, city);

        registrationFormPage.checkResultsTableVisible()
                .checkResults("Student Name", expectedFullName)
                .checkResults("Student Email", email)
                .checkResults("Gender", gender)
                .checkResults("Mobile", mobileNumber)
                .checkResults("Date of Birth", expectedDateOfBirth)
                .checkResults("Subjects", subjects)
                .checkResults("Hobbies", expectedHobby)
                .checkResults("Picture", fileName)
                .checkResults("Address", currentAddress)
                .checkResults("State and City", expectedStateAndCity);
    }
}