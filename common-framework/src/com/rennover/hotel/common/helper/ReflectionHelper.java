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

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.rennover.hotel.common.exception.Assert;
import com.rennover.hotel.common.exception.TechnicalException;
import com.rennover.hotel.common.log.Logger;
import com.rennover.hotel.common.valueobject.ValueObject;

/**
 *
 */
public abstract class ReflectionHelper {
	private static final int BUFFER_INITIAL_SIZE = 200;
	
	private static Map s_instanceFieldsCache = new HashMap();
	
	/**
	 * Ensemble des PropertyDescriptor des objets sur lesquels sont invoquées les méthodes get/setProperty. Structure =
	 * map[Class, map[String, PropertyDescriptor]]
	 */
	private static Map s_descriptorMap;
	
	/**
	 * Ensemble des classes/méthodes
	 */
	private static Map s_methodMap = new HashMap();
	
	/**
	 * Retourne la liste des attributs de la classe realClass en remontant jusqu'à la super classe upperClass.
	 * @param realClass la classe de l'objet
	 * @param upperClass la classe la plus haute
	 * @return une liste d'objets Field
	 */
	private static List getAllFieldsOf(Class realClass, Class upperClass) {
		List result = (List) s_instanceFieldsCache.get(realClass);
		
		if (result == null) {
			result = recursiveGetAllFieldsOf(realClass, upperClass);
			s_instanceFieldsCache.put(realClass, result);
		}
		
		return result;
	}
	
	private static List recursiveGetAllFieldsOf(Class realClass, Class upperClass) {
		if (realClass == upperClass) { return new ArrayList(); }
		
		List result = recursiveGetAllFieldsOf(realClass.getSuperclass(), upperClass);
		result.addAll(Arrays.asList(realClass.getDeclaredFields()));
		
		Iterator fields = result.iterator();
		
		while (fields.hasNext()) {
			Field field = (Field) fields.next();
			
			if (Modifier.isStatic(field.getModifiers())) {
				fields.remove();
			}
		}
		
		return result;
	}
	
	public static boolean equals(Object o1, Object o2) {
		if ((o1 == null) || (o2 == null)) { return (o1 == o2); }
		
		if (o1.getClass() != o2.getClass()) { return false; }
		
		if ((o1 instanceof String) || (o1 instanceof Number) || (o1 instanceof Boolean) || (o1 instanceof Character)
				|| (o1 instanceof Date)) { return o1.equals(o2); }
		
		try {
			Iterator fields = getAllFieldsOf(o1.getClass(), Object.class).iterator();
			
			while (fields.hasNext()) {
				Field field = (Field) fields.next();
				
				if (field.getName().equals("_objectClass")) {
					continue;
				}
				
				field.setAccessible(true);
				
				Object value1 = field.get(o1);
				
				if (!equals(value1, field.get(o2))) { return false; }
			}
			
			return true;
		} catch (IllegalAccessException iae) {
			throw new RuntimeException("error when accessing a field when comparing objects "
					+ iae.getMessage());
		}
	}
	
	/**
	 * Retourne la valeur de la propriété propertyName de l'objet object
	 * @param object l'objet à interroger
	 * @param propertyName le nom de la propriété
	 * @return la valeur de la propriété
	 * @audit dattias 23/12/02
	 * @change mettre ou non les assert ?
	 */
	public static final Object getProperty(Object object, String propertyName) {
		try {
			Method method = getPropertyDescriptor(object.getClass(), propertyName).getReadMethod();
			
			return method.invoke(object, null);
		} catch (InvocationTargetException exc) {
			throw new TechnicalException(
					"Can not read property " + propertyName + " of " + object.getClass(), exc);
		} catch (IllegalAccessException exc) {
			throw new TechnicalException(
					"Can not read property " + propertyName + " of " + object.getClass(), exc);
		}
	}
	
	/**
	 * Retourne le type de la propriété propertyName ddans la classe aClass
	 * @param aClass la classe a interroger
	 * @param propertyName le nom de la propriété
	 * @return le type de la propriété
	 */
	public static final Class getPropertyType(Class aClass, String propertyName) {
		return getPropertyDescriptor(aClass, propertyName).getPropertyType();
	}
	
	/**
	 * Positionne la valeur de la propriété propertyName de l'objet object
	 * @param object l'objet à interroger
	 * @param propertyName le nom de la propriété
	 */
	public static final void setProperty(Object object, String propertyName, Object value) {
		try {
			Method method = getPropertyDescriptor(object.getClass(), propertyName).getWriteMethod();
			
			if (method != null) {
				method.invoke(object, new Object[] {
					value
				});
			} else {
				throw new TechnicalException("No setter for the property " + propertyName + " of "
						+ object.getClass());
			}
		}
		// IllegalArgumentException
		// InvocationTargetException
		// IllegalAccessException
		//TechnicalException
		catch (Exception exc) {
			throw new TechnicalException("Unable to update the property " + propertyName + " of " + object.getClass()
					+ " with " + value, exc);
		}
	}
	
