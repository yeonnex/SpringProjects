package com.yeonnex.blog.repository;

import com.yeonnex.blog.dto.ReplySaveRequestDto;
import com.yeonnex.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    @Modifying
    @Query(value="INSERT INTO reply(userId, boardId, content,createDate) VALUES(?1, ?2, ?3, now())", nativeQuery = true)
    int mSave(int userId, int boardId, String content); // 이렇게 하면 굳이 영속화 시킬 필요가 없다!
    // JDBC 는 업데이트된 행의 개수를 리턴해준다
}
