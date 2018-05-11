/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */

package com.rennover.client.framework.message;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Hashtable;
import java.util.Map;

import com.rennover.hotel.common.log.Logger;

/**
 * @author Piyush
 * 
 */
public abstract class MessageFactory
{
	private static Map s_messageMap = new Hashtable();

	/**
	 * Return the message associated to the messageId if the message can not be
	 * found the messageId is returned
	 * 
	 * @param messageId
	 * @return message associated to the messageId or the messageId
	 */
	public static String translate(String messageId)
	{
		String message = (String) s_messageMap.get(messageId);
		if (message == null)
		{
			message = messageId;
		}
		return message;
	}

	/**
	 * Check if the MessageFactory contains a messageId
	 * 
	 * @param messageId
	 * @return
	 */
	public static boolean containsMessageId(String messageId)
	{
		return s_messageMap.containsKey(messageId);
	}

	public static void addMessage(String messageId, String message)
	{
		s_messageMap.put(messageId, message);
	}

	public static void addAllConstantsMessages(Class messageClass)
	{
		Field[] fields = messageClass.getFields();

		for (int i = 0; i < fields.length; i++)
		{
			Field field = fields[i];
			if (isMessageField(field))
			{
				String messageId = field.getName();
				try
				{
					String message = (String) field.get(null);
					addMessage(messageId, message);
				} catch (IllegalAccessException ex)
				{
					Logger.error(MessageFactory.class, "Can not access to the field " + field.getName(), ex);
				}
			}
		}
	}

	private static boolean isMessageField(Field field)
	{

		if (!String.class.isAssignableFrom(field.getType()))
		{
			return false;
		}
		return true;
	}

	public static String translate(String messageId, Object param)
	{
		return translate(messageId, new Object[] { param });
	}

	public static String translate(String messageId, Object param1, Object param2)
	{
		return translate(messageId, new Object[] { param1, param2 });
	}

	public static String translate(String messageId, Object param1, Object param2, Object param3)
	{
		return translate(messageId, new Object[] { param1, param2, param3 });
	}

	public static String translate(String messageId, Object[] parameters)
	{
		String msg = formatQuote(translate(messageId));
		MessageFormat messageFormat = new MessageFormat(msg);
		return messageFormat.format(parameters);
	}

	public static String formatQuote(String str)
	{
		if (null == str)
		{
			return str;
		}
		return str.replaceAll("'", "''");
	}
}
