package anigri.jct.ac.il.android5778_7866_app2.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.URL;
import java.util.HashMap;

import anigri.jct.ac.il.android5778_7866_app2.R;

/**
 * Created by Bruno on 23/01/2018.
 */

/*
        AsyncTask enables proper and easy use of the UI thread. This class
        allows to perform background operations and publish results on the UI
        thread without having to manipulate threads and/or handlers.
*/

/*
    final AsyncTask<Params, Progress, Result>
    execute(Params... params)
    Executes the task with the specified parameters.
 */
public class DownLoadImageTask extends AsyncTask<String, Void, Bitmap> {

    private ImageView imageView;
    private long id;
    private Context context;

    private static HashMap<Long, Bitmap> bitmaps;

    public DownLoadImageTask(ImageView imageView, long id, Context context) {
        this.imageView = imageView;
        this.id = id;
        this.context = context;
    }

    static {
        bitmaps = new HashMap<>();
    }
    /*
        doInBackground(Params... params)
        Override this method to perform a computation on a background thread.
    */
    @Override
    protected Bitmap doInBackground(String... strings) {
        /*String urlOfImage = strings[0];
        Bitmap logo = null;
        try{
            InputStream inputStream = new URL(urlOfImage).openStream();
            *//*
                decodeStream(InputStream is)
                Decode an input stream into a bitmap.
            *//*
            logo = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e){ // Catch the download exception
            e.printStackTrace();
        }
        */
        Bitmap logo = null;

        logo = bitmaps.get(id);

        if(logo == null){//wasn't in the hash map
            try{
                logo = BitmapFactory.decodeStream(new URL(strings[0]).openStream());
                bitmaps.put(id, logo);//save bitmap for next time
            } catch (Exception e){
                logo = BitmapFactory.decodeResource(context.getResources(), R.drawable.icons8_error_50);
            }
        }

        return logo;
    }

    /*
        onPostExecute(Result result)
        Runs on the UI thread after doInBackground(Params...).
    */
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}