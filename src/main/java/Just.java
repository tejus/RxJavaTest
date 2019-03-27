import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Just {

    void just() {
        String[] value = {"Test value 1", "Test value 2"};
        Observable.just(value)
                .subscribe(new Observer<String[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        print("onSubscribe()");
                    }

                    @Override
                    public void onNext(String[] o) {
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
