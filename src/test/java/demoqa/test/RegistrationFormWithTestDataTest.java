package demoqa.test;

import demoqa.pages.RegistrationFormPage;
import org.junit.jupiter.api.Test;

import static demoqa.test.TestData.*;
import static java.lang.String.format;

public class RegistrationFormWithTestDataTest extends TestBase {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

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