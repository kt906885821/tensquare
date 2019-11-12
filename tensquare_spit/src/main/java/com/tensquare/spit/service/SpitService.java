package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.IdWorker;

import java.util.List;

@Service
public class SpitService {
    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private MongoTemplate mongoTemplate;

    /*
    查询全部记录
     */
    public List<Spit> findAll(){
        return spitDao.findAll();
    }
    /*
    根据主键查询实体
     */
    public Spit findById(String id){
        return spitDao.findById(id).get();
    }
    /*
    增加
     */
    public void add(Spit spit){
        spit.setId(idWorker.nextId()+"");
        spitDao.save(spit);
    }
    /*
    删除
     */
    public void deleteById(String id){
        spitDao.deleteById(id);
    }
    /*
    修改
     */
    @Transactional
    public void update(Spit spit){
        spitDao.save(spit);
    }
    /*
    根据上级ID查询列表
     */
    public Page<Spit> findParentid(String parentid,int page,int size){
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return spitDao.findByParentid(parentid,pageRequest);
    }
    /*
    点赞
     */
    public void updateThumbup(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }


}
