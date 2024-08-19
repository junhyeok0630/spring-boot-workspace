package com.example.ex29_jpa_board_rest_api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ex29_jpa_board_rest_api.entity.Board;
import com.example.ex29_jpa_board_rest_api.service.BoardService;

@RestController
@RequestMapping("/api/board")
public class MyController {
    
    @Autowired
    BoardService boardService;

    @RequestMapping
    public String root() throws Exception {
        return "JPA Board API!";
    }

    @RequestMapping(value = "/user-list", method = RequestMethod.GET)
    public List<Board> userlistPage() {
        return boardService.list();
    }

    @GetMapping("/user")
    public ResponseEntity<Board> view(@RequestParam("id") int id) {
        Board board = boardService.view(id);
        if(board != null) {
            return ResponseEntity.ok(board);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/write")
    public ResponseEntity<Board> write(@RequestBody Board board) {
        boardService.write(board.getWriter(), board.getTitle(), board.getContent());

        return ResponseEntity.ok(board);
    }
    
    // @PostMapping("/write")
    // public ResponseEntity<Board> write(@RequestParam("writer") String writer, 
    //         @RequestParam("title") String title, 
    //         @RequestParam("content") String content) {
    //     boardService.write(writer, title, content);

    //     Board newBoard = new Board();
    //     newBoard.setWriter(writer);
    //     newBoard.setTitle(title);
    //     newBoard.setContent(content);

    //     return ResponseEntity.ok(newBoard);
    // }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        boardService.delete(id);

        return ResponseEntity.noContent().build();
    }

    // @DeleteMapping("/delete")
    // public ResponseEntity<Void> delete(@RequestParam("id") int id) {
    //     boardService.delete(id);

    //     return ResponseEntity.noContent().build();
    // }

    @PutMapping("/update/{id}")
    public ResponseEntity<Board> update(@PathVariable int id, @RequestBody Board updatedBoard) {
        try{
            Board board = boardService.update(id, updatedBoard.getWriter(), updatedBoard.getTitle(), updatedBoard.getContent());
            return ResponseEntity.ok(board);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
        
    }
}
