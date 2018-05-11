/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */

/*
 * 
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.rennover.hotel.common.helper;



/**
 * ...
 *
 * @author dattias
 */
public class MailHelper
{
	//private static Session s_session;

	static
	{
		initMailSession();
	}

    /**
	 * Initialize the mail session parameters. For now, this method will not
	 * work in a no server mode.
	 */
    private static void initMailSession()
	{
//		// Le nom de la session mail se configure dans le config.xml ou dans la
//		// console Weblogic
//		final String mailSessionName = "NSIMailSession";
//
//		InitialContext ic = null;
//		Properties p = new Properties();
//
//		if (!CommonProperties.isNoServerMode())
//		{
//			try
//			{
//				p.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.T3InitialContextFactory");
//
//				String clusterUrl = CommonProperties.getClusterUrl();
//				p.put(Context.PROVIDER_URL, clusterUrl);
//
//				ic = new InitialContext(p);
//			} catch (NamingException exc)
//			{
//				Logger.error(MailHelper.class, "Impossible d'intialiser le contexte initial pour la session mail "
//				        + mailSessionName + " dans Weblogic", exc);
//			}
//
//			try
//			{
//				s_session = (Session) ic.lookup(mailSessionName);
//				Logger.debug(MailHelper.class, "Session de mail Weblogic " + mailSessionName + " initialisee");
//			} catch (NamingException exc)
//			{
//				Logger.error(MailHelper.class, "Impossible de trouver la session mail " + mailSessionName
//				        + " dans Weblogic", exc);
//			}
//		} else
//		{
//			p.put("mail.from", "nsicm@agefos-pme.com");
//			p.put("mail.host", PropertiesManager.getProperty("nsicm.server.mail.host"));
//			s_session = Session.getInstance(p);
//			Logger.debug(MailHelper.class, "Session de mail intialisée avec les valeurs par défaut");
//		}
	}

    /**
	 * Send an email.
	 * 
	 * @param recipient
	 *            email address (for example: gbush@usa.gov)
	 * @param subject
	 *            email subject
	 * @param text
	 *            email main text
	 */

    // #7575 -ashisht a new argument sender has been included
	public static void sendMail(String sender, String recipient, String subject, String text)
	{
//		String[] recipients = new String[1];
//		recipients[0] = recipient;
//
//		// #7575 -ashisht a new argument sender has been included
//		sendMail(sender, recipients, subject, text);
	}

    /**
	 * Send an email.
	 * 
	 * @param recipients
	 *            email addresses (for example: gbush@usa.gov)
	 * @param subject
	 *            email subject
	 * @param text
	 *            email main text
	 */

    //	#7575 -ashisht a new argument sender has been included
	public static void sendMail(String sender, String[] recipients, String subject, String text)
	{
//		try
//		{
//			if (s_session == null)
//			{
//				initMailSession();
//
//				if (s_session == null)
//				{
//					throw new TechnicalException("Envoi impossible d'un email");
//				}
//			}
//
//			MimeMessage msg = new MimeMessage(s_session);
//
//			//		------->>>> if the sender value is provided by the UC it is set into MimeMessage explicitily <<<<------- 
//			//		------->>>>      otherwise obtained implicitilly from default.properties #7575 -ashisht      <<<<-------
//			if (!PropertyHelper.isNull(sender))
//			{
//				msg.setFrom(new InternetAddress(sender));
//			}
//
//			for (int i = 0; i < recipients.length; i++)
//			{
//				msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients[i]));
//			}
//
//			msg.setSubject(subject);
//			msg.setText(text);
//
//			Transport.send(msg);
//		} catch (Exception e)
//		{
//			Logger.warn(MailHelper.class, "Envoi impossible d'un email", e);
//			throw new TechnicalException("Envoi impossible d'un email", e);
//		}
	}
}
