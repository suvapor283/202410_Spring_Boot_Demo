package com.example.basic.domain.comment.repository;

import com.example.basic.domain.comment.entity.Comment;
import com.example.basic.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByAuthorOrderByIdDesc(Member author);
}