package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{

    /**
     * 推荐职位
     *
     * @param state 职位状态,0：关闭 1:开启  2：推荐
     * @return list
     */
	List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);

    /**
     * 最新职位
     *
     * @param state 职位状态,0：关闭 1:开启  2：推荐
     * @return list
     */
	List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);
}
