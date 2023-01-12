package com.summer.utils;

import cn.hutool.crypto.SecureUtil;
import com.summer.exception.ServiceException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 类名: SqlInjectionUtils
 * </p>
 * <p>
 * 包名: com.bestpay.common.core.utils
 * </p>
 * <p>
 * 描述:
 * </p>
 * <p>
 *
 * @author: tubo
 * </p>
 * <p>
 * @since: 2022/4/25 16:19
 * </p>
 */
public class SqlInjectionUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SqlInjectionUtils.class);
    /**
     * sign 用于sql加签的盐值【SQL漏洞】
     * （上线修改值 TABLE_DICT_SIGN_SALT，同步修改前端的盐值）
     */
    private static final String TABLE_DICT_SIGN_SALT = "20200626";

    private static final String XSS_STR = "'|and |exec |insert |select |delete |update |drop |count |chr |mid |master |truncate |char |declare |regex |;|or |+|,";

    private static final String REG = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";


    /**
     * 表示忽略大小写
     */
    private static final Pattern SQL_PATTERN = Pattern.compile(REG, Pattern.CASE_INSENSITIVE);

    /**
     * 针对sql进行额外的sign签名校验（增加安全机制）
     *
     * @param dictCode:
     * @param sign:
     * @param request:
     * @Return: void
     */
    public static void checkDictTableSign(String dictCode, String sign, HttpServletRequest request) {
        //表字典SQL注入漏洞,签名校验
        String accessToken = request.getHeader("X-Access-Token");
        String signStr = dictCode + SqlInjectionUtils.TABLE_DICT_SIGN_SALT + accessToken;
        String javaSign = SecureUtil.md5(signStr);
        if (!javaSign.equals(sign)) {
            LOGGER.warn("SQL注入漏洞签名校验失败 ：" + sign + "!=" + javaSign + ",dictCode=" + dictCode);
            throw new ServiceException("校验失败，无权限访问~");
        }
        LOGGER.warn("SQL注入漏洞签名校验成功！sign=" + sign + ",dictCode=" + dictCode);
    }


    /**
     * sql注入过滤处理，遇到注入关键字抛异常
     *
     * @param value
     * @return
     */
    public static void filterContent(String value) {
        if (StringUtils.isEmpty(value)) {
            return;
        }
        // 统一转为小写
        value = value.toLowerCase();
        String[] xssArr = XSS_STR.split("\\|");
        for (int i = 0; i < xssArr.length; i++) {
            if (value.indexOf(xssArr[i]) > -1) {
                LOGGER.warn("----请注意，存在SQL注入关键词---> {}", xssArr[i]);
                LOGGER.warn("请注意，值可能存在SQL注入风险!---> {}", value);
                throw new ServiceException("【" + value + "】该值输入非法，可能存在风险，请重新输入~");
            }
        }
        return;
    }

    /**
     * sql注入过滤处理，遇到注入关键字抛异常
     *
     * @param values
     * @return
     */
    public static void filterContent(String[] values) {
        String[] xssArr = XSS_STR.split("\\|");
        for (String value : values) {
            if (StringUtils.isEmpty(value)) {
                return;
            }
            // 统一转为小写
            value = value.toLowerCase();
            for (int i = 0; i < xssArr.length; i++) {
                if (value.indexOf(xssArr[i]) > -1) {
                    LOGGER.warn("请注意，存在SQL注入关键词---> {}", xssArr[i]);
                    LOGGER.warn("请注意，值可能存在SQL注入风险!---> {}", value);
                    throw new ServiceException("【" + value + "】该值输入非法，可能存在风险，请重新输入~");
                }
            }
        }
        return;
    }

    /**
     * 注入过滤
     *
     * @param value
     * @return
     */
    @Deprecated
    public static void specialFilterContent(String value) {
        String[] xssArr = XSS_STR.split("\\|");
        if (StringUtils.isEmpty(value)) {
            return;
        }
        // 统一转为小写
        value = value.toLowerCase();
        for (int i = 0; i < xssArr.length; i++) {
            if (value.indexOf(xssArr[i]) > -1 || value.startsWith(xssArr[i].trim())) {
                LOGGER.warn("请注意，存在SQL注入关键词---> {}", xssArr[i]);
                LOGGER.warn("请注意，值可能存在SQL注入风险!---> {}", value);
                throw new ServiceException("【" + value + "】该值输入非法，可能存在风险，请重新输入~");
            }
        }
        return;
    }


    /**
     * 注入过滤
     *
     * @param value
     * @return
     */
    @Deprecated
    public static void specialFilterContentForOnlineReport(String value) {
        String specialXssStr = " exec | insert | delete | update | drop | chr | mid | master | truncate | char | declare |";
        String[] xssArr = specialXssStr.split("\\|");
        if (StringUtils.isEmpty(value)) {
            return;
        }
        // 统一转为小写
        value = value.toLowerCase();
        for (int i = 0; i < xssArr.length; i++) {
            if (value.indexOf(xssArr[i]) > -1 || value.startsWith(xssArr[i].trim())) {
                LOGGER.warn("请注意，存在SQL注入关键词---> {}", xssArr[i]);
                LOGGER.warn("请注意，值可能存在SQL注入风险!---> {}", value);
                throw new ServiceException("【" + value + "】该值输入非法，可能存在风险，请重新输入~");
            }
        }
        return;
    }


    /**
     * 参数校验
     *
     * @param str ep: "or 1=1"
     */
    public static boolean isSqlValid(String str) {
        Matcher matcher = SQL_PATTERN.matcher(str);
        if (matcher.find()) {
            //获取非法字符：or
            LOGGER.warn("参数存在非法字符，请确认：" + matcher.group());
            return false;
        }
        return true;
    }
}
