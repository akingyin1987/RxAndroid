package rx.android.samples.http;

import android.os.Environment;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.UUID;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.RequestModel;
import rx.android.samples.ResultSub;
import rx.android.samples.util.FileUtil;

/**
 *  
 * User: Geek_Soledad(msdx.android@qq.com) 
 * Date: 2015-07-24 
 * Time: 11:27 
 *
 */
public class OkHttp {

      public static OkHttpClient mOkHttpClient = null;

    public static final int minTime = 15;
    public static final int maxTime = 45;

    static {
        mOkHttpClient = new OkHttpClient();

        mOkHttpClient.networkInterceptors().add(new StethoInterceptor());

    }


    public static void doPost(RequestBody requestBody,RequestModel  requestModel, String url
                           ,final  Subscriber<? super ResultSub> subscriber ) {

        Request request = new Request.Builder().url(url).post(requestBody)
                .build();

        Response response;
        try {
            response = mOkHttpClient.newCall(request).execute();
            int  requestcode = response.code();
            if (response.isSuccessful()) {
                try {
                    JSONObject resultJsonObject = new JSONObject(response.body().string());
                    ResultSub   sub = new ResultSub();

                    sub.requestModel = requestModel;
                    sub.obj = resultJsonObject;
                    sub.code = requestcode;
                    if(requestModel == RequestModel.DATA){

                        subscriber.onCompleted();
                    }else{
                        subscriber.onNext(sub);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {

            e.printStackTrace();

        }

    }



    public   static ResultSub doPost(RequestBody   requestBody,String url){
        Request request = new Request.Builder().url(url).post(requestBody)
                .build();
        ResultSub    sub =  new ResultSub();
        Response response;
        try {
            response = mOkHttpClient.newCall(request).execute();
            int  requestcode = response.code();
            if (response.isSuccessful()) {
                try {
                    JSONObject resultJsonObject = new JSONObject(response.body().string());
                    sub = new ResultSub();
                    sub.obj = resultJsonObject;
                    sub.code = requestcode;


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {

            e.printStackTrace();

        }
        return sub;
    }


    public  static    String  getUuid(){
        UUID  uuid = UUID.randomUUID();
        return  uuid.toString();
    }


    public   static     void   downFile(final String  url,final  Subscriber<? super String> subscriber){

        Request   request = new Request.Builder().tag(getUuid()).url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                   if(response.isSuccessful()){
                      String  directory  =  Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"temp";
                       FileUtil.makeDirectory(directory);
                       String filename = url.substring(url.lastIndexOf('/') + 1, url.length());
                       File  downloadFile = new File(directory+File.separator+filename);
                       FileOutputStream   buffer = new FileOutputStream(downloadFile);
                       InputStream instream  = response.body().byteStream();
                       try {
                           if(null != instream){

                               byte[] tmp = new byte[BUFFER_SIZE];
                               int l ;
                               // do not send messages if request has been cancelled
                               while ((l = instream.read(tmp)) != -1 && !Thread.currentThread().isInterrupted()) {

                                   buffer.write(tmp, 0, l);

                               }
                               subscriber.onNext("12233");
                           }
                       } catch (IOException e) {
                           subscriber.onError(e);
                           e.printStackTrace();
                       }

                   }
            }
        });
    }
    protected static final int BUFFER_SIZE = 4096;

}

