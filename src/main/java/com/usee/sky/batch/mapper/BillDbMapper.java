/**
 * 
 */
package com.usee.sky.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.usee.sky.model.Bill;
import com.usee.sky.model.User;

/**
 * @author kunrey
 *
 */
@Component("billDbMapper")
public class BillDbMapper implements RowMapper
{

	public Object mapRow(ResultSet rs, int i) throws SQLException
	{
		Bill b = new Bill();
		b.setId(rs.getLong("ID"));
		User u = new User();
		u.setId(rs.getLong("USER_ID"));
		u.setName(rs.getString("NAME"));
		u.setBalance(rs.getDouble("BALANCE"));
		b.setUser(u);
		b.setFees(rs.getDouble("FEES"));
		b.setPaidFees(rs.getDouble("PAID_FEES"));
		b.setUnpaidFees(rs.getDouble("UNPAID_FEES"));
		b.setPayStatus(rs.getInt("PAY_STATUS"));
		return b;
	}

}
