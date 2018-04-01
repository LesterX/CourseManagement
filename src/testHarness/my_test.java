package testHarness;

import operations.Login;

import java.io.IOException;

import authenticatedUsers.LoggedInAuthenticatedUser;

public class my_test 
{
	public static void main(String[] args) throws IOException
	{
		Login li = new Login();
		LoggedInAuthenticatedUser user = li.execute();
	}
}
