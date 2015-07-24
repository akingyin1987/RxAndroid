package rx.android.samples.jobs;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.samples.RequestModel;
import rx.android.samples.ResultSub;
import rx.android.samples.http.HttpRequestUrlConfig;
import rx.android.samples.http.OkHttp;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 *  
 * User: Geek_Soledad(msdx.android@qq.com) 
 * Date: 2015-07-24 
 * Time: 15:27 
 *
 */
public class RxUploadJob {

    public RequestModel    requestModel;

    public   String   uuid;

    public    RxUploadJob(){
            requestModel = RequestModel.VALFILEISEXIST;
            Observable.just("").subscribeOn(Schedulers.io()).map(new Func1<String, ResultSub>() {
                @Override
                public ResultSub call(String s) {
                    FormEncodingBuilder formBuilder = new FormEncodingBuilder()
                            .add("imei","357070055746475")
                            .add("userid", String.valueOf(0));

                    String   url = "http://114.215.108.130:80/EIMSKFB/"+
                    HttpRequestUrlConfig.LOGIN_URL;
                    return  OkHttp.doPost(formBuilder.build(),url);

                }
            }).subscribe(mSubscriber);
    }


    public   void   onCancel(){
        mSubscriber.unsubscribe();
    }


    Subscriber<ResultSub>   mSubscriber  =  new Subscriber<ResultSub>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(ResultSub resultSub) {
            System.out.println(resultSub.obj.toString());
        }
    };
}
