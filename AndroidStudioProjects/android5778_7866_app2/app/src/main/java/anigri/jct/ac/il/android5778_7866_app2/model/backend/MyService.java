package anigri.jct.ac.il.android5778_7866_app2.model.backend;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {


    final String TAG = "myservice";
    static boolean ServiceRun;// = false;

    static {
        ServiceRun = false;
    }

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        // TODO: Return the communication channel to the service.
        return null;
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        if (!ServiceRun) {
            ServiceRun = true;
            //Toast.makeText(this, "run service", Toast.LENGTH_LONG).show();

            Thread t = new Thread() {
                @Override
                public void run() {
                    while(true){

                        try{
                            Thread.sleep(1000);   //   sleep 10 seconds TODO 1 sec to 10 sec
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        if(DB_Manager_Factory.getManager().didReservationsChanged()){
                            //Log.d("didReservationsChanged", "changed!!!");

                            Intent i = new Intent("il.ac.jct.UPDATE");
                            i.putExtra("message", "Cars list updated!");
                            MyService.this.sendBroadcast(i);
                        }
                        else{
                            //Log.d("didReservationsChanged", "not changed!!!");
                        }
                    }
                }
            };
            t.start();
            return START_STICKY;
        }

        //Toast.makeText(this, "The service is already running", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

}
