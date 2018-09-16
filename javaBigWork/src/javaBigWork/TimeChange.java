package javaBigWork;

public class TimeChange {
	
	public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = (time / 60);
            if (minute < 60) {
                second =  (time % 60);
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second =  (time - hour * 3600 - minute * 60);
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int second) {
        String retStr = null;
        if (second >= 0 && second < 10)
            retStr = "0" + Integer.toString(second);
        else
            retStr = "" + second;
        return retStr;
    }
    
}
