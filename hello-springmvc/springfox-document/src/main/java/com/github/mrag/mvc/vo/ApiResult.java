package com.github.mrag.mvc.vo;

import com.github.mrag.mvc.common.RequestHolder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author Gmw
 */
@ApiModel("统一返回响应体")
public class ApiResult<R> {
    private static final Logger log = LoggerFactory.getLogger(ApiResult.class);
    private static final int OK = 1000;
    private static final int FAIL = 2000;

    public static <R> ApiResult<R> ok(R data) {
        ApiResult<R> result = new ApiResult<>();
        result.setCode(OK);
        result.setData(data);
        return result;
    }

    public static ApiResult<String> fail(String message) {
        ApiResult<String> result = new ApiResult<>();
        result.setCode(OK);
        result.setMessage(message);
        return result;
    }

    public static ApiResult<String> fail(Throwable ex) {
        log.error("Error: ", ex);
        ApiResult<String> result = new ApiResult<>();
        result.setCode(OK);
        result.setMessage(ex.getMessage());
        return result;
    }

    @ApiModelProperty("返回码 (成功: 1000; 错误: 其他)")
    private int code;

    @ApiModelProperty("错误信息")
    private String message;

    @ApiModelProperty("调用时间戳")
    private LocalDateTime invokeTimestamp;

    @ApiModelProperty("响应数据")
    private R data;

    @ApiModelProperty("调用者IP")
    private String invokerIp;

    {
        HttpServletRequest request = RequestHolder.get();
        if (request != null) {
            invokerIp = request.getRemoteHost();
        }
    }

    public ApiResult() {
        invokeTimestamp = LocalDateTime.now();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getInvokeTimestamp() {
        return invokeTimestamp;
    }

    public void setInvokeTimestamp(LocalDateTime invokeTimestamp) {
        this.invokeTimestamp = invokeTimestamp;
    }

    public R getData() {
        return data;
    }

    public void setData(R data) {
        this.data = data;
    }

    public String getInvokerIp() {
        return invokerIp;
    }

    public void setInvokerIp(String invokerIp) {
        this.invokerIp = invokerIp;
    }
}
