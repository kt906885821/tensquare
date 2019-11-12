package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {
    /**
     * 通过标签id查询最新问题,使用jpql,分页查询时需要表名后加别名
     *
     * @param labelId  标签id
     * @param pageable 分页条件
     * @return page
     */
    @Query(value = "from Problem p where id in (select problemid from Pl where labelid = ?1) order by replytime desc ")
    Page<Problem> findLatestByLabelId(String labelId, Pageable pageable);

    /**
     * 通过标签id查询最热门问题(按照回复数降序排序),使用jpql,分页查询时需要表名后加别名
     *
     * @param labelId  标签id
     * @param pageable 分页条件
     * @return page
     */
    @Query(value = "from Problem p where id in (select problemid from Pl where labelid = ?1) order by reply desc ")
    Page<Problem> findHottestByLabelId(String labelId, Pageable pageable);

    /**
     * 通过标签id查询等待回答的问题,使用jpql,分页查询时需要表名后加别名
     *
     * @param labelId  标签id
     * @param pageable 分页条件
     * @return page
     */
    @Query(value = "from Problem p where id in (select problemid from Pl where labelid = ?1) and reply = 0 order by createtime desc ")
    Page<Problem> findWaitedByLabelId(String labelId, Pageable pageable);


    /*
    根据标签ID查询最新问题列表
     */
    @Query("select p from Problem p where id in(select problemid from Pl where labelid=?1) order by replytime desc")
    Page<Problem> findNewListByLabelId(String labelId,Pageable pageable);


    /*
    根据标签ID查询热门问题列表
     */
    @Query("select p from Problem p where id in (select problemid from Pl where labelid=?1) order by reply desc")
    Page<Problem> findHotListByLabelId(String labelId,Pageable pageable);


    /**
     * 等待回答列表
     */
    @Query("select p from Problem p where id in(select problemid from Pl where labelid=?1) and reply=0 order by createtime desc")
    Page<Problem> findWaitListByLabelId(String labelId,Pageable pageable);

}
