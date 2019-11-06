package api.board;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Board
 */

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board {

    @Id
    int idx;

    String title;

    String content;

    String writer;

    LocalDateTime creatAt;

    LocalDateTime modifyAt;
    
}