package com.hqq.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hqq.dao.PicDao;
import com.hqq.dao.UserDao;
import com.hqq.model.Pic;
import com.hqq.model.User;

public class Util {

	
	// 获取当前月份
	public static String getYuefen() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		return sdf.format(date.getTime());
	}
	
	
	// 获取当前系统时间
	public static String getShijian() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return sdf.format(date.getTime());
	}
	
	// 获取当前系统时间
	public static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return sdf.format(date.getTime());
	}
	
	// 获取当前日期
	public static String getRiqi() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return sdf.format(date.getTime());
	}


	// 获取当前系统时间
	public static String getTime2() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		return sdf.format(date.getTime());
	}

	// 上传文件/复制文件。
	public static void copyFile(File src, File dst) {
		try {
			int BUFFER_SIZE = 16 * 1024;
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				for (int byteRead = 0; (byteRead = in.read(buffer)) > 0;) {
					out.write(buffer, 0, byteRead);
				}

			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	// 初始化系统
	public static void init(HttpServletRequest request) {
		WebApplicationContext app = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		UserDao userDao = (UserDao) app.getBean("userDao");
		
		User user = userDao.selectBean(" where username='admin'  ");
		
		PicDao picDao = (PicDao) app.getBean("picDao");
		
		if (user == null) {
			user = new User();
			user.setPassword("111111");
			user.setRole(1);
			user.setUsername("admin");
			userDao.insertBean(user);
			
			
			Pic p1 = new Pic();
			p1.setPath("pic1.jpg");
			p1.setInfo("展示图片一");
			p1.setHref(".");
			picDao.insertBean(p1);
			
			Pic p2 = new Pic();
			p2.setPath("pic2.jpg");
			p2.setInfo("展示图片二");
			p2.setHref(".");
			picDao.insertBean(p2);
			
			Pic p3 = new Pic();
			p3.setPath("pic3.jpg");
			p3.setInfo("展示图片三");
			p3.setHref(".");
			picDao.insertBean(p3);
			
			Pic p4 = new Pic();
			p4.setPath("pic4.jpg");
			p4.setInfo("展示图片四");
			p4.setHref(".");
			picDao.insertBean(p4);
			
			Pic p5 = new Pic();
			p5.setPath("pic5.jpg");
			p5.setInfo("展示图片五");
			p5.setHref(".");
			picDao.insertBean(p5);
			
			
		}
	}

	// 获取当前系统时间
	public static Date getTime3(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(str);
	}

	
	public  static String getbianhao(int count ){
		String bianhao = "";
		
		int bianhao_ = count+1;
		if(bianhao_<10){
			bianhao = "00000"+bianhao_;
		}else if(bianhao_<100){
			bianhao = "0000"+bianhao_;
		}else if(bianhao_<1000){
			bianhao = "000"+bianhao_;
		}else if(bianhao_<10000){
			bianhao = "00"+bianhao_;
		}else if(bianhao_<100000){
			bianhao = "0"+bianhao_;
		}else if(bianhao_<1000000){
			bianhao = ""+bianhao_;
		}
		return bianhao;
	}

}
