package cn.esctasy.qqchat.core.chain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 责任链
 */
@Slf4j
@Data
public abstract class Handle {

    /**
     * 责任链下一个指向
     */
    private Handle next;

    /**
     * 执行
     *
     * @param code     code
     * @param metadata 元数据字符串
     */
    public abstract void handling(String code, String metadata);

    /**
     * 执行下一责任节点
     */
    public void goNext(String code, String metadata) {
        if (null == this.getNext()) {
            log.info("责任链已经到但是最后仍然没有可执行的逻辑，node：{}， code：{}，metadata：{}", this.getClass().getName(), code, metadata);
            return;
        }

        this.getNext().handling(code, metadata);
    }
}
