import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {

    public static void main(String[] args) {
        Mail mail = new Mail();
        EmailBuilder emailBuilder =
                new EmailBuilder(EmailProvider.NetEase_163)
                        .from(System.getenv("from"))
                        .token(System.getenv("token"))
                        .to(System.getenv("to"))
                        .content("Say something")
                        .title("Welcome Letter");
        mail.sendEmail(emailBuilder);
    }

    public void sendEmail(EmailBuilder builder) {
        // 收件人电子邮箱
        String to = builder.getReceiver();

        // 发件人电子邮箱
        String from = builder.getSender();


        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", builder.getHost());
        properties.put("mail.smtp.port", builder.getPort());
        properties.put("mail.smtp.auth", builder.authNeeded());


        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, builder.getToken()); //发件人邮件用户名、密码
            }
        });

        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            message.addRecipients(Message.RecipientType.TO,
                    new InternetAddress[]{new InternetAddress(to),
                            new InternetAddress(to)});
            // Set Subject: 头部头字段
            message.setSubject(builder.getTitle());

            // 设置消息体
            message.setText(builder.getContent());

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....from Java Mail");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
