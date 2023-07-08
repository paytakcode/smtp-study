import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("이 프로그램은 Gmail에서 SMTP를 이용하여 메일을 보내는 Test 프로그램입니다. \n"
			+ "사전에 Gmail 세팅에서 IMAP/POP3을 허용하고 Google의 앱 비밀번호를 발급받으세요.");
		String host = "smtp.gmail.com";
		System.out.println("Gmail 주소를 입력하세요.");
		String user = sc.nextLine();
		System.out.println("앱 비밀번호를 입력하세요.");
		String password = sc.nextLine();
		System.out.println("받는 이메일 주소를 입력하세요.");
		String receiver = sc.nextLine();
		System.out.println("메일 제목을 입력하세요.");
		String subject = sc.nextLine();
		System.out.println("메일 내용을 입력하세요.");
		String content = sc.nextLine();


		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));


			message.setSubject(subject);


			message.setText(content);


			Transport.send(message);
			System.out.println("메일 전송이 완료되었습니다.");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
