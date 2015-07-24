package rx.android.samples;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;

import android.util.Log;
import android.view.View;
import com.squareup.okhttp.FormEncodingBuilder;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.android.samples.http.OkHttp;
import rx.android.samples.jobs.RxUploadJob;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.schedulers.HandlerScheduler;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action1;
import rx.functions.Func0;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

public class MainActivity extends Activity {
    private static final String TAG = "RxAndroidSamples";

    private Handler backgroundHandler;



    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        BackgroundThread backgroundThread = new BackgroundThread();
        backgroundThread.start();
        backgroundHandler = new Handler(backgroundThread.getLooper());

//        mObservable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(new Func1<String, Integer>() {
//                    @Override
//                    public Integer call(String s) {
//                        System.out.println("call="+s);
//                        return Integer.parseInt(s);
//                    }
//                })
//                .subscribe(mSubscriber);


        findViewById(R.id.scheduler_example).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRunSchedulerExampleButtonClicked();
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxUploadJob   job  =  new RxUploadJob();
            }
        });
    }

    void onRunSchedulerExampleButtonClicked() {
        sampleObservable()
                // Run on a background thread
                .subscribeOn(HandlerScheduler.from(backgroundHandler))
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override public void onCompleted() {
                        Log.d(TAG, "onCompleted()");
                    }

                    @Override public void onError(Throwable e) {
                        Log.e(TAG, "onError()", e);
                    }

                    @Override public void onNext(String string) {
                        Log.d(TAG, "onNext(" + string + ")");
                    }
                });
    }

    static Observable<String> sampleObservable() {
        return Observable.defer(new Func0<Observable<String>>() {
            @Override public Observable<String> call() {
                try {
                    // Do some long running operation
                    Thread.sleep(TimeUnit.SECONDS.toMillis(5));
                } catch (InterruptedException e) {
                    throw OnErrorThrowable.from(e);
                }
                return Observable.just("one", "two", "three", "four", "five");
            }
        });
    }

    static class BackgroundThread extends HandlerThread {
        BackgroundThread() {
            super("SchedulerSample-BackgroundThread", THREAD_PRIORITY_BACKGROUND);
        }
    }


    //事件源
    Observable<String>   mObservable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("123");
            System.out.println("线程1：" + Thread.currentThread().getId());


         //   OkHttp.downFile("http://114.215.108.130/EIMSKFB/rootImage/waterMeter/objectEntity/1242/objectVideo/141video9070120150723154414710.mp4",subscriber);

        }
    }) ;


    Observable<ResultSub>   resultSubObservable = Observable.create(new Observable.OnSubscribe<ResultSub>() {
        @Override
        public void call(Subscriber<? super ResultSub> subscriber) {

            FormEncodingBuilder formBuilder = new FormEncodingBuilder()
                    .add("imei","111111")
                    .add("userid", String.valueOf(0));


            String   url = "http://114.215.108.130:80/EIMSKFB";
            OkHttp.doPost(formBuilder.build(),RequestModel.FILE,url,subscriber);
        }
    });


    Subscriber<Integer>  mSubscriber  =  new Subscriber<Integer>() {
        @Override
        public void onCompleted() {
            System.out.println("onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("eeeee");
        }

        @Override
        public void onNext(Integer s) {
            System.out.println("线程2："+Thread.currentThread().getId());
           System.out.println(s);
        }
    };


    Subscriber<ResultSub>   requestSub = new Subscriber<ResultSub>() {
        @Override
        public void onCompleted() {
              System.out.println("onCompleted");
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(ResultSub resultSub) {
            if(resultSub.requestModel == RequestModel.DATA){
                System.out.println("over");
            }else{

            }
        }
    };


    Action1<String>  onNext = new Action1<String>() {
        @Override
        public void call(String s) {
            System.out.println(Thread.currentThread().getId());
            System.out.println(s);
        }
    };

}
