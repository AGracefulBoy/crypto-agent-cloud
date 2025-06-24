package org.dromara.model.platform;

import org.dromara.model.protocol.req.IChatRequest;
import org.dromara.model.protocol.resp.IChatResponse;
import org.springframework.ai.chat.messages.AssistantMessage;
import reactor.core.publisher.Flux;

/**
 * AI聊天服务接口
 */
public interface IChatService {

    /**
     * 流式聊天对话
     * @param iChatRequest 聊天请求参数
     * @return 返回流式的助手消息
     */
    Flux<IChatResponse> stream(IChatRequest iChatRequest);
}
