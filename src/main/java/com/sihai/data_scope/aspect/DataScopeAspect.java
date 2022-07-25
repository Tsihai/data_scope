package com.sihai.data_scope.aspect;

import com.sihai.data_scope.annotation.DataScope;
import com.sihai.data_scope.entity.BaseEntity;
import com.sihai.data_scope.entity.Role;
import com.sihai.data_scope.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SecurityConfiguration;

import java.util.List;

@Aspect
@Component
public class DataScopeAspect {

    /**
     * 全部数据权限
     */
    public static final String DATA_SCOPE_ALL = "1";

    /**
     * 自定数据权限
     */
    public static final String DATA_SCOPE_CUSTOM = "2";

    /**
     * 部门数据权限
     */
    public static final String DATA_SCOPE_DEPT = "3";

    /**
     * 部门及以下数据权限
     */
    public static final String DATA_SCOPE_DEPT_AND_CHILD = "4";

    /**
     * 仅本人数据权限
     */
    public static final String DATA_SCOPE_SELF = "5";

    /**
     * 数据权限过滤关键字
     */
    public static final String DATA_SCOPE = "data_scope";


    /**
     * @Before: 前置通知, 在方法执行之前执行
     * @After: 后置通知, 在方法执行之后执行 。
     * @AfterRunning: 返回通知, 在方法返回结果之后执行
     * @AfterThrowing: 异常通知, 在方法抛出异常之后
     * @Around: 环绕通知, 围绕着方法执行
     *
     * @param jp
     * @param dataScope
     */
    @Before("@annotation(dataScope)")
    public void doBefore(JoinPoint jp, DataScope dataScope) {
        // 清除
        clearDataScope(jp);
        // 获取当前登录用户信息
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 判断是否为超级管理员
        if (user.getUserId() == 1L) {
            // 不进行权限过滤
            return;
        }
        StringBuilder sql = new StringBuilder();
        // 获取用户角色
        List<Role> roles = user.getRoles();
        // 遍历角色生成sql
        for (Role role : roles) {
            // 获取角色对应的数据权限
            // 根据data_scope的值，执行对应的sql条件
            String ds = role.getDataScope();
            if (DATA_SCOPE_ALL.equals(ds)) {
                // 如果用户可以查看所有数据， 什么都不做
                return;
            } else if (DATA_SCOPE_CUSTOM.equals(ds)) {
                // 自定义的数据权限，则 根据用户角色查找到部门id
                sql.append(String.format(" OR %s.dept_id in(select rd.dept_id from sys_role_dept rd where rd.role_id=%d)", dataScope.deptAlias(), role.getRoleId()));
            } else if (DATA_SCOPE_DEPT.equals(ds)) {
                sql.append(String.format(" OR %s.dept_id=%d", dataScope.deptAlias(), user.getDeptId()));
            } else if (DATA_SCOPE_DEPT_AND_CHILD.equals(ds)) {
                sql.append(String.format(" OR %s.dept_id in(select dept_id from sys_dept where dept_id=%d or find_in_set(%d, 'ancestors'))", dataScope.deptAlias(), user.getDeptId(), user.getDeptId()));
            } else if (DATA_SCOPE_SELF.equals(ds)) {
                String s = dataScope.userAlias();
                if ("".equals(s)) {
                    // 数据权限仅限于本人
                    sql.append(" OR 1=0");
                } else {
                    sql.append(String.format(" OR %s.user_id=%d", dataScope.userAlias(), user.getUserId()));
                }
            }
        }
        // and( xxx or xxx or xxx)
        Object arg = jp.getArgs()[0];
        if (arg != null && arg instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) arg;
            // 字符串截取
            baseEntity.getParams().put(DATA_SCOPE, " AND (" + sql.substring(4) + ")");
        }

    }

    /**
     * 拼接权限sql前先清空params.data_scope参数防止注入
     *
     * @param jp
     */
    private void clearDataScope(JoinPoint jp) {
        Object arg = jp.getArgs()[0];
        // 如果arg不为空 并且 arg属于BaseEntity 实例
        if (arg != null && arg instanceof BaseEntity) {
            // 强转换为baseEntity
            BaseEntity baseEntity = (BaseEntity) arg;
            // 获取参数
            baseEntity.getParams().put(DATA_SCOPE, "");
        }
    }
}
