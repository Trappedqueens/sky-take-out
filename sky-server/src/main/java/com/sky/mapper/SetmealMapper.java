package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类id查询套餐的数量
     * @param id
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

/**
 * 插入套餐信息
 * @param setmeal 套餐对象，包含套餐的基本信息
 */
@AutoFill(value = OperationType.INSERT)
void insert(Setmeal setmeal);
/**
 * 分页查询套餐信息
 * @param setmealPageQueryDTO 套餐分页查询条件对象，包含查询参数
 * @return 返回分页查询结果，包含套餐VO列表和分页信息
 */
Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);
/**
 * 根据ID查询套餐信息
 * @param id 套餐ID
 * @return 返回对应的套餐对象
 */
@Select("select * from setmeal where id = #{id}")
Setmeal getById(Long id);
/**
 * 根据ID删除套餐信息
 * @param setmealId 要删除的套餐ID
 */
@Delete("delete from setmeal where id = #{id}")
void deleteById(Long setmealId);
/**
 * 更新套餐信息
 * @param setmeal 套餐对象，包含要更新的套餐信息
 */
@AutoFill(value = OperationType.UPDATE)
void update(Setmeal setmeal);
/**
 * 根据条件查询套餐列表
 * @param setmeal 查询条件对象，包含筛选条件
 * @return 返回符合条件的套餐列表
 */
List<Setmeal> list(Setmeal setmeal);
/**
 * 根据套餐ID查询包含的菜品信息
 * 通过联表查询获取套餐对应的菜品名称、份数、图片和描述信息
 * @param setmealId 套餐ID
 * @return 返回菜品项信息列表
 */
@Select("select sd.name, sd.copies, d.image, d.description " +
        "from setmeal_dish sd left join dish d on sd.dish_id = d.id " +
        "where sd.setmeal_id = #{setmealId}")
List<DishItemVO> getDishItemBySetmealId(Long setmealId);

    /**
     * 根据条件统计套餐数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