	/**
	 * Retourne le PropertyDescriptor de la propriété passée en paramètre. Les PropertyDescriptors sont cachés dans une
	 * map statique.
	 */
	private static PropertyDescriptor getPropertyDescriptor(Class objectClass, String propertyName) {
		Map byPropertyMap = null;
		PropertyDescriptor descriptor = null;
		
		if (s_descriptorMap == null) {
			s_descriptorMap = new HashMap();
		} else {
			byPropertyMap = (Map) s_descriptorMap.get(objectClass);
		}
		
		if (byPropertyMap == null) {
			byPropertyMap = new HashMap();
			s_descriptorMap.put(objectClass, byPropertyMap);
		} else {
			descriptor = (PropertyDescriptor) byPropertyMap.get(propertyName);
		}
		
		if (descriptor == null) {
			try {
				descriptor = new PropertyDescriptor(propertyName, objectClass);
			} catch (IntrospectionException exc) {
				try {
					try {
						descriptor = new PropertyDescriptor(propertyName, objectClass, "is" + capitalize(propertyName),
								null);
					} catch (Exception getterExc) {
						descriptor = new PropertyDescriptor(propertyName, objectClass,
								"get" + capitalize(propertyName), null);
					}
				} catch (IntrospectionException e) {
					throw new TechnicalException("Can not find getter" + propertyName + " in " + objectClass, e);
				}
			}
			
			byPropertyMap.put(propertyName, descriptor);
		}
		
		return descriptor;
	}
	
	private static String capitalize(String s) {
		if (s.length() == 0) { return s; }
		
		char[] chars = s.toCharArray();
		chars[0] = Character.toUpperCase(chars[0]);
		
		return new String(chars);
	}
	
	/**
	 * Renvoie l'état de l'objet au format : [className, attribut1=[], attribut2=...]
	 */
	public static String dump(Object object) {
		if (object == null) {
			return "null";
		} else if (object instanceof ValueObject) {
			return dumpValueObject(object, ValueObject.class);
		} else {
			return object.toString();
		}
	}
	
	public static StringBuffer dump(StringBuffer buffer, Object object) {
		if (object == null) {
			return buffer;
		} else if (object instanceof ValueObject) {
			return dumpValueObject(buffer, object, ValueObject.class);
		} else {
			return buffer.append(object.toString());
		}
	}
	
	private static String dumpValueObject(Object object, Class upperClass) {
		return dumpValueObject(new StringBuffer(BUFFER_INITIAL_SIZE), object, upperClass).toString();
	}
	
	private static StringBuffer dumpValueObject(StringBuffer buffer, Object object, Class upperClass) {
		buffer.append('[').append(getClassShortName(object.getClass())).append(", ");
		
		try {
			Iterator fields = getAllFieldsOf(object.getClass(), upperClass).iterator();
			
			while (fields.hasNext()) {
				Field field = (Field) fields.next();
				field.setAccessible(true);
				buffer.append(trimFieldPrefix(field.getName()));
				buffer.append('=');
				dump(buffer, field.get(object));
				
				if (fields.hasNext()) {
					buffer.append(", ");
				}
			}
		} catch (IllegalAccessException exc) {
			Logger.warn(ReflectionHelper.class, "Unable to dump an object of type " + object.getClass(), exc);
		}
		
		return buffer.append(']');
	}
	
	public static String trimFieldPrefix(String s) {
		int index = s.indexOf('_');
		
		if (index < 0) { return s; }
		
		return s.substring(index + 1);
	}
	
	public static String getClassShortName(Class c) {
		if (c == null) { return "ClassShortNameNull"; }
		
		String className = c.getName();
		int index = className.lastIndexOf(".");
		
		return className.substring(index + 1);
	}
	
	public static void initialize(Class c) {
		try {
			Class.forName(c.getName(), true, c.getClassLoader());
		} catch (ClassNotFoundException cnfe) {
			throw new RuntimeException("Could not initialize class " + c.getName());
		}
	}
	
	/**
	 * Allows you to search a field in a class and all its superclasses Recursively.
	 * @param name the field name to search
	 * @param aClass the class in which the search field.
	 * @throws NoSuchFieldException if the specified field is not found in the classroom or Super classes.
	 */
	public static Field getField(Class aClass, String name) throws NoSuchFieldException {
		return getField(aClass, name, aClass.getName());
	}
	
	private static Field getField(Class aClass, String attributeName, String initialClassName)
			throws NoSuchFieldException {
		if (aClass == Object.class) { throw new NoSuchFieldException("Attribute " + attributeName + " found in "
				+ initialClassName); }
		
		try {
			return aClass.getDeclaredField(attributeName);
		} catch (NoSuchFieldException nsfe) {
			return getField(aClass.getSuperclass(), attributeName, initialClassName);
		}
	}
	
