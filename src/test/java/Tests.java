import org.junit.Before;
import org.junit.Test;

public class Tests implements Constants {
    SharedMethods sharedMethods = new SharedMethods();

    @Before
    public void Setup() {

        sharedMethods.GetBaseURL();
    }


    @Test
    public void ListUsers() {

        sharedMethods.GetListUsers(Constants.Corrine);
    }

    @Test
    public void ReturnSpecificUser() {

        sharedMethods.GetSpecificUser(2);
    }
    
    @Test
    public void UpdateUser() {

        sharedMethods.UpdateUser(3, "Ivane Ivane");
    }

    @Test
    public void CreateUser() {

        sharedMethods.CreateNewUser();
    }

    @Test
    public void DeleteUser() {

        sharedMethods.DeleteUser(sharedMethods.CreateNewUser());
    }


    @Test
    public void AlreadyDeletedUser() {

        sharedMethods.AlreadyDeletedUser(1);
    }

    @Test
    public void PageLimit() {

        sharedMethods.PageLimit(13);
    }

    @Test
    public void SortAndOrder() {

        sharedMethods.SortAndOrder("name","asc" );
    }
}
