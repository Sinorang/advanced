package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    public AbstractTemplate(LogTrace trace) {
        this.trace = trace;
    }

    /**
     * 템플릿 메서드 패턴:
     * 변하지 않는 부분을 부모 클래스에
     * 변하는 부분을 자식 클래스에 두어서 상속을 사용하여 구현
     *
     * @param message
     * @return
     */
    public T execute(String message) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);

            T result = call(); // 상속으로 구현

            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();
}
