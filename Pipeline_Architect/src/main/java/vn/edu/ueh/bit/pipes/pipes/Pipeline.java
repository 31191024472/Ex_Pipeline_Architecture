package vn.edu.ueh.bit.pipes.pipes;

import java.util.List;

import vn.edu.ueh.bit.pipes.core.entities.IFilter;
import vn.edu.ueh.bit.pipes.core.entities.IMessage;

public class Pipeline extends PipelineBase<IMessage> {
    public Pipeline(List<IFilter<IMessage>> filters) {
        for (IFilter<IMessage> filter : filters) {
            this.registerFilter(filter);
        }
    }
}
