package tw.delta4studio.www.ozccdatabase;

import tw.delta4studio.www.ozccdatabase.tools.*;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.io.File;

/**
 * Created by kamira on 2016/09/03.
 */
public class DownloadLogo extends Activity {
    static String dir = "/data/data/tw.delta4studio.www.ozccdatabase/pics/";
    ProgressBar pb;

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
                String[] url = result[i][4].split(";");
                for (int j = 0; j < url.length; j++){
                    String path = dir + result[i][2] + "_" + j + ".png";
                    File file = new File(path);

                    if ( !file.exists() && !( url[j].equals(null) ||
                                              url[j].equals("")   ||
                                              url[j].equals("null") ) ){
                        Download dl = new Download();
                        Bitmap bmp = null;
                        SaveBMP save = new SaveBMP();

                        bmp = dl.downloadAsBMP(url[j]);
                        save.saveAsPNG(bmp, dir, result[i][2] + "_" + j);
                    }
                }
                publishProgress(i+1);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer...progress) { pb.setProgress(progress[0]); }

        @Override
        protected void onPostExecute(Void voids) {
            Intent intent = new Intent();
            intent.setClass(DownloadLogo.this, RuneList.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.downloadlogo);

        pb = (ProgressBar) findViewById(R.id.progressBar1);
        new SavePics().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

}
