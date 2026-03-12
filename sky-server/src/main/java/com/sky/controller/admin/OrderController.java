package com.sky.controller.admin;

import com.sky.dto.OrdersCancelDTO;
import com.sky.dto.OrdersConfirmDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersRejectionDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminOrderController")
@RequestMapping("/admin/order")
@Api(tags = "订单管理接口")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

       /**
     * 订单条件搜索接口
     * 根据传入的查询条件分页搜索订单信息
     * @param ordersPageQueryDTO 订单分页查询条件数据传输对象，包含搜索关键字、状态、时间范围等查询参数
     * @return 返回分页查询结果，包含订单列表和分页信息
     */
    @RequestMapping("/conditionSearch")
    @ApiOperation("订单搜索")
    public Result<PageResult> searchOrder(OrdersPageQueryDTO ordersPageQueryDTO){
       PageResult pageResult = orderService.conditionSearch(ordersPageQueryDTO);
       return Result.success(pageResult);

    }

    /**
     * 统计各个状态的订单数量
     * 查询并返回待接单、待配送、配送中等各状态订单的统计数量
     * @return 返回订单统计结果，包含各种状态对应的订单数量
     */
    @GetMapping("/statistics")
    @ApiOperation("各个状态的订单数量统计")
    public Result<OrderStatisticsVO> statistics(){
      OrderStatisticsVO orderStatisticsVO = orderService.statistics();
      return Result.success(orderStatisticsVO);
    }

    /**
     * 获取订单详情信息
     * 根据订单ID查询订单的详细信息，包括订单基本信息、商品信息、用户信息等
     * @param id 订单ID
     * @return 返回订单详情信息
     */
    @GetMapping("/details/{id}")
    @ApiOperation("订单详情")
    public Result<OrderVO> details(@PathVariable ("id") Long id){
        OrderVO orderVO = orderService.details(id);
        return Result.success(orderVO);
    }

    /**
     * 确认接单接口
     * 商家确认接收订单，更新订单状态为已接单
     * @param ordersConfirmDTO 订单确认数据传输对象，包含订单ID和确认相关信息
     * @return 操作结果，成功则返回成功状态
     */
    @PutMapping("/confirm")
    @ApiOperation("接单")
    public Result confirm(@RequestBody OrdersConfirmDTO ordersConfirmDTO){
        orderService.confirm(ordersConfirmDTO);
        return Result.success();
    }
    /**
     * 拒单
     *
     * @return
     */
    @PutMapping("/rejection")
    @ApiOperation("拒单")
    public Result rejection(@RequestBody OrdersRejectionDTO ordersRejectionDTO) throws Exception {
        orderService.rejection(ordersRejectionDTO);
        return Result.success();
    }
    /**
     * 取消订单接口
     * 允许用户取消订单，更新订单状态为取消
     * @param
     * @return 操作结果，成功则返回成功状态
     */
    @PutMapping("/cancel")
    @ApiOperation("取消订单")
    public Result cancel(@RequestBody OrdersCancelDTO ordersCancelDTO){
        orderService.Cancel(ordersCancelDTO);
        return Result.success();
    }
    /**
     * 派送订单
     *
     * @return
     */
    @PutMapping("/delivery/{id}")
    @ApiOperation("派送订单")
    public Result delivery(@PathVariable("id") Long id){
        orderService.delivery(id);
        return Result.success();
    }
    /**
     * 完成订单
     *
     * @return
     */
    @PutMapping("/complete/{id}")
    @ApiOperation("完成订单")
    public Result cancel(@PathVariable("id") Long id){
        orderService.complete(id);
        return Result.success();
    }
}
