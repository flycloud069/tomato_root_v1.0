
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//  欢迎来吃屎
//
package com.root.entity;

import com.baomidou.mybatisplus.annotation.Version;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Index;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.root.util.CreateCodeUtil;
import lombok.Data;


/**
 * @Description 基础类
 * @Authror GONG.SHE.NG
 * @Date 2020/8/6 18:46
 */

@Data
public class SysBaseEntity {

    //current_timestamp now()
    private static final String NOW = "CURRENT_TIMESTAMP";

    @Column(name="del_flg",type = MySqlTypeConstant.CHAR,defaultValue = "0",decimalLength = 1,comment ="状态")
    String delFlg = "0";

    @Column(name="sort_num",type = MySqlTypeConstant.INT,decimalLength = 10,defaultValue = "0",comment ="排序字段")
    Integer sortNum = 0;

    @Column(name="remark",type = MySqlTypeConstant.VARCHAR,decimalLength = 300,comment ="备注")
    String remark;

    @Index(value = "t_idx_create_time",columns = "create_time")
    @Column(name="create_time",type = MySqlTypeConstant.DATETIME, defaultValue = NOW,decimalLength = 20,comment ="创建时间")
    String createTime;

    @Column(name="update_time",type = MySqlTypeConstant.DATETIME,defaultValue = NOW,decimalLength = 20,comment ="上次修改时间")
    String updateTime;

    @Version
    @Column(name="version",type = MySqlTypeConstant.INT,decimalLength = 10,defaultValue = "0",comment ="版本号")
    Integer version;



     /* @Author GONG.SHE.NG
     * @param sysBaseEntity :
     * @return 自动生成代码 controller service serviceImpl mapper
     * @throws
     * @Date 2020/8/7 10:02
     */

    public void createCode(SysBaseEntity sysBaseEntity,String describe) throws Exception{
        CreateCodeUtil.createCode(sysBaseEntity,describe);
    }
}

