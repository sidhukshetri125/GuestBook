package com.guestbook.Web;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ContactController {

	Logger logger = LoggerFactory.getLogger(ContactController.class);

	@Autowired
	private JavaMailSender mailSender;

	@GetMapping("/contact")
	public String showContactForm() {
		return "contactform";
	}

	/**
	 * This method is used to submitContactForm. This is the simplest contact form
	 * of a class method which is send the mail to admin Users can send the positive
	 * feedbacks, negative feedbacks with their images to Admin
	 * 
	 * @param request ,multiPartFile
	 * @return mailSender sends the mail
	 */
	@PostMapping("/contact")
	public String submitContactForm(HttpServletRequest request, @RequestParam("attachment") MultipartFile multiPartFile)
			throws MessagingException, UnsupportedEncodingException {
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		String mailsubject = fullname + "has sent a message";

		logger.debug("mailsubject::", mailsubject);

		String mailcontent = "<p><b>Sender Name</b>" + fullname + "";
		mailcontent += "<p><b>sender  E-mail:</b> " + email + "";
		mailcontent += "<p><b>Subject :</b>" + subject + "";
		mailcontent += "<p><b>content :</b>" + content + "";
		mailcontent += "<hr><img src='cid:abc'/>";

		logger.debug("mailcontent::", mailcontent);

		ClassPathResource resourse = new ClassPathResource("/static/abc");
		helper.addInline("abc", resourse);

		helper.setFrom("sidhukshetri125@gmail.com", "Sidhu Kshetri");
		helper.setTo("sidhukshetri8@gmail.com");
		helper.setSubject(mailsubject);
		helper.setText(mailcontent, true);

		if (!multiPartFile.isEmpty()) {

			String filename = StringUtils.cleanPath(multiPartFile.getOriginalFilename());

			InputStreamSource source = new InputStreamSource() {

				public InputStream getInputStream() throws IOException {
					return multiPartFile.getInputStream();
				}
			};
			helper.addAttachment(filename, source);
		}

		mailSender.send(message);
		return "message";
	}

}
