package com.root.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.root.dto.ZhyyCheckOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CheckOrderMapper
 * @Author fuyunxiang
 * @Date 2021/4/9 11:20
 * @Description CheckOrderMapper
 * @Version 1.0
 */
@Mapper
public interface CheckOrderMapper extends BaseMapper<ZhyyCheckOrder> {

    List<ZhyyCheckOrder> list(@Param("visit_type") String visit_type, @Param("patient_name") String patient_name, @Param("start_date") String start_date, @Param("end_date") String end_date, @Param("hos_id") String hos_id, @Param("page") Integer page, @Param("size") Integer size);

    Map page(@Param("visit_type") String visit_type, @Param("patient_name") String patient_name, @Param("start_date") String start_date, @Param("end_date") String end_date, @Param("hos_id") String hos_id);
}
