package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.GoodsSalesDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    // 插入数据
    void insert(Orders orders);

    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    void update(Orders orders);

    @Update("update orders set status = #{orderStatus},pay_status = #{orderPaidStatus} ,checkout_time = #{check_out_time} " +
            "where number = #{orderNumber}")
    void updateStatus(Integer orderStatus, Integer orderPaidStatus, LocalDateTime check_out_time, String orderNumber);

    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    @Select("select * from orders where id=#{id}")
    Orders getById(Long id);

    @Select("select count(id) from orders where status= #{status}")
    Integer countStatus(Integer status);

    @Select("select * from orders where status= #{status} and order_time < #{orderTime}")
    List<Orders> getByStatusAndOrderTime(Integer status,LocalDateTime orderTime);

    @Select("select * from orders where number=#{number} and user_id=#{userId}")
    Orders getByNumberAndUserId(String number, Long userId);

    //
    /**
     * 根据提供的Map计算总和值
     * @param map 包含数值数据的Map对象
     * @return 计算得到的总和值，类型为Double
     */
    Double sumByMap(Map map);

    /**
     * 根据提供的Map统计数量
     * @param map 包含数据的Map对象
     * @return 统计得到的数量值，类型为Integer
     */
    Integer countByMap(Map map);

    /**
     * 统计指定时间区间内的销量排名 top n
     * @param begin
     * @param end
     * @return
     */
    List<GoodsSalesDTO> getSalesTop(LocalDateTime begin, LocalDateTime end);

}
