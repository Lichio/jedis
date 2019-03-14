package redis.clients.jedis;

import java.util.LinkedList;
import java.util.Queue;

public class Queable {
  private Queue<Response<?>> pipelinedResponses = new LinkedList<Response<?>>();

  protected void clean() {
    pipelinedResponses.clear();
  }

  protected Response<?> generateResponse(Object data) {
    Response<?> response = pipelinedResponses.poll();
    if (response != null) {
      response.set(data);
    }
    return response;
  }

  /**
   * 每一条指令对应一个结果，该方法为每一条指令添加一个对应的Response
   * 返回的结果通过
   * @see Queable#generateResponse(java.lang.Object)
   * 方法注入到对应的Response中
   * @param builder
   * @param <T>
   * @return
   */
  protected <T> Response<T> getResponse(Builder<T> builder) {
    Response<T> lr = new Response<T>(builder);
    pipelinedResponses.add(lr);
    return lr;
  }

  protected boolean hasPipelinedResponse() {
    return !pipelinedResponses.isEmpty();
  }

  protected int getPipelinedResponseLength() {
    return pipelinedResponses.size();
  }
}
