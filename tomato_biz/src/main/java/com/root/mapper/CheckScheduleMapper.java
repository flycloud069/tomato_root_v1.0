package com.root.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.root.dto.ZhyyCheckOrder;
import com.root.dto.ZhyyCheckSchedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CheckScheduleMapper
 * @Author fuyunxiang
 * @Date 2021/4/9 11:21
 * @Description CheckScheduleMapper
 * @Version 1.0
 */
@Mapper
public interface CheckScheduleMapper extends BaseMapper<ZhyyCheckSchedule> {
    List<ZhyyCheckOrder> list(@Param("visit_type") String visit_type, @Param("start_data") String start_data, @Param("end_data") String end_data, @Param("visit_time") String visit_time, @Param("hos_id") String hos_id, @Param("page") Integer page, @Param("size") Integer size);
    void delete(@Param("id") String[] id);
    Map page(@Param("visit_type") String visit_type, @Param("start_data") String start_data, @Param("end_data") String end_data, @Param("visit_time") String visit_time, @Param("hos_id") String hos_id);
}
