package org.dromara.agent.controller;

import cn.dev33.satoken.apikey.SaApiKeyUtil;
import cn.dev33.satoken.apikey.annotation.SaCheckApiKey;
import cn.dev33.satoken.apikey.model.ApiKeyModel;
import org.dromara.model.factory.AiService;
import org.dromara.model.platform.IChatService;
import org.dromara.model.protocol.req.IChatRequest;
import org.dromara.model.protocol.resp.IChatResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class AiModelChatController {

    @SaCheckApiKey
    @PostMapping(path = "/chat", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Object chat(@RequestBody IChatRequest iChatRequest) {
        ApiKeyModel apiKeyModel = SaApiKeyUtil.createApiKeyModel();

        IChatService chatService = AiService.getChatService(iChatRequest.getCode());

        if (iChatRequest.getStream() != null && iChatRequest.getStream()) {
            // 流式响应：直接返回 Flux，Spring Boot 会自动处理为 SSE
            return chatService.stream(iChatRequest);
        } else {
            // 非流式响应：返回普通 JSON 对象
            // 确保设置为非流式
            iChatRequest.setStream(false);
            return chatService.stream(iChatRequest).blockFirst();
        }
    }
}
