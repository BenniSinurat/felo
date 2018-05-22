package org.equinox.api.process;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

public abstract class Utils {
	public static String formatAmount(BigDecimal amount) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
		symbols.setGroupingSeparator('.');
		symbols.setDecimalSeparator(',');
		DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
		return "Rp." + df.format(amount);
	}

	public static String GenerateRandomNumber(int charLength) {
		return String.valueOf(charLength < 1 ? 0
				: new Random().nextInt(9 * (int) Math.pow(10.0D, charLength - 1) - 1)
						+ (int) Math.pow(10.0D, charLength - 1));
	}

	public static String GenerateTransactionNumber() {
		int randomNum = ThreadLocalRandom.current().nextInt(100000, 1000000);
		return String.valueOf(randomNum);
	}

	public static String getRandomNumberInRange(String max) {
		Random r = new Random();
		Long lrand = Long.valueOf(r.longs(1L, Long.valueOf(max).longValue() + 1L).limit(1L).findFirst().getAsLong());
		String rand = String.valueOf(lrand);
		int size = max.length();
		String pad;
		if (rand.length() == 1)
			pad = StringUtils.rightPad(rand, size, '0');
		else {
			pad = StringUtils.leftPad(rand, size, '0');
		}
		return pad;
	}

	public static String getMD5Hash(String source) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			String hex = new HexBinaryAdapter().marshal(md5.digest(source.getBytes("UTF-8")));
			return hex.toLowerCase();
		} catch (Exception ex) {
		}
		return null;
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

	public static String formatSimpleDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat osdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		Date d = sdf.parse(date);

		return osdf.format(d);
	}

	public static String addHourToDate(Integer hourAdd) {
		LocalDateTime nextTime = LocalDateTime.now().plusHours(hourAdd.intValue());
		return nextTime.toString();
	}
}