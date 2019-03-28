import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.List;

class Buffer {
    void buffer() {
        Observable.range(1, 5)
                .repeat(2)
                .buffer(2)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        print("onSubscribe()");
                    }

                    @Override
                    public void onNext(List<Integer> o) {
                        print("onNext()");
                        for (Integer intg : o)
                            print(intg.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        print("onError()");
                        print(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        print("onComplete()");
                    }
                });
    }

    private void print(String s) {
        System.out.println(s);
    }
}
