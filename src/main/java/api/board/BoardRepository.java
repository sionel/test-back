package api.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * BoardRepository
 */
@Repository
public interface BoardRepository extends JpaRepository<Board,Integer>{

}