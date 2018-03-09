package cr.go.ice.interrupciones.BO.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import cr.go.ice.interrupciones.BO.EnvioEmailBO;
import cr.go.ice.interrupciones.domain.Correo;
import cr.go.ice.sige.confmailer.bo.MailerBo;
import cr.go.ice.sige.confmailer.domain.Mailer;

public class EnvioEmailBOImpl implements EnvioEmailBO{
	
	private String emailHost;
	private String emailUser;
	private String emailPassword;
	private String emailFrom;

	private MailerBo mailerBo;
	
	private SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
	
	public void enviarCorreoInterrupcionesCircuito(List<Correo> listaCorreos, String ruta)
	{
		/***OBTENER LOS DATOS DE CONEXIÓN DESDE EL SIGE MAILER***/
		try
		{
			Mailer mailer=this.mailerBo.buscar(); 
			this.emailHost=mailer.getServidor();
			this.emailUser=mailer.getUser();
			this.emailPassword=mailer.getPassword();
		}catch (Exception e)
		{
			e.printStackTrace();
		}

		if(this.emailHost != null && !this.emailHost.trim().equals(""))
		{
			if(this.emailUser != null && !this.emailUser.trim().equals(""))
			{
				if(this.emailPassword != null && !this.emailPassword.trim().equals(""))
				{
					if(!listaCorreos.isEmpty())
					{
						JavaMailSenderImpl sender = new JavaMailSenderImpl();
						sender.setHost(this.emailHost);	
						
						//Prueba para enviar correo Local
						/*sender.setHost("smtp.gmail.com");
						sender.setPort(587);
						sender.setProtocol("smtp");
						sender.setUsername("raulhidalgocr@gmail.com");
						sender.setPassword("contraseña");
						
						
						Properties props = new Properties();
						
						props.setProperty("mail.smtp.auth", "true");
						props.setProperty("mail.smtp.starttls.enable", "true");
						props.setProperty("mail.smtp.quitwait", "false");
						
						sender.setJavaMailProperties(props);*/
						//Fin prueba enviar correos local
						
						if(this.emailUser!=null && this.emailUser.length()>0)
						{
							sender.setUsername(this.emailUser);
						}
						if(this.emailPassword!=null && this.emailPassword.length()>0) {
							sender.setPassword(this.emailPassword);
						}
						
						for(Correo usuarios: listaCorreos)
						{
							String direccionCorreo = usuarios.getCorreo();
							if(this.emailValido(direccionCorreo))
							{
								MimeMessage message = sender.createMimeMessage();
								MimeMessageHelper helper;
								try {
									File file = new File(ruta);
									helper = new MimeMessageHelper(message, true);
									helper.setFrom(this.emailFrom);
									helper.setTo(InternetAddress.parse(direccionCorreo));
									helper.setSubject("Reporte de Interrupciones por Circuito");
									helper.addAttachment("SigeItrRepInterrupcionPorCircuito.xls", file);
									String cuerpo = this.plantillaCorreo();																				
									helper.setText(cuerpo, true);
									sender.send(message); 
								}catch (MessagingException e) {
									e.printStackTrace();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					} else {
					}
				}
			}
		}
	}

	private String plantillaCorreo() {
		return "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.0 Transitional//EN'>" +
		"<html>"+
		"<head>"+
		"<META http-equiv=Content-Type content='text/html; charset=iso-8859-'>"+
		"<META content='MSHTML 6.00.2900.2963' name=GENERATOR>"+
		"<title>Reporte de Interrupciones por Circuito</title>"+
		"</head>"+
		"<body>"+
		"<p align='center'><strong>Instituto Costarricense de Electricidad<br />Sistema de Interrupciones e Indices de Calidad"+
		"<br />Reporte de Interrupciones por Circuito"+
		"</strong><br />"+
		"</p>"+
		"</body>"+
		"</html>";
	}
	private boolean emailValido(String email){
		if(email == null || email.trim().equals("")) return false;
		if(!email.trim().contains("@")) return false;
		return true;
	}

	public String getEmailHost() {
		return emailHost;
	}

	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public MailerBo getMailerBo() {
		return mailerBo;
	}

	public void setMailerBo(MailerBo mailerBo) {
		this.mailerBo = mailerBo;
	}

	public SimpleDateFormat getFormato() {
		return formato;
	}

	public void setFormato(SimpleDateFormat formato) {
		this.formato = formato;
	}
	
	

}
