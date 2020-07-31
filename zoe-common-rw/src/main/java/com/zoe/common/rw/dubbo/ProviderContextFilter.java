package com.zoe.common.rw.dubbo;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.zoe.common.rw.DataSourceNameHolder;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author gaonengli
 * @date 2020/07/29
 */
@Activate(group = Constants.PROVIDER, order = -9999)
public class ProviderContextFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        boolean push = false;
        try {
            push = getAttachment(invoker, invocation);
            return invoker.invoke(invocation);
        } catch (RpcException e) {
            throw e;
        } finally {
            if (push) {
                DataSourceNameHolder.pop();
            }
        }
    }

    private boolean getAttachment(Invoker<?> invoker, Invocation invocation) {
        boolean push = false;
        Map<String, String> attachments = RpcContext.getContext().
                setInvoker(invoker).setInvocation(invocation).getAttachments();
        String dataSourceName = attachments.get(DataSourceNameKey.KEY);

        if (!StringUtils.isEmpty(dataSourceName)) {
            DataSourceNameHolder.push(dataSourceName);
            push = true;
        }
        return push;
    }
}
