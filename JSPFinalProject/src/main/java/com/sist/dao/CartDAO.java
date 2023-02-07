package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.lf5.util.Resource;

import com.sist.vo.*;
import java.io.*;
public class CartDAO {
	private static SqlSessionFactory ssf; // = createdConnection
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {e.printStackTrace();}
	}
	
	// insert
	/*<insert id = "goodsCartInsert" parameterType="CartVO">
		insert into project_goods_buy values(pgb_bno_seq.nextval, #{gno},#{id}, #{account},#{total_price},'n',sysdate)
	</insert>*/
	public static void goodsCartInsert(CartVO vo) { ////////// rs, ps 다 해줌
		ssf.openSession(true).insert("goodsCartInsert",vo);
		// true : 커밋 실행 없으면 커밋 x
	}
	
	/*<select id = "goodsCartListData" resultType="CartVO" parameterType="string"> <!-- 어떤 변수, 어떤 타입을 쓸건지 -->
		SELECT bno, gno, id, account, total_price, TO_CHAR(regdate,'YYYY-MM-DD') as dbday,
		buy_ok, goods_poster, goods_name, goods_price
		FROM project_goods_buy pgb, goods_all ga
		where pgb.gno = ga.no
		AND id = #{id}
	</select>*/
	// select
	public static List<CartVO> goodsCartListData(String id){////////// rs, ps 다 해줌
		List<CartVO> list = null;
		try {
			
			SqlSession session = ssf.openSession();
			list = session.selectList("goodsCartListData",id);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		// true : 커밋 실행 없으면 커밋 x
	}
	
	public static void goodsCartDelete(int bno) {
		ssf.openSession().delete("goodsCartDelete", bno); // delete(int bno)
	}
	
	// delete
	
}
