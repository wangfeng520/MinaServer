package com.xy.db;

import java.util.ArrayList;
import java.util.HashMap;

import com.xy.common.model.RoleAttribute;
import com.xy.db.dao.RoleDAO;

public class RoleAttributeDB {
	
	/**��ɫ���� */
	public static HashMap<Integer, RoleAttribute> roleAttr = new HashMap<Integer, RoleAttribute> ();
	
	/**����ɫ��Ӧ�ȼ��������ݼ��ص��ڴ���*/
	public static void roleAttrInit(){
		if(roleAttr == null){
			roleAttr = new HashMap<Integer, RoleAttribute>();
		}
		
		ArrayList<RoleAttribute> roleAttrList = new RoleDAO().getAllRoleAttr();
		for(int i = 0; i < roleAttrList.size(); i ++){
			roleAttr.put(roleAttrList.get(i).getId(), roleAttrList.get(i));
		}
		return ;
	}
	
}
