package com.shan.mypubliclibrary.test;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import java.lang.ref.WeakReference;

/**
 * Created by root on 17-2-8.
 */

public class AsyncTaskTestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new MyAsyncTask(this).execute();
    }


    /********************************AsyncTask静态内部类**********************************/

    private static class MyAsyncTask extends AsyncTask<Void, Void, Void> {
        private WeakReference<Context> mWeakReference;

        public MyAsyncTask(Context context) {
            mWeakReference = new WeakReference<>(context);
        }

        @Override
        protected Void doInBackground(Void... params) {
            //处理耗时操作
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            AsyncTaskTestActivity taskTestActivity = (AsyncTaskTestActivity) mWeakReference.get();
            if (taskTestActivity != null) {
                //处理结果
            }
        }


    }
}
