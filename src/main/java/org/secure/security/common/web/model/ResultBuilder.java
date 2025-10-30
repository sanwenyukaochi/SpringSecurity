package org.secure.security.common.web.model;


public final class ResultBuilder {

  private final Result result;

  private ResultBuilder() {
    result = new Result();
  }

  public static ResultBuilder aResult() {
    return new ResultBuilder();
  }

  public ResultBuilder code(String code) {
    result.setCode(code);
    return this;
  }

  public ResultBuilder msg(String msg) {
    // 使用Builder方式构建Result对象，翻译响应数据
    result.setMessage(I18nMessageTool.translate(msg));
    return this;
  }

  public <T> ResultBuilder data(T data) {
    result.setData(data);
    return this;
  }

  public Result build() {
    return result;
  }
}
