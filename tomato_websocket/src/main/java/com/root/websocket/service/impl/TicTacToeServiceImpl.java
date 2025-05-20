//package com.root.websocket.service.impl;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.root.dto.ReturnMessage;
//import com.root.entity.TicTacToeEntity;
//import com.root.mapper.TicTacToeMapper;
//import com.root.util.ResponseUtil;
//import com.root.websocket.service.TicTacToeService;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * @author ：fuyunxiang
// * @date ：Created in 2022年7月25日,0025 18:34
// */
//@Service
//public class TicTacToeServiceImpl implements TicTacToeService {
// @Resource
//    TicTacToeMapper ticTacToeMapper;
//    public ReturnMessage add(TicTacToeEntity ticTacToeEntity){
//       if (ticTacToeMapper.insert(ticTacToeEntity)>=0){
//           return ResponseUtil.fail();
//       }
//        return ResponseUtil.success();
//    };
//    public ReturnMessage get(String id){
//        QueryWrapper<TicTacToeEntity> query = new QueryWrapper();
//        query.lambda().like(TicTacToeEntity::getPlayersId, "%"+id+"%" );
//       List <TicTacToeEntity> ticTacToeEntities = ticTacToeMapper.selectList(query);
//       return ResponseUtil.success(ticTacToeEntities);
//    };
//}
