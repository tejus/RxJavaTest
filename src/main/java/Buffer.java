import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class Buffer {
    void buffer() {
        CountDownLatch latch = new CountDownLatch(1);
        Observable.interval(1, TimeUnit.SECONDS)
                .buffer(2)
                .take(10)
                .subscribe(new Observer<List<Long>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        print("onSubscribe()");
                    }

                    @Override
                    public void onNext(List<Long> o) {
                        print("onNext()");
                        for (Long lng : o)
                            print(lng.toString());
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
