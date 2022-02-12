package com.yeonnex.blog.controller.api;

import com.yeonnex.blog.config.auth.PrincipalDetail;
import com.yeonnex.blog.dto.ResponseDto;
import com.yeonnex.blog.model.Board;
import com.yeonnex.blog.service.BoardService;
import com.yeonnex.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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

    // /auth/joinProc 을 만들지 않은 이유는,
    // 스프링 시큐리티가 이 요청을 가로채가게 하기 위해서이다
}