	/**
	 * Permet de chercher une méthode dans une classe et toutes ses super classes, connaissant son nom. Note: on fait
	 * une recherche par nom (sans spécifier le type des paramètres) car c'est une facon plus souple de procéder. En
	 * effet, cela autorise d'utiliser dans les ValueObject des accesseurs ayant en paramètres des types différents de
	 * ceux des attributs concernés (typiquement des setXXX prenant en paramètres des Object...).
	 * @param Class la classe sur laquelle s'effectue la recherche
	 * @param name nom de la méthode à rechercher
	 * @return Method la méthode concernée
	 * @throws NoSuchMethodException si la méthode recherchée n'est pas trouvée dans la classe ou ses super classes
	 */
	public static Method getMethod(Class aClass, String name) throws NoSuchMethodException {
		Method result = null;
		
		if (aClass == Object.class) { throw new NoSuchMethodException("Error: Can not find method "
				+ name); }
		
		Map methodMap = (Map) s_methodMap.get(aClass);
		
		if (methodMap == null) {
			methodMap = new HashMap();
			s_methodMap.put(aClass, methodMap);
		}
		
		result = (Method) methodMap.get(name);
		
		if (result == null) {
			Method[] methods = aClass.getDeclaredMethods();
			
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].getName().equals(name)) {
					result = methods[i];
					
					break;
				}
			}
			
			if (result == null) {
				result = getMethod(aClass.getSuperclass(), name);
			}
			
			methodMap.put(name, result);
		}
		
		return result;
	}
	
	public static Object get(Object object, List fieldPath) {
		Assert.checkNotNull(object);
		Assert.checkNotNull(fieldPath);
		Assert.check(!fieldPath.isEmpty());
		
		Object value = object;
		Iterator iterator = fieldPath.iterator();
		
		while (iterator.hasNext() && (value != null)) {
			Field field = (Field) iterator.next();
			
			try {
				value = field.get(value);
			} catch (IllegalAccessException exc) {
				throw new TechnicalException("Illegal access to field " + field.getName() + " of class " + object, exc);
			}
		}
		
		return value;
	}
	
	public static void set(Object object, List fieldPath, Object value) {
		Assert.checkNotNull(object);
		Assert.checkNotNull(fieldPath);
		Assert.check(!fieldPath.isEmpty());
		
		Iterator it = fieldPath.iterator();
		
		while (it.hasNext()) {
			Field field = (Field) it.next();
			
			try {
				if (it.hasNext()) {
					Object fieldValue = field.get(object);
					
					if (fieldValue == null) {
						fieldValue = newInstance(field.getType());
						field.set(object, fieldValue);
					}
					
					object = fieldValue;
				} else {
					field.set(object, value);
				}
			} catch (IllegalAccessException exc) {
				throw new TechnicalException("Illegal access to field " + field.getName() + " of class " + object, exc);
			}
		}
	}
	
	public static Object get(Object object, Method getMethod) {
		Assert.checkNotNull(object);
		Assert.checkNotNull(getMethod);
		
		try {
			return getMethod.invoke(object, null);
		} catch (IllegalAccessException exc) {
			throw new TechnicalException("Illegal access to method " + getMethod.getName() + " of class "
					+ object.getClass().getName(), exc);
		} catch (InvocationTargetException exc) {
			throw new TechnicalException("Error in method " + getMethod.getName() + " of class "
					+ object.getClass().getName(), exc);
		}
	}
	
	public static void set(Object object, Method setMethod, Object value) {
		Assert.checkNotNull(object);
		Assert.checkNotNull(setMethod);
		
		try {
			setMethod.invoke(object, new Object[] {
				value
			});
		} catch (IllegalAccessException exc) {
			throw new TechnicalException("Illegal access to method " + setMethod.getName() + " of class "
					+ object.getClass().getName(), exc);
		} catch (InvocationTargetException exc) {
			throw new TechnicalException("Error in method " + setMethod.getName() + " of class "
					+ object.getClass().getName(), exc);
		}
	}
	
	private static Object newInstance(Class classToInstanciate) {
		try {
			return classToInstanciate.newInstance();
		} catch (InstantiationException exc) {
			throw new TechnicalException("Instanciation error for class " + classToInstanciate, exc);
		} catch (IllegalAccessException exc) {
			throw new TechnicalException("Illegal access to default constructor of class " + classToInstanciate, exc);
		}
	}
	
	/**
	 * Method getOnlyObjectType.
	 * @param objectType
	 * @return Class
	 */
	public static Class getOnlyObjectType(Class objectType) {
		if (objectType == boolean.class) {
			return Boolean.class;
		} else if (objectType == int.class) {
			return Integer.class;
		} else if (objectType == double.class) {
			return Double.class;
		} else if (objectType == long.class) {
			return Long.class;
		} else if (objectType == short.class) {
			return Short.class;
		} else if (objectType == byte.class) {
			return Byte.class;
		} else if (objectType == float.class) {
			return Float.class;
		} else if (objectType == char.class) { return Character.class; }
		
		return objectType;
	}
}
