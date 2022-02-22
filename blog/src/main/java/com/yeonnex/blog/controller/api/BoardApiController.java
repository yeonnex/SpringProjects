package com.yeonnex.blog.controller.api;

import com.sun.media.jfxmediaimpl.HostUtils;
import com.yeonnex.blog.config.auth.PrincipalDetail;
import com.yeonnex.blog.dto.ReplySaveRequestDto;
import com.yeonnex.blog.dto.ResponseDto;
import com.yeonnex.blog.model.Board;
import com.yeonnex.blog.model.Reply;
import com.yeonnex.blog.service.BoardService;
import com.yeonnex.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

// 이 컨트롤러는 나중에 "앱"에도 쓸 수 있음!
@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@RequestBody Board board, @PathVariable int id){
        System.out.println("글 수정하기 api 호출");
        board.getTitle();
        board.getContent();
        boardService.글수정하기(board, id);
        return new ResponseDto<Integer>(HttpStatus.OK,1);
    }
    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){

        boardService.글쓰기(board,principal.getUser());

        return new ResponseDto<Integer>(HttpStatus.OK,1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> delete(@PathVariable int id){
        System.out.println("글 삭제 api 호출");
        boardService.글삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK, 1);
    }


    // 데이터를 받을 때 컨트롤러에서 dto 를 만들어서 받는게 좋다
    // dto 를 사용하지 않은 이유는! 이건 그냥 조그마한 공부용 플젝이었기 때문
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto){
        System.out.println("====");
        System.out.println(replySaveRequestDto);
        boardService.댓글쓰기(replySaveRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK, 1);
    }

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId){
        System.out.println("댓삭");
        boardService.댓글삭제(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK, 1);
    }
    // /auth/joinProc 을 만들지 않은 이유는,
    // 스프링 시큐리티가 이 요청을 가로채가게 하기 위해서이다
}
