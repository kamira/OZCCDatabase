package tw.delta4studio.www.ozccdatabase;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.io.File;

/**
 * Created by kamira on 2016/09/03.
 */
public class DownloadLogo extends Activity {
    static String dir = "/data/data/tw.delta4studio.www.ozccdatabase/pic";
    ProgressBar br;

    public class SavePics extends AsyncTask<Void...voids>{
        @Override
        protected Void doInBackground(Void...voids){
            File folder = new File(dir);
            if(!folder.exists()){
                folder.mkdir();
            }

            UseDB usedb = new UseDB();
            String[][] result = usedb.select("pics", Loading.pics, null, null);
            pb.setMax(result.length);

            for (int i = 0; i < result.length; i++){

            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.downloadlogo);

        pb = (ProgressBar) findViewById(R.id.progressBar1);
        new SavePics()
    }
}
