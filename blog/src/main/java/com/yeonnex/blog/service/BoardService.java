package com.yeonnex.blog.service;

import com.yeonnex.blog.dto.ReplySaveRequestDto;
import com.yeonnex.blog.model.Board;
import com.yeonnex.blog.model.Reply;
import com.yeonnex.blog.model.User;
import com.yeonnex.blog.repository.BoardRepository;
import com.yeonnex.blog.repository.ReplyRepository;
import com.yeonnex.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
//    public BoardService(BoardRepository bRepo, ReplyRepository rRepo){
//        this.boardRepository = bRepo;
//        this.replyRepository = rRepo;
//    }
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
            // 해당 함수 종료시(Service 가 종료될 때) 트랜잭션이 종료된다. 이때 더티체킹 - 자동 업데이트가 됨. db flush
//            boardRepository.save(board1); 이거 안해줘도 됨
        });
    }

    @Transactional
    public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto){

        int result = replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
        System.out.println("BoardService - 댓글 쓰기 (업데이트된 행의 수): " + result);

    }

    @Transactional
    public void 댓글삭제(int id){
        System.out.println("댓글 삭제중 ... ");
        replyRepository.deleteById(id);
    }
}
