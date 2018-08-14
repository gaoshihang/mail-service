package com.gsh.mailservice.dao.impl;

import com.gsh.mailservice.dao.RulesDao;
import com.gsh.mailservice.jdbctemplate.RulesRowMapper;
import com.gsh.mailservice.vo.RulesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 策略表dao接口实现类
 */
@Component
public class RulesDaoImpl implements RulesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertRules(RulesVo rulesVo) {
        String sql = "insert into rules(userId, alertId, module, subject, level, pattern, type, frequency_time, interval_time) values(?,?,?,?,?,?,?,?,?)";
        int number = jdbcTemplate.update(sql,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setLong(1, rulesVo.getUserId());
                        ps.setInt(2, rulesVo.getAlertId());
                        ps.setString(3, rulesVo.getModule());
                        ps.setString(4,rulesVo.getSubject());
                        ps.setString(5,rulesVo.getLevel().toString());
                        ps.setInt(6,rulesVo.getPattern());
                        ps.setInt(7, rulesVo.getType());
                        ps.setInt(8, rulesVo.getFrequency());
                        ps.setInt(9, rulesVo.getInterval());
                    }
                });
        if(number != 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public RulesVo findByUserAndAlertId(Long userId, Integer alertId) {

        String sql = "select * from rules where userId = ? and alertId = ?";
        RulesVo rulesVo = (RulesVo) jdbcTemplate.queryForObject(sql, new Object[]{userId, alertId}, new RulesRowMapper());
        return rulesVo;
    }
}
