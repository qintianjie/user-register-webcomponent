package com.colorcc.user.register.aop;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.colorcc.user.register.form.UserForm;

@Service
@Aspect
public class SpringSendMailAspect {

	private final static Logger logger = LoggerFactory.getLogger(SpringSendMailAspect.class);

	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	VelocityEngine velocityEngine;

	private final static String fromMailAccount = "jackstudy@163.com";
	private final static String velocityFolder = "velocity";

	@Pointcut("execution(* com.colorcc.user.register.controller.*.do*(..))")
	public void sendMailPointcut() {

	}

	// @AfterReturning(pointcut = "sendMailPointcut()")
	public void sendMail(JoinPoint thisJoinPoint) {
		Object[] args = thisJoinPoint.getArgs();
		if (args != null & args.length > 0) {
			UserForm userForm = (UserForm) args[0];
			if (userForm != null) {
				if (logger.isInfoEnabled()) {
					logger.info("Send mail to " + userForm.getEmail());
				}
				String email = userForm.getEmail();

				String mailTitle = "Default Title.";
				String vmTemplate = velocityFolder + "/" + "default";
				if ("doCreateUser".equals(thisJoinPoint.getSignature().getName())) {
					mailTitle = "Create new user in colorcc.com";
					vmTemplate = velocityFolder + "/" + "mailForCreateUser.vm";
				} else if ("doUpdateUser".equals(thisJoinPoint.getSignature().getName())) {
					mailTitle = "Update user " + userForm.getEmail();
					vmTemplate = velocityFolder + "/" + "mailForUpdateUser.vm";
				}

				Map<String, String> velocityParamsMap = new HashMap<String, String>();

				velocityParamsMap.put("title", mailTitle);
				velocityParamsMap.put("to", email);
				velocityParamsMap.put("email", email);
				velocityParamsMap.put("status", String.valueOf(userForm.getStatus()));

				velocityParamsMap.put("VM_TEMPLATE", vmTemplate);

				doSendMail(velocityParamsMap);
			}
		}
	}

	private void doSendMail(final Map<String, String> velocityParamsMap) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setSubject(velocityParamsMap.get("title"));
				message.setFrom(fromMailAccount);
				message.setTo(velocityParamsMap.get("to"));
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, velocityParamsMap.get("VM_TEMPLATE"), velocityParamsMap);
				message.setText(text, true);
			}
		};
		javaMailSender.send(preparator);
	}

	// private void doSendMail(final String email, final String mailTitle, final StringBuilder mailContent) {
	// MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	// try {
	// MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
	// messageHelper.setFrom(new InternetAddress(fromMailAccount));// from XXX邮箱
	// messageHelper.setTo(email);// toXXX邮箱
	//
	// messageHelper.setSubject(mailTitle); // 设置主题
	// messageHelper.setText(mailContent.toString()); // 设置内容
	//
	// String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/emailBody.vm", hTemplateVariables);
	//
	// javaMailSender.send(mimeMessage);
	// } catch (AddressException e) {
	// e.printStackTrace();
	// } catch (MessagingException e) {
	// e.printStackTrace();
	// }
	// }
}
