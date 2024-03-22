package ch.laengu.boundry;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import ch.laengu.entity.Message;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Messaging {

    private ArrayList<String> invalidBlogContent = new ArrayList<>(Arrays.asList("bad", "invalid", "sucks"));
    private ArrayList<String> invalidCommentContent = new ArrayList<>(Arrays.asList("hate", "stupid", "sucks"));

    @Incoming("new-blog")
    @Outgoing("validated-blog")
    public Message validateBlog(Message message) {
        Log.info("Received on topic new-blog: " + message.toString());
        String text = message.getText();
        if (invalidBlogContent.contains(text)) {
            message.setValid(false);
        } else {
            message.setValid(true);
        }
        Log.info("Sending to topic validated-blog: " + message.toString());
        return message;
    }

    @Incoming("new-comment")
    @Outgoing("validated-comment")
    public Message validateComment(Message message) {
        Log.info("Received on topic new-comment: " + message.toString());
        String text = message.getText();
        if (invalidCommentContent.contains(text)) {
            message.setValid(false);
        } else {
            message.setValid(true);
        }
        Log.info("Sending to topic validated-comment: " + message.toString());
        return message;
    }
}
