package com.leafcom.web.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.leafcom.web.vo.PostVO;

@Repository
public class PostDAOImpl implements PostDAO {

	@Override
	public int getPostCount(int boardId, boolean fullList, String writer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<PostVO> getPostList(int start, int end, int boardId, boolean fullList, String writer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPostHit(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PostVO getPostDetail(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int chkWriter(int num, String writer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatePost(PostVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertPost(PostVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePost(int num) {
		// TODO Auto-generated method stub
		return 0;
	}

}
