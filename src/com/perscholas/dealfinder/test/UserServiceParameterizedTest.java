package com.perscholas.dealfinder.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import com.perscholas.dealfinder.entities.TransactionDetail;
import com.perscholas.dealfinder.entities.User;
import com.perscholas.dealfinder.services.UserService;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.JVM) // Use order annotation
public class UserServiceParameterizedTest {

	// Declare a UserService and expectedUser for Parameterized testing
	UserService userService = null;
	User expectedUser;
	
	// Create constructor for Parameterized testing
	public UserServiceParameterizedTest(User expectedUser) {
		this.expectedUser = expectedUser;
	}
	
	// Define a Collection of User parameters
	@Parameterized.Parameters
	public static Collection params() {
		return Arrays.asList(new Object[] { new User("jim@gmail.com", "Jim", "Bbbb", "Pw123"),
				new User("gary@gmail.com", "Gary", "Kkkkk", "Pw123"),
				new User("steve@gmail.com", "Steve", "Lllll", "Pw123"),
				new User("khanh@gmail.com", "Khanh", "Nnnnnn", "Pw123"),
				new User("kasidit@gmail.com", "Kasidit", "BBbbbb", "Pw123"),
				new User("simon@gmail.com", "Simon", "Yyyy", "Pw123")});
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		// Set an instance of UserService for testing
		userService = new UserService();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetAllUsers() {
		// Gets a list of the users from the database, shouldn't be null
		List<User> users = userService.getAllUsers();
		assertNotNull(users);

		// The list should contain the expectedUser
		assertTrue(users.contains(expectedUser));
	}

	@Test
	public final void testGetUserByEmail() {
		// Get user using defined parameters
		User user = userService.getUserByEmail(expectedUser.getsEmail());
		assertNotNull(user);
		//assertEquals(expectedUser.getsUserID(), user.getsUserID());
	}

	@Test
	public final void testValidateUser() {
		// Validate using credentials from defined parameters
		assertTrue(userService.validateUser(expectedUser.getsEmail(), expectedUser.getsPass()));
	}
	
	@Test
	public final void testValidateStudentWithInvalidStudent() {

		// Validate using wrong password
		assertFalse(userService.validateUser(expectedUser.getsEmail(), "1234"));

		// Validate using wrong username
		assertFalse(userService.validateUser("dan", expectedUser.getsPass()));

		// Validate using wrong username and wrong password
		assertFalse(userService.validateUser("dan", "1234"));
	}

	@Test
	public final void testUpdateUser() {
		// Create a new User that is similar to the expected User
		User user = new User(expectedUser.getsEmail(), expectedUser.getsLastName(), expectedUser.getsFirstName(), expectedUser.getsPass());
		
		// Pass this user to update the expectedUser 
		assertTrue(userService.updateUser(user));
		
	}
}
