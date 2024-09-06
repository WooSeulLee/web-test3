package org.web.test.vo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import common.DBCon;

public class UserRepository2 {
	public List<UserVO> selectUsers(UserVO user){
		List<UserVO> users = new ArrayList<>();
		String sql = "SELECT * FROM user_info WHERE 1=1  ";
		if(user.getUiNum() !=0) {
			sql+=" AND UI_NUM=? ";
			if(user.getUiId() !=null && !"".equals(user.getUiId())) {
				sql+=" AND UI_Id LIKE ? ";
				if(user.getUiName() !=null && !"".equals(user.getUiName())) {
					sql+=" AND UI_NAME LIKE ? ";
				}
			}			
		}else if(user.getUiId() !=null && !"".equals(user.getUiId())) {
			sql+=" AND UI_ID LIKE ? ";
			if(user.getUiName() !=null && !"".equals(user.getUiName())) {
				sql+=" AND UI_NAME LIKE ? ";
			}
		}else if(user.getUiName() !=null && !"".equals(user.getUiName())) {
			sql+=" AND UI_NAME LIKE ? ";
		}
		
		try(Connection con = DBCon.getCon()) {
			try(PreparedStatement ps = con.prepareStatement(sql)) {
				if(user.getUiNum() !=0) {
					ps.setInt(1, user.getUiNum());
					if(user.getUiName() !=null && !"".equals(user.getUiName())){
						ps.setString(2, "%"+user.getUiName()+"%");
						if(user.getUiId() !=null && !"".equals(user.getUiId())){
							ps.setString(3, "%"+user.getUiId()+"%");
						}
					}
				}else if(user.getUiId() !=null && !"".equals(user.getUiId())) {
					ps.setString(1, "%"+user.getUiId()+"%");
					if(user.getUiName() !=null && !"".equals(user.getUiName())){
						ps.setString(2, "%"+user.getUiName()+"%");
					}
				}else if(user.getUiName() !=null && !"".equals(user.getUiName())) {
					ps.setString(1, "%"+user.getUiName()+"%");
				}
					
		
				try(ResultSet rs = ps.executeQuery()){
					while(rs.next()) {
						UserVO u = new UserVO();
						u.setUiNum(rs.getInt("UI_NUM"));
						u.setUiId(rs.getString("UI_ID"));
						u.setUiName(rs.getString("UI_NAME"));
						users.add(u);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	public static void main(String[] args) {
		UserRepository2 ur = new UserRepository2();
		List<UserVO> users = ur.selectUsers(null);
		for(UserVO user:users) {
			System.out.println(user);
		}
	}
}
