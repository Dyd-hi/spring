package kr.or.member.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MemberRowMapper implements RowMapper{
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException{
		Member m = new Member();
		m.setMemberId(rs.getNString("member_id"));
		m.setMemberPw(rs.getString("member_pw"));
		m.setMemberName(rs.getNString("member_name"));
		m.setPhone(rs.getNString("phone"));
		m.setAddress(rs.getNString("address"));
		m.setGender(rs.getNString("gender"));
		return m;
	}
}
