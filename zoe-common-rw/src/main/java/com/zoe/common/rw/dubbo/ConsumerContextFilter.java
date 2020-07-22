package com.zoe.common.rw.dubbo;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.zoe.common.rw.DataSourceNameHolder;
import org.springframework.stereotype.Component;

@Activate(group = "CONSUMER")
@Component
public class ConsumerContextFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result;
        try {
            setAttachment(invoker, invocation);
            result = invoker.invoke(invocation);
        } catch (RpcException e) {
            throw e;
        } finally {
            RpcContext.getContext().clearAttachments();
        }
        return result;
    }

    private void setAttachment(Invoker<?> invoker, Invocation invocation) {
        String dataSourceName = DataSourceNameHolder.peek();
        RpcContext.getContext()
                .setInvoker(invoker)
                .setInvocation(invocation)
                .setAttachment(DataSourceNameKey.KEY, dataSourceName);
    }
}
