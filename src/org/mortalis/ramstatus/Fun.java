package org.mortalis.ramstatus;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class Fun {
  
  //---------------------------------------------- Log ----------------------------------------------
  
  private static void log(Object value, Vars.LogLevel level) {
    String msg = null;
    if (value != null) {
      msg = value.toString();
      if (Vars.APP_LOG_LEVEL == Vars.LogLevel.VERBOSE) {
        msg += " " + getCallerLogInfo();
      }
    }
    
    try {
      if (Vars.APP_LOG_LEVEL.compareTo(level) <= 0) {
        switch (level) {
          case INFO:
            Log.i(Vars.APP_LOG_TAG, msg);
            break;
          case DEBUG:
            Log.d(Vars.APP_LOG_TAG, msg);
            break;
          case WARN:
            Log.w(Vars.APP_LOG_TAG, msg);
            break;
          case ERROR:
            Log.e(Vars.APP_LOG_TAG, msg);
            break;
          default:
            break;
        }
      }
    }
    catch (Exception e) {
      System.out.println(Vars.APP_LOG_TAG + " :: " + msg);
    }
  }
  
  
  public static void log(String format, Object... values) {
    try {
      log(String.format(format, values));
    }
    catch (Exception e) {
      loge("Fun.log(format, values) Exception, " + e.getMessage());
      e.printStackTrace();
    }
  }
  
  public static void logd(String format, Object... values) {
    try {
      logd(String.format(format, values));
    }
    catch (Exception e) {
      loge("Fun.logd(format, values) Exception, " + e.getMessage());
      e.printStackTrace();
    }
  }
  
  public static void loge(String format, Object... values) {
    try {
      loge(String.format(format, values));
    }
    catch (Exception e) {
      loge("Fun.loge(format, values) Exception, " + e.getMessage());
      e.printStackTrace();
    }
  }
  
  public static void logw(String format, Object... values) {
    try {
      logw(String.format(format, values));
    }
    catch (Exception e) {
      loge("Fun.loge(format, values) Exception, " + e.getMessage());
      e.printStackTrace();
    }
  }
  
  public static void log(Object value) {
    log(value, Vars.LogLevel.INFO);
  }
  
  public static void logd(Object value) {
    log(value, Vars.LogLevel.DEBUG);
  }
  
  public static void logw(Object value) {
    log(value, Vars.LogLevel.WARN);
  }
  
  public static void loge(Object value) {
    log(value, Vars.LogLevel.ERROR);
  }
  
  public static void toast(Context context, String msg) {
    if (context == null) return;
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
  }
  
  
  private static String getCallerLogInfo() {
    String result = "";
    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
    
    if (stackTrace != null && stackTrace.length > 1){
      boolean currentFound = false;
      
      int len = stackTrace.length;
      for (int i = 0; i < len; i++) {
        StackTraceElement stackElement = stackTrace[i];
        String className = stackElement.getClassName();
        
        if (className != null && className.equals(Fun.class.getName())) {
          currentFound = true;
        }
        
        if (currentFound && className != null && !className.equals(Fun.class.getName())) {
          String resultClass = stackElement.getClassName();
          String method = stackElement.getMethodName();
          int line = stackElement.getLineNumber();
          result = "[" + resultClass + ":" + method + "():" + line + "]";
          break;
        }
      }
    }
    
    return result;
  }
  
  
  //---------------------------------------------- Notifications ----------------------------------------------
  
  public static void showNotification(Context context, int iconId, int iconLevel, int ramMB) {
    log("showNotification(): iconId=" + iconId + ", ramMB=" + ramMB);
    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, Vars.NOTIFICATIONS_CHANNEL_ID);
    
    // Bitmap largeIcon = null;
    Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.notif_large);
    
    mBuilder.setSmallIcon(iconId, iconLevel);
    mBuilder.setLargeIcon(largeIcon);
    mBuilder.setOngoing(true);
    
    mBuilder.setContentTitle("Free RAM");
    mBuilder.setContentText(ramMB + " MB Free");  
    
    NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    mNotificationManager.notify(Vars.NOTIFICATION_ID, mBuilder.build());
  }
  
  public static void cancelNotification(Context context) {
    NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    mNotificationManager.cancel(Vars.NOTIFICATION_ID);
  }

}
