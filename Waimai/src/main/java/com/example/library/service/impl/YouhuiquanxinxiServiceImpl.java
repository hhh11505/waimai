package com.example.library.service.impl;

import com.example.library.dao.YonghuxinxiDao;
import com.example.library.dao.YouhuiquanxinxiDao;
import com.example.library.model.Yonghuxinxi;
import com.example.library.model.Youhuiquanxinxi;
import com.example.library.service.YonghuxinxiService;
import com.example.library.service.YouhuiquanxinxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class YouhuiquanxinxiServiceImpl implements YouhuiquanxinxiService {
    @Autowired
    private YouhuiquanxinxiDao youhuiquanxinxiDao;
    @Override
    public List<Youhuiquanxinxi> findAllYouhuiquanxinxi(){
        return youhuiquanxinxiDao.findAllYouhuiquanxinxi();
    }


}
