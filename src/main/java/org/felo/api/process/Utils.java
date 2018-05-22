package org.felo.api.process;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import org.apache.commons.lang.time.DateFormatUtils;

public abstract class Utils {

	public static String formatAmount(BigDecimal amount) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
		symbols.setGroupingSeparator('.');
		symbols.setDecimalSeparator(',');
		DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
		return "Rp." + df.format(amount);
	}
	
	public static String GenerateRandomNumber() {
		String ts = String.valueOf(System.currentTimeMillis());
		String rand = UUID.randomUUID().toString();
		return DigestUtils.sha1Hex(ts + rand);
	}

	public static String GenerateRandomNumber(int charLength) {
		return String.valueOf(charLength < 1 ? 0
				: new Random().nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
						+ (int) Math.pow(10, charLength - 1));
	}
	
	public static String getRandomNumberInRange(String max) {
		Random r = new Random();
		Long lrand = r.longs(1, (Long.valueOf(max) + 1)).limit(1).findFirst().getAsLong();
		String rand = String.valueOf(lrand);
		int size = max.length();
		String pad;
		if (rand.length() == 1) {
			pad = StringUtils.rightPad(rand, size, '0');
		} else {
			pad = StringUtils.leftPad(rand, size, '0');
		}
		return pad;
	}

	public static String GenerateTransactionNumber() {
		int randomNum = ThreadLocalRandom.current().nextInt(100000, 999999 + 1);
		return String.valueOf(randomNum);
	}

	public static String getMD5Hash(String source) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			String hex = (new HexBinaryAdapter()).marshal(md5.digest(source.getBytes("UTF-8")));
			return hex.toLowerCase();
		} catch (Exception ex) {
			return null;
		}
	}

	public static String GetCurrentDate() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(date);
	}

	public static String GetDate(String form) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat(form);
		return format.format(date);
	}

	public static String generateTraceNum() {
		Random rnd = new Random();
		int n = 10000000 + rnd.nextInt(90000000);
		String a = Integer.toString(n);
		return a;
	}

	public static String formatDate(Date date) {
		return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:SS");
	}
	
	public static String formatSimpleDate(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat osdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		Date d = sdf.parse(date);
		
		return osdf.format(d);
	}
	
	public static String generateUID(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = length; i > 0; i -= 12) {
			int n = Math.min(12, Math.abs(i));
			sb.append(StringUtils.leftPad(Long.toString(Math.round(Math.random() * Math.pow(36.0D, n)), 36), n, '0'));
		}

		return sb.toString();
	}
	
	public static int getTotalHour(Date startDate, Date endDate) {
		long sum = startDate.getTime() + endDate.getTime();
		Date sumDate = new Date(sum);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(sumDate);  
		int hours = cal.get(Calendar.HOUR_OF_DAY);
		System.out.println("SUM DATE: "+sumDate+" /Total Hours: "+hours);
		return hours;
	}
}
