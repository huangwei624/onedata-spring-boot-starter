package info.onedata.dynamic.datasource.mybatisplus.interceptor;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.StringUtils;

import java.util.Properties;

@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {
                MappedStatement.class, Object.class }),
        @Signature(type = Executor.class, method = "query", args = {
                MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class }) })
public class DynamicDataSourceInterceptor extends AbstractSqlParserHandler implements Interceptor {
    private static final String MASTER = "master";

    private static final String SLAVE = "slave";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        boolean synchronizationActive = TransactionSynchronizationManager.isSynchronizationActive();
        if(!synchronizationActive) {
            Object[] objects = invocation.getArgs();
            MappedStatement ms = (MappedStatement) objects[0];
            String currentDataSource = DynamicDataSourceContextHolder.peek();
            if(StringUtils.isEmpty(currentDataSource)){
                BoundSql boundSql = ms.getBoundSql(objects[1]);
                System.out.println(boundSql.getSql());
                if(ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                    DynamicDataSourceContextHolder.push(SLAVE);
                }else{
                    DynamicDataSourceContextHolder.push(MASTER);
                }
                Object proceed = invocation.proceed();
                DynamicDataSourceContextHolder.clear();
                return proceed;
            }
        }
        return invocation.proceed();    }

    @Override
    public Object plugin(Object target) {
        // 增删改查的拦截，然后交由intercept处理
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
