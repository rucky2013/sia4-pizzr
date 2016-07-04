package cc.picc.web;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.junit.BeforeClass;
import org.junit.Test;

import cc.picc.validation.FieldEqualsConstraint;

public class FieldEqualsConstraintTest {

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void confirmSeatCountFails() {
		Car car = new Car("Morris", "DD-AB-123", 4, 2);

		Set<ConstraintViolation<Car>> constraintViolations = validator
				.validate(car);

		assertEquals(1, constraintViolations.size());
		assertEquals("not equals", constraintViolations.iterator().next()
				.getMessage());
	}

	@Test
	public void carIsValid() {
		Car car = new Car("Morris", "DD-AB-123", 2, 2);

		Set<ConstraintViolation<Car>> constraintViolations = validator
				.validate(car);

		assertEquals(0, constraintViolations.size());
	}

	@FieldEqualsConstraint(targetField = "confirmSeatCount", compareTo = "seatCount", message = "not equals")
	public static class Car {

		@NotNull
		private String manufacturer;

		@NotNull
		@Size(min = 2, max = 14)
		private String licensePlate;

		@Min(2)
		private int seatCount;

		@Min(2)
		private int confirmSeatCount;

		public Car(String manufacturer, String licencePlate, int seatCount,
				int confirmSeatCount) {
			this.manufacturer = manufacturer;
			this.licensePlate = licencePlate;
			this.seatCount = seatCount;
			this.confirmSeatCount = confirmSeatCount;
		}

		public String getManufacturer() {
			return manufacturer;
		}

		public String getLicensePlate() {
			return licensePlate;
		}

		public int getSeatCount() {
			return seatCount;
		}

		public int getConfirmSeatCount() {
			return confirmSeatCount;
		}

		public void setManufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
		}

		public void setLicensePlate(String licensePlate) {
			this.licensePlate = licensePlate;
		}

		public void setSeatCount(int seatCount) {
			this.seatCount = seatCount;
		}

		public void setConfirmSeatCount(int confirmSeatCount) {
			this.confirmSeatCount = confirmSeatCount;
		}
	}
}
