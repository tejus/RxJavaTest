import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.List;

class Buffer {
    void buffer() {
        Observable.fromArray("A", "B", "C", "D", "E", "F", "G", "H")
                .buffer(2)
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        print("onSubscribe()");
                    }

                    @Override
                    public void onNext(List<String> o) {
                        print("onNext()");
                        for (String string : o)
                            print(string);
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
