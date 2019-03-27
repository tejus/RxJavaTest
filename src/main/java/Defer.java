import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

class Defer {

    private String value;

    void defer() {
        Observable<String> valueObservable = valueObservable();
        setValue("Test value");
        valueObservable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                print("onSubscribe");
            }

            @Override
            public void onNext(String s) {
                print("onNext");
                print(s);
            }

            @Override
            public void onError(Throwable e) {
                print("onError");
            }

            @Override
            public void onComplete() {
                print("onComplete");
            }
        });
    }

    private void setValue(String value) {
        this.value = value;
    }

    private Observable<String> valueObservable() {
        return Observable.create(emitter -> {
            try {
                print("subscribe()");
                emitter.onNext(value);
            } catch (Exception e) {
                emitter.onError(e);
            }
            emitter.onComplete();
        });
    }

    private void print(String s) {
        System.out.println(s);
    }
}
