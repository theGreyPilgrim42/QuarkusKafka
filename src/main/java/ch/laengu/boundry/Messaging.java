package ch.laengu.boundry;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import ch.laengu.entity.Message;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Messaging {

    private ArrayList<String> invalidBlogContent = new ArrayList<>(Arrays.asList("bad", "invalid", "sucks"));
    private ArrayList<String> invalidCommentContent = new ArrayList<>(Arrays.asList("hate", "stupid", "sucks"));

    @Incoming("new-blog")
    @Outgoing("validated-blog")
    public Uni<Message> validateBlog(Message message) {
        return Uni.createFrom().item(message).map(msg -> {
            String text = msg.getText();
            for (String invalidString : invalidBlogContent) {
                if (text.contains(invalidString)) {
                    msg.setValid(false);
                    return msg;
                } 
            }
            msg.setValid(true);
            return msg;
        });
    }

    @Incoming("new-comment")
    @Outgoing("validated-comment")
    public Uni<Message> validateComment(Message message) {
        return Uni.createFrom().item(message).map(msg -> {
            String text = msg.getText();
            for (String invalidString : invalidCommentContent) {
                if (text.contains(invalidString)) {
                    msg.setValid(false);
                    return msg;
                } 
            }
            msg.setValid(true);
            return msg;
        });
    }
}
