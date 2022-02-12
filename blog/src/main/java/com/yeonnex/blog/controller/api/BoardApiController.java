package com.yeonnex.blog.controller.api;

import com.sun.media.jfxmediaimpl.HostUtils;
import com.yeonnex.blog.config.auth.PrincipalDetail;
import com.yeonnex.blog.dto.ResponseDto;
import com.yeonnex.blog.model.Board;
import com.yeonnex.blog.service.BoardService;
import com.yeonnex.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

// 이 컨트롤러는 나중에 "앱"에도 쓸 수 있음!
@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

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

    // /auth/joinProc 을 만들지 않은 이유는,
    // 스프링 시큐리티가 이 요청을 가로채가게 하기 위해서이다
}
