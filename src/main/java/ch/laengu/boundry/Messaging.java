package ch.laengu.boundry;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import ch.laengu.entity.TextMessage;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Messaging {

    private ArrayList<String> invalidStrings = new ArrayList<>(Arrays.asList("bad", "invalid", "sucks"));

    @Incoming("new-blog")
    @Outgoing("validated-text")
    public TextMessage validateBlog(TextMessage message) {
        Log.info("Received on topic new-blog: " + message.toString());
        String text = message.getText();
        message.setValid(true);
        for (String invalidString : invalidStrings) {
            if (text.contains(invalidString)) {
                message.setValid(false);
            }
        }
        Log.info("Sending to topic validated-text: " + message.toString());
        return message;
    }
}
