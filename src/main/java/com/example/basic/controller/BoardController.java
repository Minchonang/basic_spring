package com.example.basic.controller;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.basic.model.Board;
import com.example.basic.model.Comment;
import com.example.basic.model.FileAtch;
import com.example.basic.repository.BoardRepository;
import com.example.basic.repository.CommentRepository;
import com.example.basic.repository.FileAtchRepository;

@Controller
@RequestMapping("/board")

public class BoardController {
   @Autowired
   BoardRepository boardRepository;

   @Autowired
   HttpSession session;

   @Autowired
   CommentRepository commentRepository;

   @Autowired
   FileAtchRepository fileAtchRepository;

   // //

   // 댓글 삭제
   @GetMapping("/comment/remove")
   public String commentRemove(@ModelAttribute Comment comment, @RequestParam int boardId) {
      // new Comment, setId()
      // ModelAttribute Comment comment

      commentRepository.delete(comment);
      return "redirect:/board/detail?id=" + boardId;

   }

   // 댓글 작성
   @PostMapping("/comment")
   public String comment(@ModelAttribute Comment comment, @RequestParam int boardId) {

      String name = (String) session.getAttribute("name");
      if (name == null) {
         name = "guest";
      }
      comment.setWriter(name);
      comment.setCreDate(new Date());

      Board board = new Board();
      board.setId(boardId);
      comment.setBoard(board);

      commentRepository.save(comment);

      return "redirect:/board/detail?id=" + boardId;
   }

   // delete
   @GetMapping("/remove")
   public String remove(@RequestParam int id) {

      // 로그인 된 이름
      String loggedName = (String) session.getAttribute("name");
      Optional<Board> dbBoard = boardRepository.findById(id);
      // 현재 게시글의 작성자 이름
      String savedName = dbBoard.get().getWriter();

      // Java의 문자열 비교 주의사항 -> equals()
      if (savedName.equals(loggedName)) {
         Board board = new Board();
         board.setId(id);

         boardRepository.delete(board);
      } else {
         return "redirect:/board/detail?id=" + id;
      }

      return "redirect:/board/list";
   }

   // update
   @PostMapping("/update")
   public String updatePost(@ModelAttribute Board board) {
      System.out.println("성공했습니다");
      boardRepository.save(board);

      return "redirect:/board/detail?id=" + board.getId();
   }

   @GetMapping("/update")
   public String update(@RequestParam int id, Model model) {
      Optional<Board> opt = boardRepository.findById(id);
      model.addAttribute("board", opt.get());

      return "board/update";
   }

   // read
   @GetMapping("/detail")
   public String detail(@RequestParam int id, Model model) {
      Optional<Board> opt = boardRepository.findById(id);
      model.addAttribute("board", opt.get());

      return "board/detail";
   }

   @GetMapping({ "/list", "/" })
   public String list(Model model, @RequestParam(defaultValue = "1") int p) {
      Direction dic = Direction.ASC;
      Sort sort = Sort.by(dic, "id");
      Pageable page = PageRequest.of(p - 1, 20, sort);

      Page<Board> boardList = boardRepository.findAll(page);
      model.addAttribute("boardList", boardList);

      return "board/list";
   }

   // create
   @GetMapping("/write")
   public String write() {
      String name = (String) session.getAttribute("name");
      if (name == null || name.trim().equals("")) {
         // trim(): 공백 제거
         return "redirect:/auth/signin";
      }

      return "board/write";
   }

   @PostMapping("/write")
   @Transactional(noRollbackFor = { ArithmeticException.class }, rollbackFor = { ArithmeticException.class })
   public String writePost(@ModelAttribute Board board, @RequestParam("file") MultipartFile mFile) {
      Board savedBoard = boardRepository.save(board);
      // 업로드된 파일 이름 확인
      String oFileName = mFile.getOriginalFilename();

      FileAtch fileAtch = new FileAtch();
      fileAtch.setOriginalName(oFileName);
      fileAtch.setSaveName(oFileName);
      fileAtch.setBoard(savedBoard);
      fileAtchRepository.save(fileAtch);

      return "redirect:/board/list";
   }
}
