package com.zoe.common.rw.dubbo;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.zoe.common.rw.DataSourceNameHolder;
import org.springframework.util.StringUtils;

import java.util.Map;

@Activate(group = Constants.PROVIDER, order = -9999)
public class ProviderContextFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        getAttachment(invoker, invocation);
        return invoker.invoke(invocation);
    }

    private void getAttachment(Invoker<?> invoker, Invocation invocation) {
        Map<String, String> attachments = RpcContext.getContext().
                setInvoker(invoker).setInvocation(invocation).getAttachments();
        String dataSourceName = attachments.get(DataSourceNameKey.KEY);
        if (!StringUtils.isEmpty(dataSourceName)) {
            DataSourceNameHolder.push(dataSourceName);
        }
    }
}
