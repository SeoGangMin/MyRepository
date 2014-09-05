package com.cattle.member.dao;

import java.sql.SQLException;
import java.util.List;

import com.cattle.member.model.Member;

public interface MemberDao {
	public List<Member> getMemberInfo() throws SQLException;
}
