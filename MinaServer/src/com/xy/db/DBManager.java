package com.xy.db;

/**
 * �ڴ����ݿ������
 */
public class DBManager {
	
	public static void dbInit(){
		/**��ɫ��Ӧ�ȼ������ڴ����ݿ��ʼ��*/
		RoleAttributeDB.roleAttrInit();
		System.out.println("��ɫ��Ӧ�ȼ������ڴ����ݿ��ʼ���ɹ���");
		return;
	}
}
