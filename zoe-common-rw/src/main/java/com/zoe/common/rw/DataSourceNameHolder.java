package com.zoe.common.rw;

import org.springframework.util.StringUtils;

import java.util.LinkedList;

/**
 * @author gaonl
 * @date 2020/07/17
 */
public class DataSourceNameHolder {
    public static final ThreadLocal<DataSourceNameStack> DATA_SOURCE_NAME_STACK_HOLDER = new ThreadLocal<>();

    public static void push(String name) {
        initStack();
        DATA_SOURCE_NAME_STACK_HOLDER.get().push(name);
    }

    public static String peek() {
        initStack();
        return DATA_SOURCE_NAME_STACK_HOLDER.get().peek();
    }

    public static void pop() {
        initStack();
        DATA_SOURCE_NAME_STACK_HOLDER.get().pop();
    }

    private static void initStack() {
        DataSourceNameStack stack = DATA_SOURCE_NAME_STACK_HOLDER.get();
        if (stack == null) {
            stack = new DataSourceNameStack();
            DATA_SOURCE_NAME_STACK_HOLDER.set(stack);
        }
    }

    /**
     * 栈的简单实现
     */
    static final class DataSourceNameStack {
        private LinkedList<String> data = new LinkedList<>();

        //栈判空
        public boolean isEmpty() {
            return data.size() == 0;
        }

        //入栈
        public void push(String value) {
            data.push(value);
        }

        //出栈, 并将栈顶的数据返回
        public String pop() {
            if (isEmpty()) {
                return null;
            }
            return data.pop();
        }

        //获取栈顶数据
        public String peek() {
            return data.peek();
        }
    }
}
