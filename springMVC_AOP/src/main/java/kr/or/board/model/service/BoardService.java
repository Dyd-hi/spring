package kr.or.board.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.board.model.dao.BoardDao;
import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.File;

@Service
public class BoardService {
	@Autowired
	private BoardDao dao;

	public int insertBoard(Board b, ArrayList<File> fileList) {
		//파일은 board_no가 필요하기 때문에 board
		int result1 = dao.insertBoard(b);
		int result = 0;
		if(result1>0) {
			int boardNo = dao.selectBoardNo();		
			for(File f : fileList) {
				f.setBoardNo(boardNo);
				result += dao.insertFile(f);
			}
		}else {
			return -1;
		}
		return result;
	}

	public ArrayList<Board> boardList() {	
		List list = dao.boardList();
		return (ArrayList<Board>)list;
	}

	public Board selectOneBoard(int boardNo) {
		List list = dao.selectOneBoard(boardNo);
		ArrayList<File> fileName = dao.selectFile(boardNo);
		System.out.println(fileName.size());
		Board board = null;
		if(!list.isEmpty()) {
			board = (Board)list.get(0);
			board.setFileList(fileName);
		}		
		
		return board;
	}
}
