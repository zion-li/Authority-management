package com.myself.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.myself.exception.ParamException;
import org.apache.commons.collections.MapUtils;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 参数校验工具类
 *
 * @author zion
 */
public class BeanValidator {

    /**
     * 创建一个自己的工厂
     * Builds and returns a instance based on the
     * <p>
     * default Bean Validation provider and following the XML configuration
     */
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    /**
     * Returns an initialized  instance using the
     * factory defaults for message interpolator, traversable resolver
     * and constraint validator factory.
     *
     * @param t      K ：地字段   V：错误信息  object to validate
     * @param groups the group or list of groups targeted for validation
     * @param <T>
     * @return
     */
    public static <T> Map<String, String> validate(T t, Class... groups) {
        //从工厂里获取
        Validator validator = validatorFactory.getValidator();
        //获取校验结果
        Set validateResult = validator.validate(t, groups);
        //不为空，既校验出错
        if (validateResult.isEmpty()) {
            return Collections.emptyMap();
        } else {
            LinkedHashMap errors = Maps.newLinkedHashMap();
            Iterator iterator = validateResult.iterator();
            while (iterator.hasNext()) {
                /*
                 * Describes a constraint  violation. This object exposes the constraint
                 * violation context as well as the message describing the violation.
                 * 描述一个violation的约束，此对象暴露了 violation的上下文约束和violation描述
                 */
                ConstraintViolation violation = (ConstraintViolation) iterator.next();
                errors.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            return errors;
        }
    }

    /**
     * list校验
     *
     * @param collection
     * @return
     */
    public static Map<String, String> validateList(Collection<?> collection) {
        /*
         Preconditions是guava提供的用于进行代码校验的工具类,用来简化我们工作或开发中对代码的校验或预 处理

            checkArgument(boolean expression)：用来校验表达式是否为真，一般用作方法中校验参数
            checkArgument(boolean expression, @Nullable Object errorMessage)：校验表达式是否为真，不为真时显示指定的错误信息。
            checkArgument(boolean expression, @Nullable String errorMessageTemplate, @Nullable Object... errorMessageArgs)：校验表达式是否为真，不为真时显示错误信息，错误信息中允许使用占位符。

            checkState(boolean expression)：校验表达式是否为真，一般用作校验方法返回是否为真。
            checkState(boolean expression, @Nullable Object errorMessage)：当表达式为假的时候，显示指定的错误信息。
            checkState(boolean expression,@Nullable String errorMessageTemplate,@Nullable Object... errorMessageArgs)：允许在错误信息中使用占位符

            checkNotNull(T reference)：校验对象是否为空。
            checkNotNull(T reference, @Nullable Object errorMessage)：对象为空时显示指定的错误信息。
            checkNotNull(T reference, @Nullable String errorMessageTemplate,@Nullable Object... errorMessageArgs)：允许在错误信息中使用占位符。

            checkPositionIndex(int index, int size, @Nullable String desc)：校验元素的索引值是否有效，index大于等于0小于等于size，在无效时显示错误描述信息。
            checkPositionIndex(int index, int size)：错误描述信息为“index”
            checkPositionIndexes(int start, int end, int size)：校验大于等于start，小于end的list的长度是否为size。
         */
        //google提供的基础校验工具类
        Preconditions.checkNotNull(collection);
        Iterator iterator = collection.iterator();
        Map errors;
        do {
            if (!iterator.hasNext()) {
                return Collections.emptyMap();
            }
            Object object = iterator.next();
            errors = validate(object, new Class[0]);
        } while (errors.isEmpty());

        return errors;
    }

    /**
     * object校验
     *
     * @param first
     * @param objects
     * @return
     */
    public static Map<String, String> validateObject(Object first, Object... objects) {
        if (objects != null && objects.length > 0) {
            return validateList(Lists.asList(first, objects));
        } else {
            return validate(first, new Class[0]);
        }
    }

    /**
     * 通过抛出异常的形式进行参数校验，包上面的方法封装起来更加方便
     * @param param 参数校验对象
     * @throws ParamException 参数校验异常
     */
    public static void check(Object param) throws ParamException {
        Map<String, String> map = BeanValidator.validateObject(param);
        if (MapUtils.isNotEmpty(map)) {
            throw new ParamException(map.toString());
        }
    }
}
