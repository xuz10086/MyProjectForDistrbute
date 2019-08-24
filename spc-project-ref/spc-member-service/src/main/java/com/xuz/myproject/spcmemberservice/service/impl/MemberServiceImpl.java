package com.xuz.myproject.spcmemberservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xuz.myproject.base.JsonResult;
import com.xuz.myproject.base.JsonResultFactory;
import com.xuz.myproject.constants.Constants;
import com.xuz.myproject.spcbaseapi.service.MemberService;
import com.xuz.myproject.spcbasedomain.db1.pojo.dao.UserDO;
import com.xuz.myproject.spcbaseservice.service.api.UserBaseService;
import com.xuz.myproject.spcmemberservice.mq.ActiveMqProducer;
import com.xuz.myproject.spcmemberservice.mq.RabbitMqPoducer;
import com.xuz.myproject.utils.RedisUtil;
import com.xuz.myproject.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Autowired
    private UserBaseService userBaseService;
    @Value("${email.messages.queue}")
    private String MESSAGESQUEUE;
    @Autowired
    private ActiveMqProducer activeMqProducer;
    @Autowired
    private RabbitMqPoducer rabbitMqPoducer;

    /**
     * 用户登陆
     *
     * @param userDO
     * @return com.xuz.myproject.base.JsonResult
     * @author xuz
     * @date 2019/7/29 8:55 AM
     */
    @Override
    public JsonResult doLogin(@RequestBody UserDO userDO) {
        UserDO result = userBaseService.queryUserOne(userDO);
        if (null == result) {
            log.error("####用户登陆： 用户名或密码错误");
            return JsonResultFactory.setResultError("用户名或密码错误");
        }
        // 登陆成功，则生成Token并保存到redis中
        result.setVcUserPassword("");
        String loginToke = TokenUtils.getToken("Login");
        RedisUtil.set(loginToke, result, Constants.TOKEN_MEMBER_TIME);
        log.info("####用户信息token存放在redis中... key为:{},value为:{}", loginToke, result.toString());
        // 返回Token
        return JsonResultFactory.setResultSuccess(loginToke);
    }

    /**
     * 用户注册
     *
     * @param userDO
     * @return com.xuz.myproject.base.JsonResult
     * @author xuz
     */
    @Override
    public JsonResult regist(@RequestBody UserDO userDO) {
        //1. 注册用户
        userBaseService.insertUser(userDO);

        //2. 发送邮件
        //2.1 接收方邮件帐号
        String email = userDO.getEmail();
        //2.2 发送信息参数设置
        String json = emailJson(email);
        //2.3 推送消息到消息服务平台
        log.info("####会员服务推送消息到消息服务平台####json:{}", json);
        sendMsgToAcitveMq(json);
        sendMsgToRabbitMq(json);

        return JsonResultFactory.setResultSuccess("注册成功！");
    }

    /**
     * 推送消息到消息服务平台(ActiveMq)
     *
     * @param json
     * @return void
     * @author xuz
     * @date 2019/7/31 10:00 AM
     */
    private void sendMsgToAcitveMq(String json) {
        ActiveMQQueue activeMQQueue = new ActiveMQQueue(MESSAGESQUEUE);
        activeMqProducer.sendMsg(activeMQQueue, json);
    }

    /**
     * 推送消息到消息服务平台(RabbitMq)
     *
     * @param json
     * @return void
     * @author xuz
     */
    private void sendMsgToRabbitMq(String json) {
        rabbitMqPoducer.sendMail(MESSAGESQUEUE, json);
    }


    /**
     * 发送信息参数设置
     *
     * @param email
     * @return java.lang.String
     * @author xuz
     * @date 2019/7/31 9:45 AM
     */
    private String emailJson(String email) {
        JSONObject rootJson = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("interfaceType", Constants.MSG_EMAIL);
        JSONObject acceptor = new JSONObject();
        acceptor.put("email", email);
        rootJson.put("header", header);
        rootJson.put("acceptor", acceptor);
        return rootJson.toJSONString();
    }


}
