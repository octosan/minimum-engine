package pl.nag;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ProgressBar;

/**
 * @author ts
 */
public class TimeLeftTimer extends CountDownTimer {

    private static final String TAG = TimeLeftTimer.class.getSimpleName();
    private static final int MAX_SECONDS = 6;
    private static final int COUNT_DOWN_INTERVAL_MS = 50;
    private static final int ONE_SECOND_MS = 1000;

    private ProgressBar progressBar;
    private OnFinishCallback onFinishCallback;

    public TimeLeftTimer(ProgressBar progressBar, OnFinishCallback onFinishCallback) {
        super(getMillisInFuture(), COUNT_DOWN_INTERVAL_MS);
        this.progressBar = progressBar;
        this.onFinishCallback = onFinishCallback;
    }

    private static int getMillisInFuture() {
        return ONE_SECOND_MS * MAX_SECONDS;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        int progress = (int) ((getMillisInFuture() - millisUntilFinished) / 10 / MAX_SECONDS);
        //Log.d(TAG, "progress" + progress + " millisUntilFinished=" + millisUntilFinished);
        progressBar.setProgress(100 - progress);
    }

    @Override
    public void onFinish() {
        progressBar.setProgress(0);
        onFinishCallback.onFinish();
    }


}
