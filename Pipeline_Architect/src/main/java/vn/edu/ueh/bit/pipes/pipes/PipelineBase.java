package vn.edu.ueh.bit.pipes.pipes;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ueh.bit.pipes.core.entities.IFilter;

public class PipelineBase<T> {
    private final List<IFilter<T>> filters = new ArrayList<>();

    public void registerFilter(IFilter<T> filter) {
        filters.add(filter);
    }

    public T processFilters(T input) {
        for (IFilter<T> filter : filters) {
            input = filter.execute(input);
        }
        return input;
    }
}
