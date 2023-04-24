package com.codingbox.core2.member.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.codingbox.core2.member.dto.Member;
import com.codingbox.core2.repository.MemberRepository;

//@Repository
public class JdbcMemberRepository implements MemberRepository{

	private final DataSource dataSource;
	public JdbcMemberRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public Member save(Member member) {
		String sql = "INSERT INTO MEMBER VALUES(member_seq.nextval, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String generatedColumns[] = {"ID"};
			pstm = conn.prepareStatement(sql, generatedColumns);
			pstm.setString(1, member.getName());
			pstm.executeUpdate();
			rs = pstm.getGeneratedKeys();
			
			if(rs.next()) {
				member.setId(rs.getInt(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstm.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}

	@Override
	public List<Member> findAll() {
		String sql = "select * from member";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Member> members = null;
		try {
			conn = dataSource.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			members = new ArrayList<>();
			while(rs.next()) {
				Member member = new Member();
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				members.add(member);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstm.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return members;
	}

}
