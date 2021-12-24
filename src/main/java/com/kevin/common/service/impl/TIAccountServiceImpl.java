package com.kevin.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kevin.common.domain.TIAccount;
import com.kevin.common.mapper.TIAccountMapper;
import com.kevin.common.service.ITIAccountService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kevin
 * @since 2021-12-20
 */
@Service
public class TIAccountServiceImpl extends ServiceImpl<TIAccountMapper, TIAccount> implements ITIAccountService {

}
