
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class WhatsappStatusTest {

    private ViewFactory viewFactory;
    private WhatsappStatus whatsappStatus;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        viewFactory = new ViewFactory();
        whatsappStatus = new WhatsappStatus(viewFactory);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testPublishTextStatus() {
        whatsappStatus.publishStatus("text", "Hello, this is my status!");
        String output = outContent.toString().toLowerCase();
        assertTrue(output.contains("displaying"), "Output should contain 'displaying'");
        assertTrue(output.contains("text"), "Output should contain 'text'");
    }

    @Test
    void testPublishImageStatus() {
        whatsappStatus.publishStatus("image", "photo.jpg");
        String output = outContent.toString().toLowerCase();
        assertTrue(output.contains("displaying"), "Output should contain 'displaying'");
        assertTrue(output.contains("image"), "Output should contain 'image'");
    }

    @Test
    void testPublishVideoStatus() {
        whatsappStatus.publishStatus("video", "video.mp4");
        String output = outContent.toString().toLowerCase();
        assertTrue(output.contains("displaying"), "Output should contain 'displaying'");
        assertTrue(output.contains("video"), "Output should contain 'video'");
    }

    @Test
    void testRemoveTextStatus() {
        whatsappStatus.publishStatus("text", "Hello, this is my status!");
        outContent.reset(); // Clear the output stream
        whatsappStatus.removeStatus("text", "Hello, this is my status!");
        String output = outContent.toString().toLowerCase();
        assertTrue(output.contains("removed"), "Output should contain 'removed'");
        assertTrue(output.contains("text"), "Output should contain 'text'");
    }

    @Test
    void testRemoveImageStatus() {
        whatsappStatus.publishStatus("image", "photo.jpg");
        outContent.reset(); // Clear the output stream
        whatsappStatus.removeStatus("image", "photo.jpg");
        String output = outContent.toString().toLowerCase();
        assertTrue(output.contains("removed"), "Output should contain 'removed'");
        assertTrue(output.contains("image"), "Output should contain 'image'");
    }

    @Test
    void testRemoveVideoStatus() {
        whatsappStatus.publishStatus("video", "video.mp4");
        outContent.reset(); // Clear the output stream
        whatsappStatus.removeStatus("video", "video.mp4");
        String output = outContent.toString().toLowerCase();
        assertTrue(output.contains("removed"), "Output should contain 'removed'");
        assertTrue(output.contains("video"), "Output should contain 'video'");
    }

    
    @Test
    void testFactoryPatternImplementation() {
        try {
            // Check if ViewFactory class exists
            Class<?> factoryClass = Class.forName("ViewFactory");
            assertNotNull(factoryClass, "ViewFactory class should exist");
            // Check if createView method exists
            Method createViewMethod = factoryClass.getMethod("createView", String.class, String.class);
            assertNotNull(createViewMethod, "createView method should exist in ViewFactory class");

            // Check if createView method returns a View instance for TextView
            ViewFactory viewFactory = new ViewFactory();
            View view = (View) createViewMethod.invoke(viewFactory, "text", "Sample content");
            assertTrue(view instanceof TextView, "createView method should return an instance of TextView for 'text' type");

            // Check if createView method returns a View instance for ImageView
            view = (View) createViewMethod.invoke(viewFactory, "image", "Sample content");
            assertTrue(view instanceof ImageView, "createView method should return an instance of ImageView for 'image' type");

            // Check if createView method returns a View instance for VideoView
            view = (View) createViewMethod.invoke(viewFactory, "video", "Sample content");
            assertTrue(view instanceof VideoView, "createView method should return an instance of VideoView for 'video' type");

        } catch (ClassNotFoundException e) {
            fail("ViewFactory class does not exist: " + e.getMessage());
        } catch (NoSuchMethodException e) {
            fail("createView method does not exist in ViewFactory class: " + e.getMessage());
        } catch (Exception e) {
            fail("Factory pattern implementation check failed: " + e.getMessage());
        }
    }
}


