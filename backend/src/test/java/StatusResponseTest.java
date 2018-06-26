import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marshall.eric.api.ShortenedUrl;
import com.marshall.eric.api.StatusResponse;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StatusResponseTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final StatusResponse person = new StatusResponse(true, new ShortenedUrl("https://health.gov/"));

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/statusResponse.json"), StatusResponse.class));

        assertThat(MAPPER.writeValueAsString(person)).isEqualTo(expected);
    }

    // @Test
    // public void deserializesFromJSON() throws Exception {
    //     final StatusResponse statusResponse = new StatusResponse(true, new ShortenedUrl("https://health.gov/"));
    //     assertThat(MAPPER.readValue(fixture("fixtures/statusResponse.json"), StatusResponse.class))
    //             .isEqualTo(statusResponse);
    // }
}