package org.mortalis.ramstatus;

public class Vars {

  public enum LogLevel {VERBOSE, DEBUG, INFO, WARN, ERROR};
  public static final LogLevel APP_LOG_LEVEL = LogLevel.DEBUG;
  
  public static final String APP_LOG_TAG = "ramstatus";
  public static final String NOTIFICATIONS_CHANNEL_ID = "ramstatus_channel_id";
  
  public static final int NOTIFICATION_ID = 1;

}
