import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.ArrayList;
import java.util.List;

class Create {

    static void create() {
        final List<String> alphabets = new ArrayList<>();
        alphabets.add("a");
        alphabets.add("b");
        alphabets.add("c");
        alphabets.add("d");
        alphabets.add("e");
        alphabets.add("f");
        alphabets.add("g");
        alphabets.add("h");

        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                print("subscribe()");
                try {
                    for (String alphabet : alphabets) {
                        emitter.onNext(alphabet);
                    }
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                print("onSubscribe()");
            }

            @Override
            public void onNext(String o) {
                print("onNext()");
                print(o);
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
        };

        observable.subscribe(observer);
    }

    private static void print(String s) {
        System.out.println(s);
    }
}
