import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {

    public static void main(String [] args)
    {
        Mail mail = new Mail();
        mail.sendEmailUseQQ();
    }

    public void sendEmailUseQQ(){
        // 收件人电子邮箱
        String to = "422549477@qq.com";

        // 发件人电子邮箱
        String from = "594702170@qq.com";

        // 指定发送邮件的主机为 localhost
        String host = "smtp.qq.com";  //QQ 邮件服务器

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        String token = System.getenv("token");
        if(token==null) {
            System.err.println("缺失口令,无法发送邮件!");
            return;
        }

        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("594702170@qq.com", token); //发件人邮件用户名、密码
            }
        });

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
//            message.addRecipient(Message.RecipientType.TO,
//                    new InternetAddress(to));

//            message.addRecipients(Message.RecipientType.TO,
//                    "422549477@qq.com,422549477@qq.com");

            message.addRecipients(Message.RecipientType.TO,
                    new InternetAddress[]{new InternetAddress(to),
                            new InternetAddress(to)});
            // Set Subject: 头部头字段
            message.setSubject("This is the Subject Line!");

            // 设置消息体
            message.setText("This is actual message");

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....from Java Mail");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    private void template(){
        // Recipient's email ID needs to be mentioned.
        String to = "abcd@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "web@gmail.com";

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
