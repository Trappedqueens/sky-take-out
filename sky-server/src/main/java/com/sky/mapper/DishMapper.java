package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

       /**
     * 插入菜品信息
     * @param dish 要插入的菜品对象
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);



    /**
     * 分页查询菜品信息
     * @param dishPageQueryDTO 菜品分页查询条件对象
     * @return 菜品分页查询结果
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);



    /**
     * 根据ID查询菜品信息
     * @param id 菜品ID
     * @return 菜品对象
     */
    Dish getById(Long id);


    /**
     * 根据ID批量删除菜品
     * @param ids 要删除的菜品ID集合
     */
    void deleteByIds(List<Long> ids);


    /**
     * 根据菜品ID查询菜品信息及其口味信息
     * @param dishId 菜品ID
     * @return 包含口味信息的菜品VO对象
     */
    DishVO getByIdWithFlavor(Long dishId);

    /**
     * 更新菜品信息
     * @param dish 要更新的菜品对象
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    /**
     * 根据条件查询菜品列表
     * @param dish 查询条件对象
     * @return 菜品列表
     */
    List<Dish> list(Dish dish);

    /**
     * 根据套餐ID查询包含的菜品信息
     * @param id 套餐ID
     * @return 该套餐包含的菜品列表
     */
    @Select("select a.* from dish a left join setmeal_dish b on a.id = b.dish_id where b.setmeal_id = #{setmealId}")
    List<Dish> getBySetmealId(Long id);

    /**
     * 根据条件统计菜品数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);

}
