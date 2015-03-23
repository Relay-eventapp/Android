package bernard.com.eventapp.event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by bernard on 3/23/15.
 */
public class Splash extends Activity{
    @Override
    public void onCreate(Bundle bernard) {
        super.onCreate(bernard);
        setContentView(R.layout.splash);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                } catch(InterruptedException e){
                    e.printStackTrace();
                } finally{
                    Intent startMain = new Intent("bernard.com.eventapp.event.MainActivity");
                    startActivity(startMain);
                }
            }
        };

        timer.start();
    }
}
