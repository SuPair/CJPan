package com.whut.pan.dao;

import com.whut.pan.domain.VerifyCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by zc on 2018/12/3.
 */
public interface IVerifyCodeRepository extends JpaRepository<VerifyCode,Integer> {
    //保存的时候判断有没有重复
    VerifyCode findVerifyCodeByCustomNameAndOperatePersonAndRegisterCode(String customName,String operatePerson,String registerCode);
    //保存
    VerifyCode save(VerifyCode verifyCode);
    List<VerifyCode> findVerifyCodeByCustomName(String customName);
    //根据用户名和注册码来修改注册码的状态
    @Modifying
    @Transactional
    @Query("update VerifyCode verifyCode set verifyCode.state=?1 where verifyCode.customName=?2 and verifyCode.operatePerson=?3 and verifyCode.registerCode=?4" )
    int setVerifyCode(boolean state,String customName,String operatePerson,String registerCode);
    List<VerifyCode> findVerifyCodeByRegisterCode(String registerCode);
    //根据id修改状态
    @Modifying
    @Transactional
    @Query("update VerifyCode verifyCode set verifyCode.state=?1 where verifyCode.id=?2" )
    int modifyStateById(boolean state,Integer id);

}
