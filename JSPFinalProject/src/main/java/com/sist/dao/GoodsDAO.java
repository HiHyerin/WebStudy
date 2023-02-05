package com.sist.dao;
import java.util.*;

import com.sist.vo.GoodsVO;

import java.sql.*;
public class GoodsDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	//기능 => 페이징
	// 1. 전체출력
	public ArrayList<GoodsVO> goodsAllListData(int page){
		ArrayList<GoodsVO> list = new ArrayList<GoodsVO>();
		try {
			conn = CreateConnection.getConnection();
			String sql = "select no,goods_name,goods_poster,num "
					+"from (select no,goods_name,goods_poster,rownum as num "
					+"from (select /*+INDEX_ASC(goods_all ga_no_pk)*/ no,goods_name,goods_poster "
					+"from goods_all)) "
					+"where num between ? and ?";
			
			ps = conn.prepareStatement(sql);
			int rowSize=20;
			int start = (rowSize*page)-(rowSize-1);
			int end = rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery();
//			System.out.println("rs:"+rs);
			while(rs.next()) {
				System.out.println(rs.getInt(1));
				GoodsVO vo = new GoodsVO();
				vo.setNo(rs.getInt(1));
				vo.setGoods_name(rs.getString(2));
				vo.setGoods_poster(rs.getString(3));
				list.add(vo);
			}
			rs.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return list;
	}
	
	public int goodsAllTotalPage() {
		int total = 0;
		try {
			conn = CreateConnection.getConnection();
			String sql="select ceil(count(*)/20.0) from goods_all";
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return total;
	}
	
	
	
	// 2. 특가
	public ArrayList<GoodsVO> goodsSpecialListData(int page){
		ArrayList<GoodsVO> list = new ArrayList<GoodsVO>();
		try {
			conn = CreateConnection.getConnection();
			String sql = "select no,goods_name,goods_poster,num "
					+"from (select no,goods_name,goods_poster,rownum as num "
					+"from (select /*+INDEX_ASC(goods_special gs_no_pk)*/ no,goods_name,goods_poster "
					+"from goods_special)) "
					+"where num between ? and ?";
			
			ps = conn.prepareStatement(sql);
			int rowSize=20;
			int start = (rowSize*page)-(rowSize-1);
			int end = rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				GoodsVO vo = new GoodsVO();
				vo.setNo(rs.getInt(1));
				vo.setGoods_name(rs.getString(2));
				vo.setGoods_poster(rs.getString(3));
				list.add(vo);
			}
			rs.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return list;
	}
	
	public int goodsSpecialTotalPage() {
		int total = 0;
		try {
			conn = CreateConnection.getConnection();
			String sql="select ceil(count(*)/20.0) from goods_special";
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return total;
	}
	
	
	// 3. 베스트
	public ArrayList<GoodsVO> goodsBestListData(int page){
		ArrayList<GoodsVO> list = new ArrayList<GoodsVO>();
		try {
			conn = CreateConnection.getConnection();
			String sql = "select no,goods_name,goods_poster,num "
					+"from (select no,goods_name,goods_poster,rownum as num "
					+"from (select /*+INDEX_ASC(goods_best gb_no_pk)*/ no,goods_name,goods_poster "
					+"from goods_best)) "
					+"where num between ? and ?";
			
			ps = conn.prepareStatement(sql);
			int rowSize=20;
			int start = (rowSize*page)-(rowSize-1);
			int end = rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				GoodsVO vo = new GoodsVO();
				vo.setNo(rs.getInt(1));
				vo.setGoods_name(rs.getString(2));
				vo.setGoods_poster(rs.getString(3));
				list.add(vo);
			}
			rs.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return list;
	}
	
	public int goodsBestTotalPage() {
		int total = 0;
		try {
			conn = CreateConnection.getConnection();
			String sql="select ceil(count(*)/20.0) from goods_best";
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return total;
	}
	
	
	// 4. 신상품
	public ArrayList<GoodsVO> goodsNewListData(int page){
		ArrayList<GoodsVO> list = new ArrayList<GoodsVO>();
		try {
			conn = CreateConnection.getConnection();
			String sql = "select no,goods_name,goods_poster,num "
					+"from (select no,goods_name,goods_poster,rownum as num "
					+"from (select /*+INDEX_ASC(goods_new gn_no_pk)*/ no,goods_name,goods_poster "
					+"from goods_new)) "
					+"where num between ? and ?";
			
			ps = conn.prepareStatement(sql);
			int rowSize=20;
			int start = (rowSize*page)-(rowSize-1);
			int end = rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				GoodsVO vo = new GoodsVO();
				vo.setNo(rs.getInt(1));
				vo.setGoods_name(rs.getString(2));
				vo.setGoods_poster(rs.getString(3));
				list.add(vo);
			}
			rs.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return list;
	}
	
	public int goodsNewTotalPage() {
		int total = 0;
		try {
			conn = CreateConnection.getConnection();
			String sql="select ceil(count(*)/20.0) from goods_best";
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return total;
	}
	
	
	public GoodsVO goodsDetailData(int no) {
		GoodsVO vo = new GoodsVO();
		try {
			conn = CreateConnection.getConnection();
			String sql="select no, goods_poster, goods_price, goods_name, goods_delivery, goods_first_price, account, "
					+"goods_discount, goods_sub "
					+"from goods_all "
					+"where no=?"; // mvc에서 모든 전송은 model이 => request.setAttribute()
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setGoods_poster(rs.getString(2));
			vo.setGoods_price(rs.getString(3));
			vo.setGoods_name(rs.getString(4));
			vo.setGoods_delivery(rs.getString(5));
			vo.setGoods_first_price(rs.getString(6));
			vo.setAccount(rs.getInt(7));
			vo.setGoods_discount(rs.getInt(8));
			vo.setGoods_sub(rs.getString(9));
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return vo;
	}
	
}
