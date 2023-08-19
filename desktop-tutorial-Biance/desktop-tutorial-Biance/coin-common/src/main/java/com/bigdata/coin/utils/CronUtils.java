package com.bigdata.coin.utils;


public class CronUtils {

    /**
     * 转换cron表达式.
     * @param dim 周期HHH
     * @param runTime 运行时间
     * @return
     */
    public static String createCron(String dim, String runTime) {
        StringBuilder sb = new StringBuilder();
        if (dim.contains("HHH")) {
            sb.append("0");
            sb.append(" ");
            sb.append(runTime.substring(runTime.length() - 2));
            sb.append(" * * * ?");
        } else if (dim.contains("DDD")) {
            sb.append("0");
            sb.append(" ");
            sb.append(runTime.substring(2, 4));
            sb.append(" ");
            sb.append(runTime.substring(0, 2));
            sb.append(" * * ?");
        } else if (dim.contains("W")) {
            sb.append("0");
            sb.append(" ");
            sb.append(runTime.substring(2, 4));
            sb.append(" ");
            sb.append(runTime.substring(0, 2));
            sb.append(" ? * ");
            sb.append(getWeek(dim.substring(2, 3)));
        } else if (dim.contains("M")) {
            String day = dim.substring(1, 3);
            sb.append("0");
            sb.append(" ");
            sb.append(runTime.substring(2, 4));
            sb.append(" ");
            sb.append(runTime.substring(0, 2));
            sb.append(" ");
            sb.append(day);
            sb.append(" * ?");
        } else if (dim.contains("H") && !dim.contains("HHH")) {
            String hour = dim.substring(1, 3);
            if (hour.contains("0")) {
                hour = hour.substring(1, 2);
            }
            sb.append("0");
            sb.append(" ");
            sb.append(runTime.substring(runTime.length() - 2));
            sb.append(" ");
            sb.append("*/").append(hour);
            sb.append(" * * *");
        } else if (dim.contains("D") && !dim.contains("DDD")) {
            String day = dim.substring(1, 3);
            if (day.contains("0")) {
                day = day.substring(1, 2);
            }
            sb.append("* ").append("* */").append(day).append(" * *");
        }
        return sb.toString();
    }

    public static String getWeek(String num) {
        String week;
        switch (num) {
            case "1":
                week = "MON";
                break;
            case "2":
                week = "TUE";
                break;
            case "3":
                week = "WED";
                break;
            case "4":
                week = "THU";
                break;
            case "5":
                week = "FRI";
                break;
            case "6":
                week = "SAT";
                break;
            case "7":
                week = "SUN";
                break;
            default:
                week = "SUN";
        }
        return week;
    }
}
