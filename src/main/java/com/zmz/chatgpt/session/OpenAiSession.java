package com.zmz.chatgpt.session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zmz.chatgpt.model.ChatCompletionRequest;
import com.zmz.chatgpt.model.ChatCompletionSyncResponse;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * @author 小傅哥，微信：fustack
 * @description 会话服务接口
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public interface OpenAiSession {

    EventSource completions(ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException;

    CompletableFuture<String> completions(ChatCompletionRequest chatCompletionRequest) throws InterruptedException;

    ChatCompletionSyncResponse completionsSync(ChatCompletionRequest chatCompletionRequest) throws IOException;

}
