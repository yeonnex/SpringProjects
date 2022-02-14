package com.yeonnex.blog.controller;

import com.yeonnex.blog.model.Board;
import com.yeonnex.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class BoardController {
    
    private final BoardService boardService;
    
    public BoardController(BoardService boardService){
        this.boardService = boardService;   
    }

    @GetMapping("")
    public String index(Model model, @PageableDefault(size=3, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){ //컨트롤러에서 세션을 어떻게 찾는지?
        model.addAttribute("boards", boardService.글목록(pageable));
        return "index"; // viewResolver 작동!!
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model){
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/detail";
    }

    // USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) { // Model 은 "데이터를 가지고" 해당 뷰까지 가게 함
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/updateForm";
    }
}
