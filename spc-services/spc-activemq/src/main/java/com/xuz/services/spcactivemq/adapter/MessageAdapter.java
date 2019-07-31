package com.xuz.services.spcactivemq.adapter;

import com.alibaba.fastjson.JSONObject;

/**
 * 统一发送消息接口
 *
 * @author xuz
 * @date 2019/7/31 10:52 AM
 */
public interface MessageAdapter {
	void sendMsg(JSONObject body);
}
