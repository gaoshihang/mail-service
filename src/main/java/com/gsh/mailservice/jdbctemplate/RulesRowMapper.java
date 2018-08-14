package com.gsh.mailservice.jdbctemplate;

import com.gsh.mailservice.enums.LevelEnum;
import com.gsh.mailservice.vo.RulesVo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RulesRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {

        RulesVo rulesVo = new RulesVo();
        rulesVo.setUserId(rs.getLong("userId"));
        rulesVo.setAlertId(rs.getInt("alertId"));
        rulesVo.setModule(rs.getString("module"));
        rulesVo.setSubject(rs.getString("subject"));
        rulesVo.setLevel(LevelEnum.valueOf(rs.getString("level")));
        rulesVo.setPattern(rs.getInt("pattern"));
        rulesVo.setType(rs.getInt("type"));
        rulesVo.setFrequency(rs.getInt("frequency_time"));
        rulesVo.setInterval(rs.getInt("interval_time"));

        return rulesVo;
    }
}
