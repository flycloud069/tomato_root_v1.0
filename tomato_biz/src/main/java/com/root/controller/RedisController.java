//package com.root.controller;
//
//import com.root.entity.UserEntity;
//import com.root.mapper.UserMapper;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//
//@RestController
//@RequestMapping("/webSocket")
//@CacheConfig(cacheNames = "user")
//public class RedisController {
//
//    @Resource
//    UserMapper userMapper;
//
//
//    /**
//     * Put语句适合去保存和更新数据
//     */
//    @CachePut(key = "#userEntity.id")
//    @RequestMapping(value = "/CachePut", method = RequestMethod.POST)
//    public String CachePut(@RequestBody @Validated UserEntity userEntity) {
////        UserEntity userEntity=new UserEntity();
////        userEntity.setId(id);
////        userEntity.setName(name);
//        userMapper.insert(userEntity);
//        return "success";
//
//    }
//
//    /**
//     * Evict删除缓存
//     */
//    @CacheEvict(key = "#p0", allEntries = true)
//    @RequestMapping(value = "/CacheEvict", method = RequestMethod.GET)
//    public String CacheEvict(@RequestParam(required = true) String id) {
//        userMapper.deleteById(id);
//        return "success";
//
//    }
//
//    /**
//     * 查询缓存
//     */
//    @Cacheable(key = "#p0")
//    @RequestMapping(value = "/Cacheable", method = RequestMethod.GET)
//    public String Cacheable(@RequestParam(required = true) String id) {
//        userMapper.selectById(id);
//        return "success";
//    }
//
//}
