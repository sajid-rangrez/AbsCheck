package com.alite.AbstractCheck;

import com.alite.AbstractCheck.entity.Citation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyRepository extends JpaRepository<Citation, Integer> {
    Optional<Citation> findByPui(String pui);

    @Query(value = "SELECT lc.record_id,lc.pui,lc.problem,lc.abs_citation, lc.api_abstract, lc.created_date, lc.user_created from lit_citation lc join lit_item li on li.record_id=lc.fk_item_rec_id join lit_query lq on lq.record_id=li.fk_literature_query and lq.lit_type='7122002'", nativeQuery = true)
    List<Citation> findAllRequired();


}
