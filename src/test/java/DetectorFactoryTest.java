import com.cybozu.labs.langdetect.LangDetectException;
import org.junit.Test;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.Detector;

import static org.junit.Assert.assertEquals;
public class DetectorFactoryTest {

    @Test
    public void shouldRecognizeStringAsEnglish() {
        try {
            DetectorFactory.loadProfile("/Users/joshuahowell/Desktop/Twitter Guard/app/langdetect-03-03-2014/profiles");
            Detector detector = DetectorFactory.create();
            String englishString = "en";
            String text = "This string is in english.";

            detector.append(text);
            String language = detector.detect();

            assertEquals(englishString, language);
        } catch (LangDetectException ex) {
            System.out.println("There was a LangDetect Exception");
            System.out.println(ex.getMessage());
            System.out.println(ex.getCode());
        }
    }
}
