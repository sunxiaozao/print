package com.lubian.cpf.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {

	private static final Log logger = LogFactory.getLog(BeanUtils.class);

	private BeanUtils() {
	}

	public static Field getDeclaredField(Object object, String propertyName)
			throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		return getDeclaredField(object.getClass(), propertyName);
	}

	public static Object forceGetProperty(Object object, String propertyName)
			throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		Field field = getDeclaredField(object, propertyName);
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			logger.info("error wont' happen");
		}
		field.setAccessible(accessible);
		return result;
	}

	public static void forceSetProperty(Object object, String propertyName,
			Object newValue) throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		Field field = getDeclaredField(object, propertyName);
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		try {
			field.set(object, newValue);
		} catch (IllegalAccessException e) {
			logger.info("Error won't happen");
		}
		field.setAccessible(accessible);
	}

	public static Object invokePrivateMethod(Object object, String methodName,
			Object params[]) throws NoSuchMethodException {
		Assert.notNull(object);
		Assert.hasText(methodName);
		Class types[] = new Class[params.length];
		for (int i = 0; i < params.length; i++)
			types[i] = params[i].getClass();

		Class clazz = object.getClass();
		Method method = null;
		Class superClass = clazz;
		do {
			if (superClass == Object.class)
				break;
			try {
				method = superClass.getDeclaredMethod(methodName, types);
				break;
			} catch (NoSuchMethodException e) {
				superClass = superClass.getSuperclass();
			}
		} while (true);
		if (method == null)
			throw new NoSuchMethodException((new StringBuilder())
					.append("No Such Method:").append(clazz.getSimpleName())
					.append(methodName).toString());
		boolean accessible = method.isAccessible();
		method.setAccessible(true);
		Object result = null;
		try {
			result = method.invoke(object, params);
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
		method.setAccessible(accessible);
		return result;
	}

	public static List getFieldsByType(Object object, Class type) {
		List list = new ArrayList();
		Field fields[] = object.getClass().getDeclaredFields();
		Field arr$[] = fields;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; i$++) {
			Field field = arr$[i$];
			if (field.getType().isAssignableFrom(type))
				list.add(field);
		}

		return list;
	}

	public static Class getPropertyType(Class type, String name)
			throws NoSuchFieldException {
		return getDeclaredField(type, name).getType();
	}

	public static String getGetterName(Class type, String fieldName) {
		Assert.notNull(type, "Type required");
		Assert.hasText(fieldName, "FieldName required");
		if (type.getName().equals("boolean"))
			return (new StringBuilder()).append("is")
					.append(StringUtils.capitalize(fieldName)).toString();
		else
			return (new StringBuilder()).append("get")
					.append(StringUtils.capitalize(fieldName)).toString();
	}

	public static Method getGetterMethod(Class type, String fieldName)
			throws Exception {
		return type.getMethod(getGetterName(type, fieldName), new Class[0]);

	}

	public static Object invoke(String className, String methodName,
			Class argsClass[], Object args[]) throws ClassNotFoundException,
			SecurityException, NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			InstantiationException {
		Class cl = Class.forName(className);
		Method method = cl.getMethod(methodName, argsClass);
		return method.invoke(cl.newInstance(), args);
	}

	public static Object invoke(Object oldObject, String methodName,
			Class argsClass[], Object args[]) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Class cl = oldObject.getClass();
		Method method = cl.getMethod(methodName, argsClass);
		return method.invoke(oldObject, args);
	}

	public static String[] getFieldsName(Class cl) throws Exception {
		Field fields[] = cl.getDeclaredFields();
		String fieldNames[] = new String[fields.length];
		for (int i = 0; i < fields.length; i++)
			fieldNames[i] = fields[i].getName();

		return fieldNames;
	}

	public static List getAllFieldName(Class cl) {
		List list = new ArrayList();
		Field fields[] = cl.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (!field.getName().equals("serialVersionUID"))
				list.add(field.getName());
		}

		do {
			cl = cl.getSuperclass();
			if (cl != Object.class)
				list.addAll(getAllFieldName(cl));
			else
				return list;
		} while (true);
	}

	public static List getSetter(Class cl) {
		List list = new ArrayList();
		Method methods[] = cl.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			String methodName = method.getName();
			if (methodName.startsWith("set"))
				list.add(method);
		}

		do {
			cl = cl.getSuperclass();
			if (cl != Object.class)
				list.addAll(getSetter(cl));
			else
				return list;
		} while (true);
	}

	public static List getGetter(Class cl) {
		List list = new ArrayList();
		Method methods[] = cl.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			String methodName = method.getName();
			if (methodName.startsWith("get") || methodName.startsWith("is"))
				list.add(method);
		}

		do {
			cl = cl.getSuperclass();
			if (cl != Object.class)
				list.addAll(getGetter(cl));
			else
				return list;
		} while (true);
	}

	public static String getClassNameWithoutPackage(Class cl) {
		String className = cl.getName();
		int pos = className.lastIndexOf('.') + 1;
		if (pos == -1)
			pos = 0;
		return className.substring(pos);
	}

	public static String beanToString(Object obj) {
		return ToStringBuilder.reflectionToString(obj);
	}

}
