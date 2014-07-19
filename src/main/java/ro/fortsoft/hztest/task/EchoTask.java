package ro.fortsoft.hztest.task;

import com.google.common.base.Objects;
import ro.fortsoft.hztask.common.task.Task;

/**
 * @author Serban Balamaci
 */
public class EchoTask extends Task<String, Void> {

    private Integer counter;

    public EchoTask(Integer counter) {
        this.counter = counter;
    }

    public Integer getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", getId())
                .add("counter", counter)
                .toString();
    }
}
