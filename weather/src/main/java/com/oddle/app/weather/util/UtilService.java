package com.oddle.app.weather.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilService {
	public static String getCurrentTime() {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return  sdf.format(dt);
	}
}
