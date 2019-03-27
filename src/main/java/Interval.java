import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class Interval {

    void interval() {
        CountDownLatch latch = new CountDownLatch(1);

        Observable.interval(1, TimeUnit.SECONDS)
                .take(10)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        print("onSubscribe()");
                    }

                    @Override
                    public void onNext(Long o) {
                        print("onNext()");
                        print(o.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        print("onError()");
                        print(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        print("onComplete()");
                        latch.countDown();
                    }
                });

        try {
            latch.await();
        } catch (InterruptedException e) {
            print("Interrupted exception caught!");
            print(e.getMessage());
        }
    }

    private void print(String s) {
        System.out.println(s);
    }
}
