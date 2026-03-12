package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

public interface OrderService  {

    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo
     */
    /**
     * 支付成功处理方法
     * @param outTradeNo 外部交易号
     */
    void paySuccess(String outTradeNo);


    /**
     * 分页查询用户订单
     * @param page 页码
     * @param pageSize 每页大小
     * @param status 订单状态
     * @return 分页结果
     */
    PageResult pageQueryUser(Integer page, Integer pageSize, Integer status);

    /**
     * 查询订单详情
     * @param id 订单ID
     * @return 订单VO对象
     */
    OrderVO details(Long id);

    /**
     * 用户根据ID取消订单
     * @param id 订单ID
     * @throws Exception 取消订单异常
     */
    void userCancelById(Long id) throws Exception;

    /**
     * 重复下单
     * @param id 原订单ID
     */
    void repetition(Long id);

    /**
     * 条件搜索订单
     * @param ordersPageQueryDTO 订单分页查询DTO
     * @return 分页结果
     */
    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 统计订单数据
     * @return 订单统计VO
     */
    OrderStatisticsVO statistics();
    /**
     * 确认订单
     * @param ordersConfirmDTO 订单确认DTO
     */
    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    void rejection(OrdersRejectionDTO ordersRejectionDTO) throws Exception;

    void Cancel(OrdersCancelDTO ordersCancelDTO);

    void delivery(Long id);

    void complete(Long id);

    void reminder(Long id);
}
