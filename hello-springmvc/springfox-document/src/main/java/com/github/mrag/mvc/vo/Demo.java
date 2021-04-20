package com.github.mrag.mvc.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * @author Gmw
 */
@ApiModel("数据类型DemoVO")
public class Demo {

    @ApiModelProperty("Int编码")
    private Integer intId;

    @ApiModelProperty("Long编码")
    private Long longId;

    @ApiModelProperty("JSR-310时间类型")
    private LocalDateTime localDateTime;

    @ApiModelProperty("内部类参数")
    private InnerClass innerClass;

    public Integer getIntId() {
        return intId;
    }

    public void setIntId(Integer intId) {
        this.intId = intId;
    }

    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public InnerClass getInnerClass() {
        return innerClass;
    }

    public void setInnerClass(InnerClass innerClass) {
        this.innerClass = innerClass;
    }

    @ApiModel("内部类")
    private static class InnerClass {

        @ApiModelProperty("内部类Comment")
        private String comment;

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }
}
