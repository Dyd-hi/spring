package kr.or.board.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.BoardRowMapper;
import kr.or.board.model.vo.File;
import kr.or.board.model.vo.FileRowMapper;

@Repository
public class BoardDao {
	@Autowired
	private JdbcTemplate jdbctemplate;

	public int insertBoard(Board b) {
		String query = "insert into board values(board_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'))";
		Object[] params = {b.getBoardTitle() , b.getBoardWriter() , b.getBoardContent()};
		int result = jdbctemplate.update(query,params);
		return result;
	}

	public int selectBoardNo() {
		String query = "select max(board_no) from board";
		int boardNo = jdbctemplate.queryForObject(query, int.class);
		return boardNo;
	}

	public int insertFile(File f) {
		String query = "insert into file_tbl values(file_seq.nextval,?,?,?)";
		Object[] params = {f.getFilename() , f.getFilepath() , f.getBoardNo()};
		int result = jdbctemplate.update(query, params);
		return result;
	}

	public List boardList() {
		String query = "select * from board";
		List list  =jdbctemplate.query(query, new BoardRowMapper());
		return list;
	}

	public List selectOneBoard(int boardNo) {
		String query = "select * from board where board_no=?";
		Object[] params = {boardNo};
		List list = jdbctemplate.query(query, params , new BoardRowMapper());
		return list;
	}

	public ArrayList<File> selectFile(int boardNo) {
		String query = "select * from file_tbl where board_no=?";
		Object[] params = {boardNo};
		List list = jdbctemplate.query(query, params , new FileRowMapper());
		return (ArrayList<File>)list;
	}
}
