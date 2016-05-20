import java.security.InvalidParameterException;

public class EmailBuilder {
    private final EmailProvider emailProvider;
    private String from;
    private String receiver;
    private String token;
    private String content;
    private String title;
    private boolean authNeeded;

    public EmailBuilder(EmailProvider provider) {
        authNeeded = true;
        this.emailProvider = provider;
    }

    public EmailBuilder from(String sender) {
        if (sender == null)
            throw new InvalidParameterException("发件人不存在!");

        this.from = sender;
        return this;
    }

    public EmailBuilder to(String receiver) {
        if (receiver == null)
            throw new InvalidParameterException("收件人不存在!");

        this.receiver = receiver;
        return this;
    }

    public EmailBuilder token(String token) {
        if (authNeeded && token == null)
            throw new InvalidParameterException("缺失口令,无法发送邮件!");
        this.token = token;
        return this;
    }

    public EmailBuilder content(String content) {
        this.content = content;
        return this;
    }

    public EmailBuilder title(String title) {
        this.title = title;
        return this;
    }

    public String getHost() {
        return emailProvider.host;
    }

    public int getPort() {
        return emailProvider.port;
    }

    public boolean authNeeded() {
        return authNeeded;
    }

    public String getToken() {
        return token;
    }

    public EmailProvider getEmailProvider() {
        return emailProvider;
    }

    public String getSender() {
        return from;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }
}
