package cn.anyoufang.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {

	// 鏃堕棿鏍煎紡
	private static final SimpleDateFormat SDF_YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat SDF_Y_M_D = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat SDF_YMDHMS_FNAME = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final SimpleDateFormat SDF_YMD = new SimpleDateFormat("yyyyMMdd");
	private static final SimpleDateFormat SDF_MD = new SimpleDateFormat("MMdd");


	public static String getYmdhms() {

		return SDF_YMDHMS.format(new Date());
	}
	private static final ThreadLocal<DateFormat> DATE_FORMATTER = new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};
	public static String getY_m_d(Date date) {
		return SDF_Y_M_D.format(date);
	}

	public static Date changeStrToDate(String dateStr) {
		try {
			return  SDF_Y_M_D.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static String getYmdhmsFName() {
		return SDF_YMDHMS_FNAME.format(new Date());
	}

	public static String getYmd() {
		return SDF_YMD.format(new Date());
	}

	/**
	 * @author chenyi
	 * @Description: 鑾峰彇MMDD鏃堕棿鏍煎紡
	 * @param
	 * @date 2017-1-31 涓嬪崍1:14:19
	 */
	public static String getMd() {
		return SDF_MD.format(new Date());
	}

	/**
	 * @author chenyi
	 * @Description 鑾峰彇鍓嶅悗鍑犲垎閽熺殑鏃堕棿
	 * @param
	 * @date 2017-3-3 涓嬪崍2:11:14
	 */
	public static String getTimeByMinute(int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, minute);
		return SDF_YMDHMS.format(calendar.getTime());

	}

	/**
	 * @author chenyi
	 * @Description 鑾峰彇鍓嶅悗鍑犲ぉ鐨勬棩鏈�
	 * @param
	 * @date 2017-3-3 涓嬪崍2:11:14
	 */
	public static String getDateByDay(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, day);
		return SDF_Y_M_D.format(calendar.getTime());

	}

	/**
	 * @author chenyi
	 * @Description 鏍规嵁浼犲叆鐨勬椂闂翠覆鏍煎紡鍖栨椂闂�
	 * @param
	 * @date 2017-3-20 涓嬪崍3:50:50
	 */
	public static String getYmdDate(Date date) {
		return SDF_Y_M_D.format(date);
	}
	public static String getYmdhmsDate(Date date) {
		return SDF_YMDHMS.format(date);
	}

	/**
	 * @author chenyi
	 * @Description 姣旇緝涓や釜鏃ユ湡鐨勫ぇ灏�
	 * @param
	 * @return 0:琛ㄧず鐩哥瓑 -1:DATE2鍦―ATE1涔嬪墠 1:DATE2鍦―ATE1涔嬪悗
	 * @date 2017-3-13 涓嬪崍4:25:47
	 */
	public static int compareDate(Date DATE1, Date DATE2) {
		try {
//			Date dt1 = SDF_Y_M_D.parse(DATE1);
//			Date dt2 = SDF_Y_M_D.parse(DATE2);
			if (DATE1.getTime() > DATE2.getTime()) {
				return 1;
			} else if (DATE1.getTime() < DATE2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * @author chenyi
	 * @Description 璺熷綋鍓嶆椂闂存瘮杈冨ぇ灏�
	 * @param
	 * @date 2017-3-13 涓嬪崍4:50:35
	 */
	public static int compareDate(Date DATE) {
		return compareDate((new Date()), DATE);
	}

	/**
	 * 比较两个时间大小（精确到时分秒）
	 * 
	 * @param timeone
	 *            格式（2017-02-07）
	 * @param timetow
	 *            格式（2017-02-07）
	 * @param type
	 *            (1比较大小有等于 2比较大小没等于)
	 * @return 1(timeone>timetow) -1(timeone<timetow) 2(timeone=timetow) 0错误
	 *         3(timeone>=timetow) -3(timeone<=timetow)
	 */
	public static int compare_dateTime(String timeone, String timetow, int type) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date dateone = sdf.parse(timeone);
			Date datetow = sdf.parse(timetow);
			if (type == 1) {
				if (dateone.getTime() >= datetow.getTime()) {
					return 3;
				} else if (dateone.getTime() <= datetow.getTime()) {
					return -3;
				} else if (dateone.getTime() == datetow.getTime()) {
					return 2;
				}
			} else if (type == 2) {
				if (dateone.getTime() > datetow.getTime()) {
					return 1;
				} else if (dateone.getTime() < datetow.getTime()) {
					return -1;
				} else if (dateone.getTime() == datetow.getTime()) {
					return 2;
				}
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * 比较两个时间大小（精确到天）
	 * 
	 * @param timeone
	 *            格式（2017-02-07）
	 * @param timetow
	 *            格式（2017-02-07）
	 * @param type
	 *            (1比较大小有等于 2比较大小没等于)
	 * @return 1(timeone>timetow) -1(timeone<timetow) 2(timeone=timetow) 0错误
	 *         3(timeone>=timetow) -3(timeone<=timetow)
	 */
	public static int compare_date(String timeone, String timetow, int type) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dateone = sdf.parse(timeone);
			Date datetow = sdf.parse(timetow);
			if (type == 1) {
				if (dateone.getTime() >= datetow.getTime()) {
					return 3;
				} else if (dateone.getTime() <= datetow.getTime()) {
					return -3;
				} else if (dateone.getTime() == datetow.getTime()) {
					return 2;
				}
			} else if (type == 2) {
				if (dateone.getTime() > datetow.getTime()) {
					return 1;
				} else if (dateone.getTime() < datetow.getTime()) {
					return -1;
				} else if (dateone.getTime() == datetow.getTime()) {
					return 2;
				}
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * @author chenyi
	 * @Description 时间字符串，转时间戳
	 * @param
	 * @date 2017年8月5日 下午3:49:53
	 */
	public static String getTime(String dateStr) {
		String re_time = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d;
		try {
			d = sdf.parse(dateStr);
			long l = d.getTime();
			String str = String.valueOf(l);
			re_time = str.substring(0, 10);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return re_time;
	}
	/**
	 * @author chenjiabin
	 * @Description 修改时间格式 yyyy-MM-dd 23:59:59
	 * @param
	 * @date
	 */
	public static Date changeDate(Date date) {
		String dateStr = SDF_Y_M_D.format(date);
		dateStr= dateStr + " 23:59:59";
		Date date2 = null;
		try {
			date2 = SDF_YMDHMS.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date2;
	}

	public static String stampToDate(String s){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}

	public static boolean isNotExpired(int end,int deadline) {
		Date time = new Date(Long.valueOf(end+"000"));
		long from = time.getTime();
		long to = System.currentTimeMillis();
		int days = (int) ((to - from)/(1000 * 60 * 60 * 24));
		if(days <= deadline) {
			return true;
		}
		return false;
	}

	/**
	 * 获取十位时间戳
	 * @return
	 */
	public static int generateTenTime() {
		long now = System.currentTimeMillis();
		int ten = (int) (now/1000);
		return ten;
	}

	/**
	 * 获取过期时间（十位）
	 * @param over
	 * @return
	 */
	public static int getExpiretimeInten(Object over) {
		String overtime = String.valueOf(over);
		long endtime = new Long(overtime) * 1000;
		long now = System.currentTimeMillis();
		return  (int) ((endtime - now) / 1000);
	}
}
