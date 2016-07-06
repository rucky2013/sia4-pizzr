package cc.pizzr.validation;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 将一个类里面的field和同一类型的其他field(s)比较是否相等。如果相等则通过，否则不通过。
 * 
 * @author lijinting01
 * 
 */
public class FieldEqualsValidator implements
		ConstraintValidator<FieldEqualsConstraint, Object> {
	private static final Logger logger = LoggerFactory
			.getLogger(FieldEqualsValidator.class);

	private String target;

	private String[] compareTos;

	@Override
	public void initialize(final FieldEqualsConstraint constraintAnnotation) {
		target = constraintAnnotation.targetField();
		compareTos = constraintAnnotation.compareTo();
	}

	@Override
	public boolean isValid(final Object value,
			final ConstraintValidatorContext context) {
		try {
			Object fieldValue = BeanUtils.getProperty(value, target);
			for (String compareTo : compareTos) {
				Object compareToValue = BeanUtils.getProperty(value, compareTo);
				if (!Objects.equals(compareToValue, fieldValue)) {
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(
							context.getDefaultConstraintMessageTemplate())
							.addPropertyNode(target).addConstraintViolation();
					return false;
				}
			}

		} catch (Exception ignore) {
			// ignores exception
			logger.error("Exception Occured When Trying To Get Property:",
					ignore);
			throw new RuntimeException("Exception Occured When Trying To Get Property", ignore);
		}
		return true;
	}
}
