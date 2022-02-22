package com.yeonnex.blog.test;

import com.yeonnex.blog.model.Board;
import com.yeonnex.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ReplyControllerTest {
    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/test/board/{id}")
    public Optional<Board> getBoard(@PathVariable int id){
        return boardRepository.findById(id); // 리턴시 jackson 라이브러리 발동!!!(오브젝트를 json 으로 리턴) => 모델의 getter 를 호출. 설정안해주면 무한참조가 일어날 수 있으니 설정에 주의
    }
}
