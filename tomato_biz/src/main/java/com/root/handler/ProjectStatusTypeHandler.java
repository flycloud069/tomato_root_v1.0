package com.root.handler;

import com.root.enums.ProjectStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ProjectStatus枚举类型处理器
 */
public class ProjectStatusTypeHandler extends BaseTypeHandler<ProjectStatus> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                  ProjectStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public ProjectStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String code = rs.getString(columnName);
        return code == null ? null : ProjectStatus.fromCode(code);
    }

    @Override
    public ProjectStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String code = rs.getString(columnIndex);
        return code == null ? null : ProjectStatus.fromCode(code);
    }

    @Override
    public ProjectStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String code = cs.getString(columnIndex);
        return code == null ? null : ProjectStatus.fromCode(code);
    }
}
