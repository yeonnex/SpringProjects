package com.yeonnex.blog.service;

import com.yeonnex.blog.model.Board;
import com.yeonnex.blog.model.User;
import com.yeonnex.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;

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

    @Transactional
    public List<Board> 글목록(){
        return boardRepository.findAll();
    }
}
