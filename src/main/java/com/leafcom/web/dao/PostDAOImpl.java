package com.leafcom.web.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leafcom.web.vo.PostVO;

@Repository
public class PostDAOImpl implements PostDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int getPostCount(int boardId, boolean fullList, String writer) {
		return sqlSession.selectOne("com.leafcom.web.dao.PostDAO.getPostCount");
	}

	@Override
	public List<PostVO> getPostList(int start, int end, int boardId, boolean fullList, String writer) {
		return sqlSession.selectList("com.leafcom.web.dao.PostDAO.getPostList");
	}

	@Override
	public void addPostHit(int num) {
		sqlSession.update("com.leafcom.web.dao.PostDAO.addPostHit", num);
	}

	@Override
	public PostVO getPostDetail(int num) {
		return sqlSession.selectOne("com.leafcom.web.dao.PostDAO.getPostDetail",num);
	}

	@Override
	public int chkWriter(int num, String writer) {
		return sqlSession.selectOne("com.leafcom.web.dao.PostDAO.chkWriter");
	}

	@Override
	public int updatePost(PostVO vo) {
		return sqlSession.update("com.leafcom.web.dao.PostDAO.updatePost", vo);
	}
	
	@Override
	public int updateRef(PostVO vo) {
		return sqlSession.update("com.leafcom.web.dao.PostDAO.updateRef", vo);
	}
	
	@Override
	public int insertPost(PostVO vo) {
		return sqlSession.insert("com.leafcom.web.dao.PostDAO.insertPost", vo);
	}
	
	@Override
	public int hasReply(int ref, int refStep, int refLevel) {
		return sqlSession.selectOne("com.leafcom.web.dao.PostDAO.hasReply");
	}
	
	@Override
	public int deletePost(PostVO vo) {
		return sqlSession.delete("com.leafcom.web.dao.PostDAO.deletePost", vo);
	}

	@Override
	public int deletePostRef(PostVO pVo) {
		return sqlSession.delete("com.leafcom.web.dao.PostDAO.deletePostRef", pVo);
	}



}
