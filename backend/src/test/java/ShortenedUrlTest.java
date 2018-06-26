import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marshall.eric.api.ShortenedUrl;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ShortenedUrlTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final ShortenedUrl person = new ShortenedUrl("https://health.gov/");

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/shortenedUrl.json"), ShortenedUrl.class));

        assertThat(MAPPER.writeValueAsString(person)).isEqualTo(expected);
    }

    // @Test
    // public void deserializesFromJSON() throws Exception {
    //     final ShortenedUrl person = new ShortenedUrl("https://health.gov/");
    //     System.out.println(person);
    //     assertThat(MAPPER.readValue(fixture("fixtures/shortenedUrl.json"), ShortenedUrl.class))
    //             .isEqualTo(person);
    // }
}