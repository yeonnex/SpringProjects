package com.yeonnex.blog.service;

import com.yeonnex.blog.model.Board;
import com.yeonnex.blog.model.User;
import com.yeonnex.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    @Transactional
    public void 글쓰기(Board board, User user){
        String title = board.getTitle();
        String content = board.getContent();
        board.setUser(user);

        System.out.println("======");
        System.out.println(title);
        System.out.println(content);
        System.out.println("유저");
        System.out.println(board.getUser());
        System.out.println("======");

        boardRepository.save(board);

    }

    @Transactional(readOnly = true) // select 만 하는거니까 readOnly=true 로!
    public Page<Board> 글목록(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board 글상세보기(int id){
        return boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("글 상세보기 실패");
        });
    }

    @Transactional
    public void 글삭제하기(int id){
        boardRepository.deleteById(id);
    }

    @Transactional
    public void 글수정하기(Board board, int id){
        System.out.println("글수정하기 서비스 시작!");
        Optional<Board> selectedBoard = boardRepository.findById(id);
        selectedBoard.ifPresent((board1)->{
            board1.setTitle(board.getTitle());
            board1.setContent(board.getContent());
            boardRepository.save(board1);
        });
    }
}
