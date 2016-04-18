package org.mortalis.ramstatus;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class MemService extends Service {

  private Looper mServiceLooper;
  private ServiceHandler mServiceHandler;
  String mName;


  private final class ServiceHandler extends Handler {
    public ServiceHandler(Looper looper) {
      super(looper);
    }

    @Override
    public void handleMessage(Message msg) {
      SetAlarm();
//      stopSelf(msg.arg1); 
    }
  }

  
  @Override
  public void onCreate() {
    HandlerThread thread = new HandlerThread("Service[" + mName + "]");
    thread.start();

    mServiceLooper = thread.getLooper();
    mServiceHandler = new ServiceHandler(mServiceLooper);
    mName = "Memory";
  }

  
  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    String toastMsg = "RAM Status Service Started";
    Log.i(MainActivity.TAG, toastMsg);
    Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();

    Message msg = mServiceHandler.obtainMessage();
    msg.arg1 = startId;
    mServiceHandler.sendMessage(msg);

    return START_STICKY;
  }

  
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  
  @Override
  public void onDestroy() {
    CancelAlarm();
    cancelNotification(this);
    
    String toastMsg = "RAM Status Service Stopped";
    Log.i(MainActivity.TAG, toastMsg);
    Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
    super.onDestroy();
  }


  public void SetAlarm() {
    Context context = this;
    AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(context, AlarmReceiver.class);
    PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
    am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000, pi);
  }

  public void CancelAlarm() {
    Context context = this;
    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(context, AlarmReceiver.class);
    PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
    alarmManager.cancel(sender);
  }
  
  
  public void cancelNotification(Context context){
    NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    mNotificationManager.cancel(MainActivity.notificationId);
  }

}
