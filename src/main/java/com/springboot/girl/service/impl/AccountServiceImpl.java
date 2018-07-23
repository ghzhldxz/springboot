package com.springboot.girl.service.impl;

import com.springboot.girl.bean.Account;
import com.springboot.girl.bean.AccountExample;
import com.springboot.girl.mapper.AccountMapper;
import com.springboot.girl.service.AccountService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 转账服务实现类
 * @Author GuanHuizhen
 * @Date 2018/7/23
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    Log logger = LogFactory.getLog(AccountServiceImpl.class);

    @Autowired
    AccountMapper accountMapper;

    //@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)//默认是有事务的，只要在需要回滚的地方抛异常
    public String transfer(String in, String out, Double money) throws Exception{
        AccountExample exmple = new AccountExample();
        exmple.createCriteria().andNameEqualTo(out);
        List<Account> listOut = accountMapper.selectByExample(exmple);
        if(listOut == null || listOut.isEmpty()) {
            logger.info("转出账号错误，请重新确认！");
            return "转出账号错误，请重新确认！";
        }
        if(listOut.get(0).getMoney()-money<0) {
            logger.info("账户余额不足，转账失败");
            return out+"账户余额不足，转账失败";
        }
        int ret =accountMapper.updateMoneyByName(out,listOut.get(0).getMoney()-money);
        if(ret<=0) {
            logger.info("转账失败");
            return "FAILURE";
        }
        exmple.clear();
        exmple.createCriteria().andNameEqualTo(in);
        List<Account> listIn = accountMapper.selectByExample(exmple);
        if(listIn == null || listIn.isEmpty()) {
            logger.info("转入账号错误，请重新确认！");
            throw new Exception("转入账号错误，请重新确认！");
        }
        ret =accountMapper.updateMoneyByName(in,listIn.get(0).getMoney()+money);
        if(ret<=0) {
            logger.info("入账失败");
            throw new Exception("入账失败！");
        }


        return "SUCCESS";
    }
}
