import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

class Defer {

    private String value;

    void defer() {
        Observable<String> valueObservable = valueObservable();
        if (valueObservable == null) {
            print("valueObservable is null, returning");
            return;
        }
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
        try {
            return Observable.just(value);
        } catch (NullPointerException e) {
            print("NPE caught!");
            return null;
        }
    }

    private void print(String s) {
        System.out.println(s);
    }
}
