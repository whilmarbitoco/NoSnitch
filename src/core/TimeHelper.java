package core;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeHelper {

        public static String parse(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(dateTime, now);

        long seconds = duration.getSeconds();
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if (seconds < 60) {
            return seconds > 1 ? (seconds + " seconds ago") : (seconds + " second ago");
        } else if (minutes < 60) {
            return minutes > 1 ? (minutes + " minutes ago") : (minutes + " minute ago");
        } else if (hours < 24) {
            return hours > 1 ? (hours + " hours ago") : (hours + " hour ago");
        } else {
            return days > 1 ? (days + " days ago") : (days + " day ago");
        }
        
    }
    
}
