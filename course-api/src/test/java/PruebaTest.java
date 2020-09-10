import io.javabrains.springbootstarter.hello.Numbers;
import io.javabrains.springbootstarter.topic.Topic;
import io.javabrains.springbootstarter.topic.TopicService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PruebaTest {

    TopicService topicService;

    @ParameterizedTest
    @ValueSource(strings = {"Integration", "Junit", "Spring"})
    void addTopics(String topics) {
        Topic topic = new Topic();
        topic.setName(topics);
        topicService = new TopicService();
        topicService.addTopic(topic);
        List<Topic> result = topicService.getAllTopics();
        assertTrue(result.size() > 0);
    }



    @ParameterizedTest
    @CsvSource(value = {"test:test", "tEst:test", "Java:java"}, delimiter = ':')
    void toUpperCase_ShouldGenerateTheExpectedUppercaseValue(String input, String expected) {
        String actualValue = input.toLowerCase();
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "Test.csv", numLinesToSkip = 1)//cargar archivo
    void csvInFile(String input, String expected) {
        String actualValue = input.toLowerCase();
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvSource({"Isaac,,Newton,Isaac Newton", "Charles,Robert,Darwin,Charles Robert Darwin"})
    void fullName_ShouldGenerateTheExpectedFullName(ArgumentsAccessor argumentsAccessor) {
        String firstName = argumentsAccessor.getString(0);
        String middleName = (String) argumentsAccessor.get(1);
        String lastName = argumentsAccessor.get(2, String.class);
        String expectedFullName = argumentsAccessor.getString(3);

        String person = firstName + middleName + lastName;
        assertEquals(expectedFullName, person);
    }

    @Test
    void test_exception() {

        Exception exception = assertThrows(
                ArithmeticException.class,
                () -> divide(1, 0));

        assertEquals("/ by zero", exception.getMessage());

        assertTrue(exception.getMessage().contains("zero"));

    }

    int divide(int input, int divide) {
        return input / divide;
    }
}
