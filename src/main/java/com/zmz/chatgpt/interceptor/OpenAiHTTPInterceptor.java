package com.zmz.chatgpt.interceptor;

import com.zmz.chatgpt.session.Configuration;
import com.zmz.chatgpt.utils.BearerTokenUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author 小傅哥，微信：fustack
 * @description 接口拦截器
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class OpenAiHTTPInterceptor implements Interceptor {

    /**
     * 智普Ai，Jwt加密Token
     */
    private final Configuration configuration;

    public OpenAiHTTPInterceptor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public @NotNull Response intercept(Chain chain) throws IOException {
        // 1. 获取原始 Request
        Request original = chain.request();
        // 2. 构建请求
        Request request = original.newBuilder()
                .url(original.url())
                .header("Authorization", "Bearer " + BearerTokenUtils.getToken(configuration.getApiKey(), configuration.getApiSecret()))
                .header("Content-Type", Configuration.JSON_CONTENT_TYPE)
                .header("User-Agent", Configuration.DEFAULT_USER_AGENT)
                .header("Accept", null != original.header("Accept") ? original.header("Accept") : Configuration.SSE_CONTENT_TYPE)
                .method(original.method(), original.body())
                .build();

        // 3. 返回执行结果
        return chain.proceed(request);
    }

    public static void main(String[] args) {
        /**
         * 12fce155c62df5e35de7a7607af7b745.GQOg54PaVcuRJGlV
         * c8ebb130fc7fcf11c6fb826f6b55c412.dFYCXMJs3P6QDotr
         */
        String tok = BearerTokenUtils.getToken("12fce155c62df5e35de7a7607af7b745.GQOg54PaVcuRJGlV",
                "dFYCXMJs3P6QDotr"
        );
        System.err.println(tok);
    }

}
