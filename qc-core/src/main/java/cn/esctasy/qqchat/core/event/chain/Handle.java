package cn.esctasy.qqchat.core.event.chain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * 责任链
 */
@Slf4j
@Getter
@Setter
@Accessors(chain = true)
public abstract class Handle {

    /**
     * 责任链下一个同级指向
     */
    private Handle next;


    /**
     * 责任链下一个子级指向
     */
    private Handle child;

    /**
     * 责任链入口
     *
     * @param metadata 元数据字符串
     */
    public void inlet(String metadata) {
        if (!metadata.contains(keyword())) {
            this.goNext(metadata);
            return;
        }

        this.handling(metadata);
    }

    /**
     * 执行
     *
     * @param metadata 元数据字符串
     */
    public abstract void handling(String metadata);

    /**
     * 执行下一责任节点
     */
    public void goNext(String metadata) {
        if (null == this.getNext()) {
            log.info("责任链已经到但是最后仍然没有可执行的逻辑，node：{}，metadata：{}", this.getClass().getName(), metadata);
            return;
        }

        this.getNext().inlet(metadata);
    }

    /**
     * 进入责任链子链
     */
    public void goChild(String metadata) {
        if (null == this.getChild()) {
            log.info("当前责任链节点并没有子节点可以调用，node：{}，metadata：{}", this.getClass().getName(), metadata);
            return;
        }

        this.getChild().inlet(metadata);
    }

    /**
     * 判断是否命中的关键词
     */
    public abstract String keyword();
}
