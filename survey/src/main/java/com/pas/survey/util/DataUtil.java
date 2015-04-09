package com.pas.survey.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;

import com.pas.survey.model.BaseEntity;

/**
 * 数据
 */
public class DataUtil {
	/**
	 * 使用sha-1算法进行加密
	 */
	public static String sha_1(String src) {
		try {
			StringBuffer buffer = new StringBuffer();
			char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'A', 'B', 'C', 'D', 'E', 'F' };
			byte[] bytes = src.getBytes();
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] targ = md.digest(bytes);
			for (byte b : targ) {
				buffer.append(chars[(b >> 4) & 0xF]);
				buffer.append(chars[b & 0xF]);
			}
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 深度复制 需要对象及相关对象实现串行化接口
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Serializable deepCopy(Serializable obj) {
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		Serializable target = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			byte[] bytes = bos.toByteArray();
			bis = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bis);
			target = (Serializable) ois.readObject();

			oos.close();
			bos.close();
			ois.close();
			bis.close();
			return target;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
